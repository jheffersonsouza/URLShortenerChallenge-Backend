package br.jheffersonsouza.backend.UrlShortener.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Random;

@Service
public class UrlShortenerService {
    private final HashMap<String, String> urls;


    public UrlShortenerService() {
        this.urls = new HashMap<>();
    }

    private final Random RANDOM = new Random();
    private String randomShortURL(){
        int shortenurl_length = 6;
        StringBuilder sb = new StringBuilder(shortenurl_length);
        for (int i = 0; i < shortenurl_length; i++) {
            String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
            int randomIndex = RANDOM.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(randomIndex));
        }
        return sb.toString();
    }

    public String shorten(String originalUrl){
        String shortenedUrl = randomShortURL();
        while (urls.containsKey(shortenedUrl)){
            shortenedUrl = randomShortURL();
        }
        urls.put(shortenedUrl, originalUrl);
        return shortenedUrl;
    }

    public String getOriginalUrl(String shortenedUrl){
        return urls.get(shortenedUrl);
    }

}
