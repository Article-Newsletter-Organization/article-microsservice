package br.com.devtops.microservice.article.core.shared;

import br.com.devtops.microservice.article.core.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, String> {
    Optional<Article> findByTitle(String title);

}
