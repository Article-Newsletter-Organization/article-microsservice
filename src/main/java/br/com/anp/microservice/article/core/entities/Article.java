package br.com.anp.microservice.article.core.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity(name = "articles")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder(access = AccessLevel.PUBLIC)
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 100)
    private String subtitle;

    @Column(name = "object_id", nullable = false)
    private String objectId;

    @Column(nullable = false, length = 50)
    private String description;

    @Column(name = "posted_at", nullable = false)
    private Date postedAt;

}
