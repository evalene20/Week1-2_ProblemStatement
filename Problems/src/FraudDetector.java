import java.util.*;
import java.time.*;

public class FraudDetector {

    // Static nested Transaction class inside FraudDetector
    static class Transaction {
        int id, amount;
        String merchant, account;
        LocalDateTime time;

        Transaction(int id, int amount, String merchant, String account, String timeStr) {
            this.id = id;
            this.amount = amount;
            this.merchant = merchant;
            this.account = account;
            this.time = LocalDateTime.parse(timeStr);
        }
    }

    // Classic Two-Sum
    public List<int[]> findTwoSum(List<Transaction> txns, int target) {
        Map<Integer, Transaction> map = new HashMap<>();
        List<int[]> result = new ArrayList<>();

        for (Transaction t : txns) {
            int complement = target - t.amount;
            if (map.containsKey(complement)) {
                result.add(new int[]{map.get(complement).id, t.id});
            }
            map.put(t.amount, t);
        }
        return result;
    }

    // Duplicate detection
    public Map<String, Map<Integer, Set<String>>> detectDuplicates(List<Transaction> txns) {
        Map<String, Map<Integer, Set<String>>> map = new HashMap<>();
        for (Transaction t : txns) {
            map.computeIfAbsent(t.merchant, k -> new HashMap<>())
                    .computeIfAbsent(t.amount, k -> new HashSet<>())
                    .add(t.account);
        }

        Map<String, Map<Integer, Set<String>>> duplicates = new HashMap<>();
        for (var merchantEntry : map.entrySet()) {
            String merchant = merchantEntry.getKey();
            for (var amountEntry : merchantEntry.getValue().entrySet()) {
                if (amountEntry.getValue().size() > 1) {
                    duplicates.computeIfAbsent(merchant, k -> new HashMap<>())
                            .put(amountEntry.getKey(), amountEntry.getValue());
                }
            }
        }
        return duplicates;
    }

    // Example main method
    public static void main(String[] args) {
        List<Transaction> txns = Arrays.asList(
                new Transaction(1, 500, "StoreA", "acc1", "2026-03-12T10:00"),
                new Transaction(2, 300, "StoreB", "acc2", "2026-03-12T10:15"),
                new Transaction(3, 200, "StoreC", "acc3", "2026-03-12T10:30"),
                new Transaction(4, 500, "StoreA", "acc2", "2026-03-12T11:00")
        );

        FraudDetector fd = new FraudDetector();

        System.out.println("Two-Sum:");
        fd.findTwoSum(txns, 500).forEach(pair -> System.out.println(Arrays.toString(pair)));

        System.out.println("\nDuplicates:");
        System.out.println(fd.detectDuplicates(txns));
    }
}