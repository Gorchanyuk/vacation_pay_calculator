package neoflex.gorchanyuk.vacation_pay_calculator.services.impl;

import lombok.RequiredArgsConstructor;
import neoflex.gorchanyuk.vacation_pay_calculator.models.VacationPay;
import neoflex.gorchanyuk.vacation_pay_calculator.services.VacationPayService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * Базовый класс для расчета отпускных
 */
@Service
@RequiredArgsConstructor
public class VacationPayServiceImpl implements VacationPayService {

    /**
     * Среднее количество дней в месяце
     */
    private static final double AVG_DAYS = 29.3;

    private final CounterDays counterDays;

    /**
     * Вычисляет размер отпускных по количеству дней отпуска
     *
     * @param days      количество дней отпуска
     * @param avgSalary средняя зарплата сотрудника
     * @return {@link VacationPay} размер отпускных
     */
    @Override
    public VacationPay calculate(int days, double avgSalary) {

        VacationPay vacationPay = new VacationPay();

        double result = avgSalary / AVG_DAYS * days;
        vacationPay.setVacationPay(Math.round(result * 100) / 100.0);   // Задаем отпусные, округляя до сотых
        return vacationPay;
    }

    /**
     * Вычисляет размер отпускных по датам начала и окончания отпуска
     *
     * @param startVacation дата начала отпуска
     * @param endVacation   дата окончания отпуска
     * @param avgSalary     средняя зарплата сотрудника
     * @return {@link VacationPay} размер отпускных
     */
    @Override
    public VacationPay calculate(LocalDate startVacation, LocalDate endVacation, double avgSalary) {

        int daysVacation = counterDays.getCountDaysForPay(startVacation, endVacation);
        return calculate(daysVacation, avgSalary);
    }
}

