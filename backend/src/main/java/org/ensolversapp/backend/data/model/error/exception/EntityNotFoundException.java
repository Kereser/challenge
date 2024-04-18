package org.ensolversapp.backend.data.model.error.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@Getter
public class EntityNotFoundException extends RuntimeException {

    private String fieldName;
    private String entityName;

    public EntityNotFoundException(String entityName, String fieldName) {
        super(String.format("Entity: %s was not found", entityName));
        this.entityName = entityName;
        this.fieldName = fieldName;
    }

    public HttpStatusCode getStatusCode() { return HttpStatus.NOT_FOUND;}
}
