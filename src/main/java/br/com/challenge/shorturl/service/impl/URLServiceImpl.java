package br.com.challenge.shorturl.service.impl;

import br.com.challenge.shorturl.model.URL;
import br.com.challenge.shorturl.repository.URLRepository;
import br.com.challenge.shorturl.service.URLService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
@Slf4j
public class URLServiceImpl implements URLService {

    @Autowired
    private URLRepository repository;

    String URL_GETTER_FIXED = "http://localhost:8080/";

    @Override
    public URL save(URL url) {

        log.info("URL FULL OBJECT: " + url);
        log.info("URL FULL OBJECT: " + url.getFullUrl());
        url.setId(generateId());
        url.setFullUrl(url.getFullUrl());
        url.setShortUrl(shortenUrl());
        url.setDateTime(LocalDateTime.now().plusHours(3));

        try{
            repository.save(url);
            return url;
        } catch (Exception e){
            e.printStackTrace();
        }
        return url;
    }

    @Override
    public URL findByShortUrl(String shortUrl) {
        log.info("URL_GETTER_FIXED" + URL_GETTER_FIXED.concat(shortUrl));
        return repository.findByShortUrl(URL_GETTER_FIXED.concat(shortUrl));
    }

    public String generateId (){
        return UUID.randomUUID().toString();
    }

    public String shortenUrl(){
        /*
         * ID generation based on:
         *  [mm] Up to 2 characters (Minute of hour 0 - 59) 		// Resets Every 1 hour
         *  [ss] Up to 2 characters (Second of minute 0 - 59) 		// Resets Every 1 minute
         *  [SS] Up to 2 characters (Fraction of second 0 - 999)	// Resets Every 1 second
         */

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("mmssSS");

        return URL_GETTER_FIXED + now.format(formatter);
    }
}
