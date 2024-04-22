package br.com.devtops.microservice.article.core.dtos;

import br.com.devtops.microservice.article.core.entities.Article;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateArticleDTO {
    @Size(max = 100)
    private String title;

    @Size(max = 100)
    private String subtitle;

    @Size(max = 50)
    private String objectId;

    @Size(max = 50)
    private String description;

    public void merge(Article article) {
        Map<String, String> attributesToUpdate = getAllAttributes();
        Class<?> clazz = Article.class;

        for (Map.Entry<String, String> entry : attributesToUpdate.entrySet()) {
            String attributeName = entry.getKey();
            String attributeValue = entry.getValue();

            try {
                Field field = clazz.getDeclaredField(attributeName);
                field.setAccessible(true);
                field.set(article, attributeValue);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public Map<String, String> getAllAttributes() {
        Map<String, String> attributes = new HashMap<>();
        Class<?> clazz = this.getClass();

        try {
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);
                Object value = field.get(this);
                if (value != null) {
                    attributes.put(field.getName(), value.toString());
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return attributes;
    }
}
