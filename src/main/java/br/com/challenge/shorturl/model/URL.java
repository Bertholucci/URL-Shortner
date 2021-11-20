package br.com.challenge.shorturl.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "URL")
public class URL {

    @Id
    @Column(name = "id")
    public String id;

    @Column(name = "fullUrl")
    public String fullUrl;

    @Column(name = "shortUrl")
    public String shortUrl;

    @Column(name = "expireDate")
    public LocalDateTime dateTime;

}
