package br.com.devtops.microservice.article.exceptions.protocols;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.lang.Nullable;

import java.util.List;

@Getter
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(value = {"suppressed", "localizedMessage", "cause", "stackTrace"})
public class CustomException extends RuntimeException {
    final private String name;
    final private String message;
    final private @Nullable String stack;
    final private List<CustomException> issues;

    public CustomException(String name, String message, @Nullable String stack) {
        super(message);
        this.name = name;
        this.message = message;
        this.stack = stack;
        this.issues = List.of();
    }

    public CustomException(String name, String message, List<CustomException> issues) {
        super(message);
        this.name = name;
        this.message = message;
        this.stack = null;
        this.issues = issues;
    }

    public CustomException(String name, String message) {
        super(message);
        this.name = name;
        this.message = message;
        this.stack = null;
        this.issues = List.of();
    }
}
