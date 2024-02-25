package br.com.devtops.microservice.article.core.usecases;

import br.com.devtops.microservice.article.core.dtos.CreateArticleDTO;
import br.com.devtops.microservice.article.core.entities.Article;
import br.com.devtops.microservice.article.core.shared.ArticleRepository;
import br.com.devtops.microservice.article.exceptions.CannotFindResourceException;
import br.com.devtops.microservice.article.exceptions.DuplicatedDataException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class ArticleService {

    private ArticleRepository repository;

    public Article create(CreateArticleDTO articleDTO) {
        Optional<Article> optionalArticle = repository.findByTitle(articleDTO.getTitle());
        if(optionalArticle.isPresent())
            throw new DuplicatedDataException("This title is already in use!");

        return repository.save(articleDTO.convertToArticle());
    }

    public List<Article> getAll() {
        return repository.findAll();
    }

    public Article getById(Long id) {
        Optional<Article> optionalArticle = repository.findById(id);
        if(optionalArticle.isEmpty())
            throw new CannotFindResourceException("Article not found!");

        return optionalArticle.get();
    }

    public Article update(Article article) {
        Optional<Article> optionalArticle = repository.findById(article.getId());
        if(optionalArticle.isEmpty())
            throw new CannotFindResourceException("Article not found!");

        boolean isNameInUse = repository.findByTitle(article.getTitle()).stream().anyMatch(articleToCheck -> Objects.equals(articleToCheck.getTitle(), article.getTitle()));
        if(isNameInUse)
            throw new DuplicatedDataException("Title already in use!");

        return repository.save(article);
    }

    public void delete(Long id) {
        Optional<Article> optionalArticle = repository.findById(id);
        if(optionalArticle.isEmpty())
            throw new CannotFindResourceException("Article not found!");

        repository.deleteById(id);
    }
}
