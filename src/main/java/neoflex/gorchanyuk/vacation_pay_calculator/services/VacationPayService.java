package neoflex.gorchanyuk.vacation_pay_calculator.services;

import neoflex.gorchanyuk.vacation_pay_calculator.models.VacationPay;

import java.time.LocalDate;

public interface VacationPayService {

    VacationPay calculate(int days, double avgSalary);

    VacationPay calculate(LocalDate startVacation, LocalDate endVacation, double avgSalary);
}
