package nl.han.se.pizzanu.pizzas;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = { Exception.class})
    protected ResponseEntity<Object> handleException (
            RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, "Pizza.nu could not perform the requested method: " + ex.getMessage(),
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
