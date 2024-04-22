package br.com.devtops.microservice.article.core.usecases;

import br.com.devtops.microservice.article.core.dtos.CreateArticleDTO;
import br.com.devtops.microservice.article.core.dtos.UpdateArticleDTO;
import br.com.devtops.microservice.article.core.entities.Article;
import br.com.devtops.microservice.article.core.shared.ArticleRepository;
import br.com.devtops.microservice.article.exceptions.CannotFindResourceException;
import br.com.devtops.microservice.article.exceptions.DuplicatedDataException;
import br.com.devtops.microservice.article.exceptions.protocols.CustomHttpException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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
            throw new CustomHttpException(new DuplicatedDataException("Title already in use!"), HttpStatus.CONFLICT);

        return repository.save(articleDTO.convertToArticle());
    }

    public List<Article> getAll() {
        return repository.findAll();
    }

    public Article getById(String id) {
        Optional<Article> optionalArticle = repository.findById(id);
        if(optionalArticle.isEmpty())
            throw new CustomHttpException(new CannotFindResourceException("Article not found!"), HttpStatus.NOT_FOUND);

        return optionalArticle.get();
    }

    public Article update(String id, UpdateArticleDTO dto) {
        Optional<Article> optionalArticle = repository.findById(id);
        if(optionalArticle.isEmpty())
            throw new CustomHttpException(new CannotFindResourceException("Article not found!"), HttpStatus.NOT_FOUND);

        if(dto.getTitle() != null) {
            boolean isNameInUse = repository.findByTitle(dto.getTitle())
                    .stream()
                    .anyMatch(articleToCheck -> Objects.equals(articleToCheck.getTitle(), dto.getTitle()));
            if(isNameInUse)
                throw new CustomHttpException(new DuplicatedDataException("Title already in use!"), HttpStatus.CONFLICT);
        }

        dto.merge(optionalArticle.get());

        return repository.save(optionalArticle.get());
    }

    public void delete(String id) {
        Optional<Article> optionalArticle = repository.findById(id);
        if(optionalArticle.isEmpty())
            throw new CustomHttpException(new CannotFindResourceException("Article not found!"), HttpStatus.NOT_FOUND);

        repository.deleteById(id);
    }
}
