package br.jheffersonsouza.backend.UrlShortener.controllers;

import br.jheffersonsouza.backend.UrlShortener.dto.ShortenedUrl;
import br.jheffersonsouza.backend.UrlShortener.service.UrlShortenerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api")
public class ShortenerController {
    private final UrlShortenerService urlShortenerService;

    public ShortenerController(UrlShortenerService urlShortenerService) {
        this.urlShortenerService = urlShortenerService;
    }

    @PostMapping("/shorten")
    @Operation(summary = "Shorten an URL.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Created shortened url")
            })
    public ResponseEntity<ShortenedUrl> shorten(@RequestParam String url) {
        return ResponseEntity.status(HttpStatus.CREATED).body(urlShortenerService.shorten(url));
    }

    @GetMapping("/{shortenedUrl}")
    @Operation(summary = "Get a URL from a shortened one.",
            responses = {
                    @ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "404", description = "URL not found")
            })
    public ResponseEntity<Void> getOriginalURL(@PathVariable String shortenedUrl) {
        shortenedUrl = urlShortenerService.getOriginalUrl(shortenedUrl);
        if (shortenedUrl == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(shortenedUrl)).build();
    }
}
