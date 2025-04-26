package br.jheffersonsouza.backend.controllers;

import br.jheffersonsouza.backend.service.UrlShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class ShortenerController {
    @Autowired
    UrlShortenerService urlShortenerService;

    @PostMapping("/shorten")
    public ResponseEntity<String> shorten(@RequestParam String url) {
        return ResponseEntity
                .created(URI.create(urlShortenerService.shorten(url)))
                .build();
    }
    @GetMapping("/{shortenedUrl}")
    public ResponseEntity<String> getShortenedUrl(@PathVariable String shortenedUrl) {
       shortenedUrl = urlShortenerService.shorten(shortenedUrl);
       if (shortenedUrl == null) {
           return ResponseEntity.notFound().build();
       }
       return ResponseEntity.ok(shortenedUrl);
    }
}
