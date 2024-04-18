package org.ensolversapp.backend.data.model.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatusCode;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
public class ApiErrorMessages {

    private Date timestamp;
    private Integer code;
    private HttpStatusCode statusCode;
    private List<ApiErrors> errors;

    public ApiErrorMessages(HttpStatusCode statusCode, List<ApiErrors> errors, Integer code) {
        this.statusCode = statusCode;
        this.errors = new ArrayList<>(errors);
        this.code = code;
        this.timestamp = new Date();
    }

    @Getter
    @AllArgsConstructor
    public static class ApiErrors {
        private String fieldName;
        private String message;
    }
}