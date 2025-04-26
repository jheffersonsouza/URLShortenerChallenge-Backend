package br.jheffersonsouza.backend.service;

import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.HashMap;
import java.util.Random;

@Service
public class ShortenService {
    private int shortenurl_length = 6;
    private HashMap<String, URL> urls;

    public ShortenService() {
        this.urls = new HashMap<>();
    }

    private final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private final Random RANDOM = new Random();
    private String randomShortURL(){
        StringBuilder sb = new StringBuilder(shortenurl_length);
        for (int i = 0; i < shortenurl_length; i++) {
            int randomIndex = RANDOM.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(randomIndex));
        }
        return sb.toString();
    }

    public void shorten(URL originalUrl){
        String shortenedUrl = randomShortURL();
        while (urls.containsKey(shortenedUrl)){
            shortenedUrl = randomShortURL();
        }
        urls.put(shortenedUrl, originalUrl);
    }

    public URL getOriginalUrl(String shortenedUrl){
        return urls.get(shortenedUrl);
    }

}
