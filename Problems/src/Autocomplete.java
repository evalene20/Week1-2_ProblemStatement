import java.util.*;

class Autocomplete {

    HashMap<String, Integer> frequency = new HashMap<>();

    public void addQuery(String query) {
        frequency.put(query, frequency.getOrDefault(query, 0) + 1);
    }

    public List<String> search(String prefix) {

        List<String> result = new ArrayList<>();

        for (String query : frequency.keySet()) {

            if (query.startsWith(prefix))
                result.add(query);
        }

        result.sort((a, b) -> frequency.get(b) - frequency.get(a));

        return result.subList(0, Math.min(10, result.size()));
    }
}