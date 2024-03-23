package neoflex.gorchanyuk.vacation_pay_calculator.controllers.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import neoflex.gorchanyuk.vacation_pay_calculator.models.VacationPay;
import neoflex.gorchanyuk.vacation_pay_calculator.util.InputDataErrorResponse;
import neoflex.gorchanyuk.vacation_pay_calculator.validator.annotation.ConsistentDateParameters;
import neoflex.gorchanyuk.vacation_pay_calculator.validator.annotation.GreaterThanMinWage;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Validated
@RequestMapping("/calculate")
@Tag(name = "Отпускные", description = "Контроллер для расчета отпускных")
@ApiResponses({
        @ApiResponse(responseCode = "200"),
        @ApiResponse(responseCode = "404",
                content = @Content(schema = @Schema(implementation = InputDataErrorResponse.class)))
})
public interface VacationPayAPI {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Получение значения отпускных",
            description = "Получение значения отпускных, по количеству дней и средней зарплате")
    VacationPay calculate(@Parameter(description = "Количество дней в отпуске")
                          @RequestParam(value = "days", defaultValue = "0")
                          @Min(value = 1, message = "Количество дней не может быть меньше 1")
                          int days,

                          @Parameter(description = "Средняя зарплата сотрудника")
                          @RequestParam(value = "avg_salary", defaultValue = "0")
                          @GreaterThanMinWage
                          double avgSalary);

    @ConsistentDateParameters
    @GetMapping(value = "/from_to",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Получение значения отпускных",
            description = "Получение значения отпускных, по датам начала и окончания отпуска, и средней зарплате")
    VacationPay calculateByPeriod(@Parameter(description = "Дата первого дня отпуска", example = "20/04/2024")
                                  @RequestParam(value = "startVacation")
                                  @DateTimeFormat(pattern = "dd/MM/yyyy")
                                  @NotNull(message = "Значение не может быть пустым")
                                  LocalDate startVacation,

                                  @Parameter(description = "Дата последняго дня отпуска", example = "05/05/2024")
                                  @RequestParam(value = "endVacation")
                                  @DateTimeFormat(pattern = "dd/MM/yyyy")
                                  @NotNull(message = "Значение не может быть пустым")
                                  LocalDate endVacation,

                                  @Parameter(description = "Средняя зарплата сотрудника", example = "65000.00")
                                  @RequestParam(value = "avg_salary", defaultValue = "0")
                                  @GreaterThanMinWage
                                  double avgSalary);
}
