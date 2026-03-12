import java.util.*;

public class Problem1UsernameSystem {

    private Map<String, Integer> usernameMap = new HashMap<>();
    private Map<String, Integer> attemptFrequency = new HashMap<>();

    public boolean checkAvailability(String username) {
        attemptFrequency.put(username, attemptFrequency.getOrDefault(username, 0) + 1);
        return !usernameMap.containsKey(username);
    }

    public void registerUser(String username, int userId) {
        usernameMap.put(username, userId);
    }

    public List<String> suggestAlternatives(String username) {
        List<String> suggestions = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            suggestions.add(username + i);
        }
        suggestions.add(username.replace("_", "."));
        return suggestions;
    }

    public String getMostAttempted() {
        return Collections.max(attemptFrequency.entrySet(),
                Map.Entry.comparingByValue()).getKey();
    }

    public static void main(String[] args) {
        Problem1UsernameSystem system = new Problem1UsernameSystem();

        system.registerUser("john_doe", 1);

        System.out.println(system.checkAvailability("john_doe"));
        System.out.println(system.checkAvailability("jane_smith"));
        System.out.println(system.suggestAlternatives("john_doe"));
    }
}