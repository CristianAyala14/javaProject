package primerspringboot.com.gestiondepedidos.exception;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolationException;
import primerspringboot.com.gestiondepedidos.dtos.ErrorDTO;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Maneja errores de validación de los @RequestBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> handleValidationErrors(
            MethodArgumentNotValidException ex) {

        List<String> detalles = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error ->
                        error.getField() + ": " + error.getDefaultMessage())
                .toList();

        ErrorDTO errorDTO = ErrorDTO.of(
                HttpStatus.BAD_REQUEST.value(),
                "Error de validación",
                detalles
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorDTO);
    }

    // Maneja errores de ConstraintViolation
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDTO> handleConstraintViolation(
            ConstraintViolationException ex) {

        List<String> detalles = ex.getConstraintViolations()
                .stream()
                .map(v ->
                        v.getPropertyPath() + ": " + v.getMessage())
                .toList();

        ErrorDTO errorDTO = ErrorDTO.of(
                HttpStatus.BAD_REQUEST.value(),
                "Error de validación",
                detalles
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorDTO);
    }
}
