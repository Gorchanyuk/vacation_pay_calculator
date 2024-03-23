package neoflex.gorchanyuk.vacation_pay_calculator.services.impl;

import de.jollyday.HolidayManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CounterDaysTest {

    private CounterDays counterDays;
    private HolidayManager holidayManager;

    @BeforeEach
    void setUp() {
        holidayManager = Mockito.mock(HolidayManager.class);
        counterDays = new CounterDays(holidayManager);
    }

    @Test
    @DisplayName("Тестированиевычисления при когда все дни рабочие")
    void testGetCountDaysForPayNoHolidays() {
        LocalDate startVacation = LocalDate.of(2024, 1, 1);
        LocalDate endVacation = LocalDate.of(2024, 1, 5);

        when(holidayManager.isHoliday(Mockito.any(LocalDate.class))).thenReturn(false);

        int result = counterDays.getCountDaysForPay(startVacation, endVacation);
        assertEquals(5, result, "Все дни должны быть подсчитаны, так как нет праздничных");
    }

    @Test
    @DisplayName("Тестирование вычислени с праздничными днями")
    void testGetCountDaysForPayWithHolidays() {
        LocalDate startVacation = LocalDate.of(2024, 1, 1);
        LocalDate endVacation = LocalDate.of(2024, 1, 5);


        Mockito.doReturn(false).when(holidayManager).isHoliday(Mockito.any(LocalDate.class));
        Mockito.doReturn(true).when(holidayManager).isHoliday(LocalDate.of(2024, 1, 1));
        Mockito.doReturn(true).when(holidayManager).isHoliday(LocalDate.of(2024, 1, 5));


        int result = counterDays.getCountDaysForPay(startVacation, endVacation);
        assertEquals(3, result, "Дни с праздниками не должны учитываться");
    }

    @Test
    @DisplayName("Тестирование вычисления при длине отпуска в 1 день")
    void testGetCountDaysForPay_SingleDay() {
        LocalDate startVacation = LocalDate.of(2024, 1, 10);
        LocalDate endVacation = LocalDate.of(2024, 1, 10);

        when(holidayManager.isHoliday(LocalDate.of(2024, 1, 10))).thenReturn(false);

        int result = counterDays.getCountDaysForPay(startVacation, endVacation);
        assertEquals(1, result, "Для одного дня должно быть подсчитано 1 день");
    }
}
