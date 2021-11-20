package br.com.challenge.shorturl.repository;

import br.com.challenge.shorturl.model.URL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface URLRepository extends JpaRepository<URL, String> {

    URL findByShortUrl(String shortUrl);

}
