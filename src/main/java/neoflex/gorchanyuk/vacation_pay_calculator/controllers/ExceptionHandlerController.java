package neoflex.gorchanyuk.vacation_pay_calculator.controllers;

import neoflex.gorchanyuk.vacation_pay_calculator.util.InputDataErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler
    private ResponseEntity<List<InputDataErrorResponse>> handleException(ConstraintViolationException e) {

        List<InputDataErrorResponse> response = e.getConstraintViolations()
                .stream()
                .map(constraintViolation ->
                        new InputDataErrorResponse(
                                e.getMessage(),
                                System.currentTimeMillis()
                        )
                ).collect(Collectors.toList());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<Object> handleException(IllegalArgumentException e) {

        InputDataErrorResponse response = new InputDataErrorResponse(
                "Недопустимый формат данных: " + e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
