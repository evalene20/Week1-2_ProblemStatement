import java.util.*;

class RateLimiter {

    class TokenBucket {

        int tokens;
        long lastRefill;

        TokenBucket(int maxTokens) {
            tokens = maxTokens;
            lastRefill = System.currentTimeMillis();
        }
    }

    HashMap<String, TokenBucket> clients = new HashMap<>();

    int LIMIT = 1000;

    public boolean allowRequest(String clientId) {

        clients.putIfAbsent(clientId, new TokenBucket(LIMIT));

        TokenBucket bucket = clients.get(clientId);

        if (bucket.tokens > 0) {
            bucket.tokens--;
            return true;
        }

        return false;
    }
}