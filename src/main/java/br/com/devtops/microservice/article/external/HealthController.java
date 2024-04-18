package br.com.devtops.microservice.article.external;

import br.com.devtops.microservice.article.core.entities.HealthData;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/health")
public class HealthController {

    @GetMapping
    public ResponseEntity<HealthData> get() {
        HealthData data = HealthData.builder().status("GOOD").active(true).build();

        return new ResponseEntity<HealthData>(data, HttpStatus.OK);
    }
}
