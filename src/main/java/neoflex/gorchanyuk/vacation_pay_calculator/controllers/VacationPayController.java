package neoflex.gorchanyuk.vacation_pay_calculator.controllers;

import lombok.RequiredArgsConstructor;
import neoflex.gorchanyuk.vacation_pay_calculator.models.VacationPay;
import neoflex.gorchanyuk.vacation_pay_calculator.services.VacationPayService;
import neoflex.gorchanyuk.vacation_pay_calculator.util.InputDataErrorResponse;
import neoflex.gorchanyuk.vacation_pay_calculator.util.WrongInputDataException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/calculate")
public class VacationPayController {

    private final VacationPayService vacationPayService;

    @GetMapping()
    public VacationPay calculate(@RequestParam(value = "days", defaultValue = "0") int days,
                                 @RequestParam(value = "avg_salary", defaultValue = "0") double avgSalary) {

        return vacationPayService.calculate(days, avgSalary);
    }

    @GetMapping("/from_to")
    public VacationPay calculate2(@RequestParam(value = "startVacation")
                                  @DateTimeFormat(pattern = "dd/MM/yyyy")
                                          LocalDate startVacation,
                                  @RequestParam(value = "endVacation")
                                  @DateTimeFormat(pattern = "dd/MM/yyyy")
                                          LocalDate endVacation,
                                  @RequestParam(value = "avg_salary", defaultValue = "0") double avgSalary) {

        return vacationPayService.calculate(startVacation, endVacation, avgSalary);
    }

    @ExceptionHandler
    private ResponseEntity<InputDataErrorResponse> handleException(WrongInputDataException e) {
        //Срабатывает в случае ошибки входных данных (смотри VacationPayService)
        InputDataErrorResponse response = new InputDataErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        // Отправляем объект ошибки и статус 404
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
