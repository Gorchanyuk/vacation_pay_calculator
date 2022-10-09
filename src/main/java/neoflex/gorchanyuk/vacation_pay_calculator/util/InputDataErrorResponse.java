package neoflex.gorchanyuk.vacation_pay_calculator.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Класс для представления ошибки
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InputDataErrorResponse {

    private String message;
    private long timestamp;
}
