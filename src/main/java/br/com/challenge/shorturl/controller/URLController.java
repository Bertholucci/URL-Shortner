package br.com.challenge.shorturl.controller;

import br.com.challenge.shorturl.model.URL;
import br.com.challenge.shorturl.service.impl.URLServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/")
@Slf4j
public class URLController {

    @Autowired
    private URLServiceImpl urlService;

    @PostMapping("shortenURL")
    private ResponseEntity<URL> shortenURL(@RequestBody URL url){
        return new ResponseEntity<URL>(urlService.save(url), HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public RedirectView localRedirect(@PathVariable("id") String id) {
        URL shortURL = urlService.findByShortUrl(id);

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(shortURL.getFullUrl());
        return redirectView;
    }
}
