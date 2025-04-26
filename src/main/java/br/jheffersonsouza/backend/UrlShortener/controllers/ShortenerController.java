package br.jheffersonsouza.backend.UrlShortener.controllers;

import br.jheffersonsouza.backend.UrlShortener.service.UrlShortenerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api")
public class ShortenerController {
    @Autowired
    UrlShortenerService urlShortenerService;

    @PostMapping("/shorten")
    @Operation(summary = "Shorten an URL.",
    responses = {
            @ApiResponse(responseCode = "201")
    })
    public ResponseEntity<String> shorten(@RequestParam String url) {
        return ResponseEntity
                .created(URI.create(urlShortenerService.shorten(url)))
                .build();
    }
    @GetMapping("/{shortenedUrl}")
    @Operation(summary = "Get a URL from a shortened one.",
    responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "URL not found")
    })
    public ResponseEntity<String> getShortenedUrl(@PathVariable String shortenedUrl) {
       shortenedUrl = urlShortenerService.shorten(shortenedUrl);
       if (shortenedUrl == null) {
           return ResponseEntity.notFound().build();
       }
       return ResponseEntity.ok(shortenedUrl);
    }
}
