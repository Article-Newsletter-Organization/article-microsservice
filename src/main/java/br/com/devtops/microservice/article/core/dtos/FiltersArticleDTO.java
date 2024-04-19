package br.com.devtops.microservice.article.core.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FiltersArticleDTO {
    private Optional<String> id = Optional.empty();

    private Optional<String> title = Optional.empty();

    private Optional<String> subtitle = Optional.empty();

    private Optional<String> objectId = Optional.empty();

    private Optional<String> description = Optional.empty();

    private Optional<Date> postedAt = Optional.empty();
}
