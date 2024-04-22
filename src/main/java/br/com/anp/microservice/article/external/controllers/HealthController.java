package br.com.anp.microservice.article.external.controllers;

import br.com.anp.microservice.article.core.entities.HealthData;
import br.com.anp.microservice.article.external.protocols.CustomResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/health")
public class HealthController {

    @GetMapping
    public CustomResponse<HealthData> get() {
        HealthData data = HealthData.builder().status("GOOD").active(true).build();

        return new CustomResponse<>(data, HttpStatus.OK);
    }
}
