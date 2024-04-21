package br.com.devtops.microservice.article.core.dtos;

import br.com.devtops.microservice.article.core.entities.Article;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Date;

@AllArgsConstructor
@Data
public class CreateArticleDTO {

    @NotNull()
    @Size(max = 100)
    private String title;

    @NotNull
    @Size(max = 100)
    private String subtitle;

    @NotNull
    private String objectId;

    @NotNull
    @Size(max = 50)
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
