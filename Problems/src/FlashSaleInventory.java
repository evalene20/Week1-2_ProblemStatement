import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

class FlashSaleInventory {

    HashMap<String, AtomicInteger> stock = new HashMap<>();
    HashMap<String, Queue<Integer>> waitingList = new HashMap<>();

    public void addProduct(String productId, int quantity) {
        stock.put(productId, new AtomicInteger(quantity));
        waitingList.put(productId, new LinkedList<>());
    }

    public String purchaseItem(String productId, int userId) {

        AtomicInteger available = stock.get(productId);

        if (available.get() > 0) {
            int remaining = available.decrementAndGet();
            return "Success. Remaining: " + remaining;
        }

        waitingList.get(productId).add(userId);
        return "Added to waiting list";
    }

    public int checkStock(String productId) {
        return stock.get(productId).get();
    }
}