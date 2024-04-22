package br.com.anp.microservice.article.core.shared;

import br.com.anp.microservice.article.core.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, String> {
    Optional<Article> findByTitle(String title);

}
