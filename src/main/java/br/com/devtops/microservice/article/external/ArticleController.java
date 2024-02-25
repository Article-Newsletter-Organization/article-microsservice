package br.com.devtops.microservice.article.external;

import br.com.devtops.microservice.article.core.dtos.CreateArticleDTO;
import br.com.devtops.microservice.article.core.usecases.ArticleService;
import br.com.devtops.microservice.article.core.entities.Article;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/article")
public class ArticleController {

    private ArticleService service;

    @GetMapping
    public ResponseEntity<List<Article>> getAllPosts(){
        return  new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Article> getPostById(@PathVariable("id") Long id){
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Article> createPost(@RequestBody() CreateArticleDTO article){
        return new ResponseEntity<>(service.create(article), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Article> updatePost(@PathVariable("id") Long id, @RequestBody() Article article){
        article.setId(id);
        return new ResponseEntity<>(service.update(article), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable("id") Long id){
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
