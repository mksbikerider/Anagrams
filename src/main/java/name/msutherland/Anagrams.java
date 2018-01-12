package name.msutherland;

import java.util.*;

public class Anagrams {
    private final Map<String,Set<String>> anagrams = new HashMap<>();

    public void add(String word){
        String key = sortWord(word);
        if (!anagrams.containsKey(key)) {
            Set<String> words = new HashSet<>();
            anagrams.put(key, words);
        }
        anagrams.get(key).add(word);
    }

    private String sortWord(String word) {
        char[] wordArray = word.toCharArray();
        Arrays.sort(wordArray);
        return new String(wordArray);
    }

    public Set<String> getAnagrams(String word){
        String key = sortWord(word);
        if (anagrams.containsKey(key)) {
            Set<String> copy = anagrams.get(key);
            copy.remove(word);
            return copy;
        }else{
            return Collections.emptySet();
        }
    }
}
