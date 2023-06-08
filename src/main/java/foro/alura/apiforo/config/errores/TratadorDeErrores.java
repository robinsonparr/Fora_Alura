package foro.alura.apiforo.config.errores;

import jdk.jshell.spi.ExecutionControl;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class TratadorDeErrores {
    @ExceptionHandler(value = {ExecutionControl.EngineTerminationException.class, MissingPathVariableException.class})
    public ResponseEntity tratarError404() {
        return ResponseEntity.status(HttpStatusCode.valueOf(404)).body(message404());
    }
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity tratarError400(MethodArgumentNotValidException ex) {
        var errores = ex.getFieldErrors().stream().map(datosErrorDeValidacion::new).toList();
        return ResponseEntity.badRequest().body(errores);
    }

    private record datosErrorDeValidacion(String campo, String error) {

        public datosErrorDeValidacion(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }

    }

    private Map<String, String> message404() {
        Map<String, String> data = new HashMap<>();
        data.put("message", "record not fount");
        return data;
    }
}
