import java.util.*;

class MultiLevelCache {

    LinkedHashMap<String, String> L1 =
            new LinkedHashMap<>(10000, 0.75f, true) {
                protected boolean removeEldestEntry(Map.Entry eldest) {
                    return size() > 10000;
                }
            };

    HashMap<String, String> L2 = new HashMap<>();

    public String getVideo(String videoId) {

        if (L1.containsKey(videoId))
            return "L1 HIT";

        if (L2.containsKey(videoId)) {
            L1.put(videoId, L2.get(videoId));
            return "L2 HIT";
        }

        String video = queryDatabase(videoId);

        L2.put(videoId, video);

        return "DB HIT";
    }

    private String queryDatabase(String videoId) {
        return "video-data";
    }
}