package br.com.devtops.microservice.article.core.dtos;

import br.com.devtops.microservice.article.core.entities.Article;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateArticleDTO {
    private String title;

    private String subtitle;

    private String objectId;

    private String description;

    public Article convertToArticle(){
        return Article.builder()
                .title(this.getTitle())
                .subtitle(this.getSubtitle())
                .description(this.getDescription())
                .objectId(this.getObjectId())
                .postedAt(new Date())
                .build();
    }
}
