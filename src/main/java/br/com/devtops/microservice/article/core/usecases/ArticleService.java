package br.com.devtops.microservice.article.core.usecases;

import br.com.devtops.microservice.article.core.dtos.CreateArticleDTO;
import br.com.devtops.microservice.article.core.dtos.UpdateArticleDTO;
import br.com.devtops.microservice.article.core.entities.Article;
import br.com.devtops.microservice.article.core.shared.ArticleRepository;
import br.com.devtops.microservice.article.exceptions.CannotFindResourceException;
import br.com.devtops.microservice.article.exceptions.DuplicatedDataException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
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

    public Article getById(String id) {
        Optional<Article> optionalArticle = repository.findById(id);
        if(optionalArticle.isEmpty())
            throw new CannotFindResourceException("Article not found!");

        return optionalArticle.get();
    }

    public Article update(String id, UpdateArticleDTO dto) {
        Optional<Article> optionalArticle = repository.findById(id);
        if(optionalArticle.isEmpty())
            throw new CannotFindResourceException("Article not found!");

        if(dto.getTitle().isPresent()) {
            boolean isNameInUse = repository.findByTitle(dto.getTitle().get())
                    .stream()
                    .anyMatch(articleToCheck -> Objects.equals(articleToCheck.getTitle(), dto.getTitle().get()));
            if(isNameInUse)
                throw new DuplicatedDataException("Title already in use!");
        }

        dto.merge(optionalArticle.get());

        return repository.save(optionalArticle.get());
    }

    public void delete(String id) {
        Optional<Article> optionalArticle = repository.findById(id);
        if(optionalArticle.isEmpty())
            throw new CannotFindResourceException("Article not found!");

        repository.deleteById(id);
    }
}
