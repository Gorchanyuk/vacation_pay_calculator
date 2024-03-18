package neoflex.gorchanyuk.vacation_pay_calculator.service;

import neoflex.gorchanyuk.vacation_pay_calculator.exception.WrongInputParameterAverageSalaryException;
import neoflex.gorchanyuk.vacation_pay_calculator.models.VacationPay;
import neoflex.gorchanyuk.vacation_pay_calculator.props.WageProperty;
import neoflex.gorchanyuk.vacation_pay_calculator.services.impl.VacationPayServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {VacationPayServiceImpl.class})
public class VacationPayServiceImplTestqw {

    @Autowired
    private VacationPayServiceImpl vacationPayService;

    @MockBean
    private WageProperty wageProperty;

    @Test
    @DisplayName("Тестирование вычисления отпускных по количеству дней")
    void testCalculateByDays() {
        when(wageProperty.getMinWage()).thenReturn(1000.0);
        VacationPay result = vacationPayService.calculate(10, 1000.0);
        assertEquals(341.3, result.getVacationPay(), "Размер отпускных должен быть 341.3");
    }

    @Test
    @DisplayName("Тестирование вычисления отпускных с исключением при недопустимой зарплате")
    void testCalculateByDaysWithException() {
        when(wageProperty.getMinWage()).thenReturn(1000.0);
        assertThrows(WrongInputParameterAverageSalaryException.class, () -> vacationPayService.calculate(10, 500.0));
    }

    @Test
    @DisplayName("Тестирование вычисления отпускных по датам")
    void testCalculateByDates() {
        when(wageProperty.getMinWage()).thenReturn(1000.0);
        VacationPay result = vacationPayService.calculate(LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 10), 1000.0);
        assertEquals(300.0, result.getVacationPay(), "Размер отпускных должен быть 300.0");
    }

    @Test
    @DisplayName("Тестирование вычисления отпускных с исключением при недопустимых датах")
    void testCalculateByDatesWithException() {
        when(wageProperty.getMinWage()).thenReturn(1000.0);
        assertThrows(IllegalArgumentException.class, () -> vacationPayService.calculate(LocalDate.of(2023, 1, 10), LocalDate.of(2023, 1, 1), 1000.0));
    }
}