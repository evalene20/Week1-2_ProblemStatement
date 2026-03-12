import java.util.*;

class PlagiarismDetector {

    HashMap<String, Set<Integer>> ngramIndex = new HashMap<>();

    public void indexDocument(int docId, String text) {

        String[] words = text.split(" ");

        for (int i = 0; i <= words.length - 5; i++) {

            String ngram = "";

            for (int j = i; j < i + 5; j++)
                ngram += words[j] + " ";

            ngramIndex
                    .computeIfAbsent(ngram, k -> new HashSet<>())
                    .add(docId);
        }
    }

    public int findMatches(String text) {

        String[] words = text.split(" ");
        int matches = 0;

        for (int i = 0; i <= words.length - 5; i++) {

            String ngram = "";

            for (int j = i; j < i + 5; j++)
                ngram += words[j] + " ";

            if (ngramIndex.containsKey(ngram))
                matches++;
        }

        return matches;
    }
}