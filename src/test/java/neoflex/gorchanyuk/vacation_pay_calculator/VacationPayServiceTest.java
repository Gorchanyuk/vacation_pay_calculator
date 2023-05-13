package neoflex.gorchanyuk.vacation_pay_calculator;

import neoflex.gorchanyuk.vacation_pay_calculator.services.VacationPayService;
import neoflex.gorchanyuk.vacation_pay_calculator.util.WrongInputParameterAverageSalaryException;
import neoflex.gorchanyuk.vacation_pay_calculator.util.WrongInputParameterDaysException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Random;


public class VacationPayServiceTest {


    private static final double MINIMUM_WAGE = 15_279;
    private static final double AVG_DAYS = 29.3;
    private static VacationPayService service;
    private static Random random;

    @BeforeAll
    public static void createVacationPayService() {
        service = new VacationPayService();
        random = new Random();
    }

    @Test
    public void calculateShouldThrowWrongInputParameterDaysException() {
        //Проверяем работоспособность метода при отрицательных и нулевом значениях дней отпуска
        for (int i = 0; i > -50; i--) {
            int days = i;

            Assertions.assertThrows(WrongInputParameterDaysException.class,
                    () -> service.calculate(days, 20000),
                    "Произошло исключение при значении days = " + days
            );
        }
    }

    @Test
    public void calculateShouldThrowWrongInputParameterAverageSalaryException() {
        //Проверяем работоспособность при отрицательных и значениях меньше МРОТ
        for (int i = 0; i < 1000; i++) {
            //задаем среднюю зарплату от -50_000 до 50_000
            double avgSalary = random.nextDouble() * 100_000 - 50_000;
            if (avgSalary < MINIMUM_WAGE) {

                Assertions.assertThrows(WrongInputParameterAverageSalaryException.class,
                        () -> service.calculate(20, avgSalary),
                        "Произошло исключение при значении avgSalary = " + avgSalary
                );
            } else {
                Assertions.assertDoesNotThrow(() -> service.calculate(20, avgSalary),
                        "Произошло исключение при значении avgSalary = " + avgSalary);
            }
        }
    }

    @Test
    public void calculateShouldReturnResult() {

        for (int i = 0; i < 1000; i++) {
            //Задаем количество дней и среднюю зарплату, ограничивая допустимыми значениями
            int days = random.nextInt(100) + 1;
            double avgSalary = MINIMUM_WAGE + random.nextDouble() * 100_000;

            double expectedResult = avgSalary / AVG_DAYS * days;
            double actualResult = service.calculate(days, avgSalary).getVacationPay();

            Assertions.assertEquals(expectedResult, actualResult, 10e-2);
        }
    }
}
