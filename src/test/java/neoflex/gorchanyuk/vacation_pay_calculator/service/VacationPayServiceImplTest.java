//package neoflex.gorchanyuk.vacation_pay_calculator.service;
//
//import neoflex.gorchanyuk.vacation_pay_calculator.services.VacationPayService;
//import neoflex.gorchanyuk.vacation_pay_calculator.services.impl.VacationPayServiceImpl;
//import neoflex.gorchanyuk.vacation_pay_calculator.exception.WrongInputParameterAverageSalaryException;
//import neoflex.gorchanyuk.vacation_pay_calculator.exception.WrongInputParameterDaysException;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.Random;
//
//@ContextConfiguration(classes = VacationPayServiceImpl.class)
//public class VacationPayServiceImplTestqw {
//
//
////    private static final double MINIMUM_WAGE = 15_279;
//    private static final double AVG_DAYS = 29.3;
//
//    @Autowired
//    private VacationPayServiceImpl service;
//    private static Random random;
//
//    @BeforeAll
//    public static void createVacationPayService() {
////        service = new VacationPayServiceImpl();
//        random = new Random();
//    }
//
//    @Test
//    public void calculateShouldThrowWrongInputParameterDaysException() {
//        //Проверяем работоспособность метода при отрицательных и нулевом значениях дней отпуска
//        for (int i = 0; i > -50; i--) {
//            int days = i;
//
//            Assertions.assertThrows(WrongInputParameterDaysException.class,
//                    () -> service.calculate(days, 20000),
//                    "Произошло исключение при значении days = " + days
//            );
//        }
//    }
//
////    @Test
////    @DisplayName("Должен выбросить исключение WrongInputParameterAverageSalaryException при невалиных данных")
////    public void calculateShouldThrowWrongInputParameterAverageSalaryException() {
////        //Проверяем работоспособность при отрицательном количестве дней и значениях средней зп меньше МРОТ
////        for (int i = 0; i < 1000; i++) {
////            //задаем среднюю зарплату от -50_000 до 50_000
////            double avgSalary = random.nextDouble() * 100_000 - 50_000;
////            if (avgSalary < MINIMUM_WAGE) {
////
////                Assertions.assertThrows(WrongInputParameterAverageSalaryException.class,
////                        () -> service.calculate(20, avgSalary),
////                        "Произошло исключение при значении avgSalary = " + avgSalary
////                );
////            } else {
////                Assertions.assertDoesNotThrow(() -> service.calculate(20, avgSalary),
////                        "Произошло исключение при значении avgSalary = " + avgSalary);
////            }
////        }
////    }
//
////    @Test
////    public void calculateShouldReturnResult() {
////
////        for (int i = 0; i < 1000; i++) {
////            //Задаем количество дней и среднюю зарплату, ограничивая допустимыми значениями
////            int days = random.nextInt(100) + 1;
////            double avgSalary = MINIMUM_WAGE + random.nextDouble() * 100_000;
////
////            double expectedResult = avgSalary / AVG_DAYS * days;
////            double actualResult = service.calculate(days, avgSalary).getVacationPay();
////
////            Assertions.assertEquals(expectedResult, actualResult, 10e-2);
////        }
////    }
//}
