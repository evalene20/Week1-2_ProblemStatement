import java.util.*;

class DNSCache {

    class DNSEntry {
        String ip;
        long expiry;

        DNSEntry(String ip, int ttl) {
            this.ip = ip;
            this.expiry = System.currentTimeMillis() + ttl * 1000;
        }
    }

    HashMap<String, DNSEntry> cache = new HashMap<>();

    public String resolve(String domain) {

        DNSEntry entry = cache.get(domain);

        if (entry != null && entry.expiry > System.currentTimeMillis()) {
            return "Cache HIT: " + entry.ip;
        }

        String ip = queryUpstream(domain);

        cache.put(domain, new DNSEntry(ip, 300));

        return "Cache MISS: " + ip;
    }

    private String queryUpstream(String domain) {
        return "172.217.14.206"; // simulate DNS lookup
    }
}