package br.com.devtops.microservice.article.external.controllers;

import br.com.devtops.microservice.article.core.dtos.CreateArticleDTO;
import br.com.devtops.microservice.article.core.dtos.UpdateArticleDTO;
import br.com.devtops.microservice.article.core.usecases.ArticleService;
import br.com.devtops.microservice.article.core.entities.Article;
import br.com.devtops.microservice.article.external.protocols.CustomResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/article")
public class ArticleController {

    private ArticleService service;

    @GetMapping
    public CustomResponse<List<Article>> getAllArticles(){
        return  new CustomResponse<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public CustomResponse<Article> getArticleById(@PathVariable("id") String id){
        return new CustomResponse<>(service.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public CustomResponse<Article> createArticle(@RequestBody() CreateArticleDTO article){
        return new CustomResponse<>(service.create(article), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public CustomResponse<Article> updateArticle(@PathVariable("id") String id, @RequestBody() UpdateArticleDTO dto){
        return new CustomResponse<>(service.update(id, dto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public CustomResponse<Void> deleteArticle(@PathVariable("id") String id){
        service.delete(id);
        return new CustomResponse<>(HttpStatus.NO_CONTENT);
    }
}
