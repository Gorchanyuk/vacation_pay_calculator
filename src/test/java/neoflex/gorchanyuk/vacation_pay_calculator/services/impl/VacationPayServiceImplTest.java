package neoflex.gorchanyuk.vacation_pay_calculator.services.impl;

import neoflex.gorchanyuk.vacation_pay_calculator.config.HolidayManagerConfiguration;
import neoflex.gorchanyuk.vacation_pay_calculator.models.VacationPay;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {VacationPayServiceImpl.class,
        HolidayManagerConfiguration.class,
        CounterDays.class})
public class VacationPayServiceImplTest {

    @Autowired
    private VacationPayServiceImpl vacationPayService;

    private final double AVG_SALARY = 20000.0;
    private final double AVG_DAYS = 29.3;

    @ParameterizedTest
    @ValueSource(ints = {5, 10, 15, 20, 30, 40, 100, 1000})
    @DisplayName("Тестирование вычисления отпускных по количеству дней")
    void testCalculateByDays(int days) {

        double expected = Math.round((AVG_SALARY / AVG_DAYS * days) * 100) / 100.0;

        VacationPay result = vacationPayService.calculate(days, AVG_SALARY);

        assertEquals(expected, result.getVacationPay(), "Ошибка при значении days = " + days);
    }

    @Test
    @DisplayName("Тестирование вычисления отпускных по датам")
    void testCalculateByDates() {

        LocalDate dateStart = LocalDate.of(2024, 1, 1);
        LocalDate dateEnd = LocalDate.of(2024, 1, 20);
        double expected = Math.round((AVG_SALARY / AVG_DAYS * 12) * 100) / 100.0;

        VacationPay result = vacationPayService.calculate(dateStart, dateEnd, AVG_SALARY);

        assertEquals(expected, result.getVacationPay());
    }
}