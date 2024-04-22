package br.com.anp.microservice.article.core.dtos;


import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FiltersArticleDTO {
    @Builder.Default
    @Size(max = 50)
    private Optional<String> id = Optional.empty();

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

    @Builder.Default
    private Optional<String> postedAt = Optional.empty();
}
