package name.msutherland.rest;

import name.msutherland.Anagrams;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping(path="/{inputWords}")
public class Application {

    private final Anagrams anagrams;

    public Application(Anagrams anagrams) {
        this.anagrams = anagrams;
    }

    @RequestMapping(method= RequestMethod.GET)
    public Map<String,Set<String>> getAnagrams(@PathVariable String inputWords) {
        Map<String,Set<String>> result = new HashMap<>();
        for (String inputWord : inputWords.split(",") ) {
            result.put(inputWord, anagrams.getAnagrams(inputWord));
        }
        return result;
    }
}
