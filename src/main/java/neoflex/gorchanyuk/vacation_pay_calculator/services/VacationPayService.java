package neoflex.gorchanyuk.vacation_pay_calculator.services;

import de.jollyday.HolidayCalendar;
import de.jollyday.HolidayManager;
import neoflex.gorchanyuk.vacation_pay_calculator.models.VacationPay;
import neoflex.gorchanyuk.vacation_pay_calculator.util.WrongInputParameterAverageSalaryException;
import neoflex.gorchanyuk.vacation_pay_calculator.util.WrongInputParameterDaysException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
public class VacationPayService {

    private static final double MINIMUM_WAGE = 15279;// Размер МРОТ (средняя зарплата не может быть ниже МРОТ)
    private static final double AVG_DAYS = 29.3;// Среднее количество дней в месяце

    public VacationPay calculate(int days, double avgSalary) {

        //Проверяем правильность полученных данных для зарплаты и средней зарплаты
        if (days < 1) {
            throw new WrongInputParameterDaysException("Количество дней в отпуске должно быть больше 0");
        } else if (avgSalary < MINIMUM_WAGE) {
            throw new WrongInputParameterAverageSalaryException(
                    "Средняя зарплата не может быть ниже минимально размера оплаты труда (" + MINIMUM_WAGE + ")");
        }
        VacationPay vacationPay = new VacationPay();
        //Вычисляем отпускные
        double result = avgSalary / AVG_DAYS * days;
        vacationPay.setVacationPay(Math.round(result * 100) / 100.0);// Задаем отпусные, округляя до сотых
        return vacationPay;
    }


    public VacationPay calculate(LocalDate startVacation, LocalDate endVacation, double avgSalary) {

        //Проверяем правильность полученных дат отпуска, при необходимости меняем местами
        if (startVacation.isAfter((endVacation))) {
            LocalDate temp = endVacation;
            endVacation = startVacation;
            startVacation = temp;
        }
        int daysVacation = getCountDaysForPay(startVacation, endVacation);
        return calculate(daysVacation, avgSalary);
    }


    private int getCountDaysForPay(LocalDate startVacation, LocalDate endVacation) {
        //Высчитывает количество дней из заданного диапазона, которые подлежат оплате

        //Получаем все праздничные дни Российской Федерации
        // (в демонстрационных целях использована готовая библиотека,
        // некоторые праздничные дни могут не соответствовать действительности)
        HolidayManager holidayManager = HolidayManager.getInstance(HolidayCalendar.RUSSIA);
        LocalDate date = startVacation;
        int daysVacation = 0;

        while (date.compareTo(endVacation) <= 0) {
            //Проходимся по всем дням в выбранном диапазоне, включая первый и последний
            if (!holidayManager.isHoliday(date))
                // Проверяем является ли день праздничным, если нет, то прибавляем день к отпуску
                daysVacation++;
            date = date.plusDays(1);
        }
        return daysVacation;
    }
}
