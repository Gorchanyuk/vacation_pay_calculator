package neoflex.gorchanyuk.vacation_pay_calculator.util;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Класс для представления сообщения об ошибки
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Модель для предоставления сообщения об ошибке")
public class InputDataErrorResponse {

    private String message;     //Сообщение
    private long timestamp;     //Время ошибки
}
