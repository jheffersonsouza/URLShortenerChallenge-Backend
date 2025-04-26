package br.jheffersonsouza.backend.UrlShortener.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UrlShortenerService {
    private final int shortenurl_length = 6;
    private HashMap<String, String> urls;

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
