package neoflex.gorchanyuk.vacation_pay_calculator.services.impl;

import de.jollyday.HolidayManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
class CounterDays {

    private final HolidayManager holidayManager;

    /**
     * Вычисляет количество дней из заданного диапазона (включая первый и последний), которые подлежат оплате.
     * Получаем все праздничные дни Российской Федерации (в демонстрационных целях использована готовая библиотека,
     * некоторые праздничные дни могут не соответствовать действительности)
     *
     * @param startVacation дата начала отпуска
     * @param endVacation   дата окончания отпуска
     * @return количесво оплачиваемых дней
     */
    int getCountDaysForPay(LocalDate startVacation, LocalDate endVacation) {

        LocalDate date = startVacation;
        int daysVacation = 0;

        while (!date.isAfter(endVacation)) {
            if (!holidayManager.isHoliday(date)) {
                daysVacation++;
            }
            date = date.plusDays(1);
        }
        return daysVacation;
    }
}