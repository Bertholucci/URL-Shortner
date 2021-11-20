package br.com.challenge.shorturl.service;

import br.com.challenge.shorturl.model.URL;
import org.springframework.stereotype.Service;

import java.net.http.HttpResponse;

@Service
public interface URLService {

    URL save(URL url);

    URL findByShortUrl(String fullUrl);

}
