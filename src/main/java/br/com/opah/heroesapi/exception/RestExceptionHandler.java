package br.com.opah.heroesapi.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String NOT_FOUND = "Não encontrado";

    private ResponseEntity<Object> buildResponseEntity(ApiException apiException){
        return new ResponseEntity<>(apiException, apiException.getStatus());
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<Object> httpClientErrorExceptionHandler(HttpClientErrorException ex){
        return buildResponseEntity(new ApiException(ex.getStatusCode(), ex.getStatusText(), ex));
    }



}
