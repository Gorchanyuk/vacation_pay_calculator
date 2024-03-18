package neoflex.gorchanyuk.vacation_pay_calculator.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@Schema(description = "Модель для предоставдения размера отпускных")
public class VacationPay {

    @Schema(description = "Размер отпускных", example = "65000.00")
    private Double vacationPay;     //Размер отпускных
}
