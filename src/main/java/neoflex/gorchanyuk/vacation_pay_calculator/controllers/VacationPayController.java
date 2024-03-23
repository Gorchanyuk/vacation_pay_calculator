package neoflex.gorchanyuk.vacation_pay_calculator.controllers;

import lombok.RequiredArgsConstructor;
import neoflex.gorchanyuk.vacation_pay_calculator.controllers.api.VacationPayAPI;
import neoflex.gorchanyuk.vacation_pay_calculator.models.VacationPay;
import neoflex.gorchanyuk.vacation_pay_calculator.services.VacationPayService;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
public class VacationPayController implements VacationPayAPI {

    private final VacationPayService service;

    @Override
    public VacationPay calculate(int days, double avgSalary) {

        return service.calculate(days, avgSalary);
    }

    @Override
    public VacationPay calculateByPeriod(LocalDate startVacation, LocalDate endVacation, double avgSalary) {

        return service.calculate(startVacation, endVacation, avgSalary);
    }
}
