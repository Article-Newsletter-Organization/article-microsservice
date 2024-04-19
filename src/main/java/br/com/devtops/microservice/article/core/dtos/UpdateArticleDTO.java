package br.com.devtops.microservice.article.core.dtos;

import br.com.devtops.microservice.article.core.entities.Article;
import lombok.*;

import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UpdateArticleDTO {
    @Builder.Default
    private Optional<String> title = Optional.empty();

    @Builder.Default
    private Optional<String> subtitle = Optional.empty();

    @Builder.Default
    private Optional<String> objectId = Optional.empty();

    @Builder.Default
    private Optional<String> description = Optional.empty();

    public void merge(Article article) {
        article.setTitle(this.title.orElse(article.getTitle()));
        article.setSubtitle(this.subtitle.orElse(article.getSubtitle()));
        article.setObjectId(this.objectId.orElse(article.getObjectId()));
        article.setDescription(this.description.orElse(article.getDescription()));
    }
}
