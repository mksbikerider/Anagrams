package name.msutherland.config;

import name.msutherland.Anagrams;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

@SpringBootApplication
@ComponentScan("name.msutherland")
public class SpringStart extends SpringBootServletInitializer {
    private static ConfigurableApplicationContext applicationContext;

    public static void main(String[] args) {
        start(args);
    }

    public static void start(String[] args) {
        applicationContext = SpringApplication.run(name.msutherland.config.SpringStart.class, args);
    }

    public static void stop() {
        applicationContext.stop();
    }

    @Bean
    public Anagrams anagrams() throws IOException {
        Anagrams anagrams = new Anagrams();
        Resource resource = new ClassPathResource("wordlist.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()));
        String word = br.readLine();
        while(word!=null) {
            anagrams.add(word);
            word = br.readLine();
        }
        return anagrams;
    }

}
