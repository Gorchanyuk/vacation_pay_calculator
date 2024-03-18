package neoflex.gorchanyuk.vacation_pay_calculator.services.impl;

import de.jollyday.HolidayCalendar;
import de.jollyday.HolidayManager;
import de.jollyday.ManagerParameters;
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
        vacationPay.setVacationPay(Math.round(result * 100) / 100.0);// Задаем отпусные, округляя до сотых
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

        int daysVacation = getCountDaysForPay(startVacation, endVacation);
        return calculate(daysVacation, avgSalary);
    }

    /**
     * Вычисляет количество дней из заданного диапазона (включая первый и последний), которые подлежат оплате.
     * Получаем все праздничные дни Российской Федерации (в демонстрационных целях использована готовая библиотека,
     * некоторые праздничные дни могут не соответствовать действительности)
     *
     * @param startVacation дата начала отпуска
     * @param endVacation   дата окончания отпуска
     * @return количесво оплачиваемых дней
     */
    private int getCountDaysForPay(LocalDate startVacation, LocalDate endVacation) {

        HolidayManager holidayManager = HolidayManager.getInstance(ManagerParameters.create(HolidayCalendar.RUSSIA));
        LocalDate date = startVacation;
        int daysVacation = 0;

        while (!date.isAfter(endVacation)) {
            if (holidayManager.isHoliday(date)) {
                continue;
            }
            daysVacation++;
            date = date.plusDays(1);
        }
        return daysVacation;
    }
}
