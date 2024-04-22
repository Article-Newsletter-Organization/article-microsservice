package br.com.anp.microservice.article.core.entities;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(access = AccessLevel.PUBLIC)
public class HealthData {

    private String status;

    private Boolean active;
}
