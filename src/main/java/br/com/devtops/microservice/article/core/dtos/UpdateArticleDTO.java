package br.com.devtops.microservice.article.core.dtos;

import br.com.devtops.microservice.article.core.entities.Article;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UpdateArticleDTO {
    @Builder.Default
    @Size(max = 100)
    private Optional<String> title = Optional.empty();

    @Builder.Default
    @Size(max = 100)
    private Optional<String> subtitle = Optional.empty();

    @Builder.Default
    @Size(max = 50)
    private Optional<String> objectId = Optional.empty();

    @Builder.Default
    @Size(max = 50)
    private Optional<String> description = Optional.empty();

    public void merge(Article article) {
        article.setTitle(this.title.orElse(article.getTitle()));
        article.setSubtitle(this.subtitle.orElse(article.getSubtitle()));
        article.setObjectId(this.objectId.orElse(article.getObjectId()));
        article.setDescription(this.description.orElse(article.getDescription()));
    }
}
