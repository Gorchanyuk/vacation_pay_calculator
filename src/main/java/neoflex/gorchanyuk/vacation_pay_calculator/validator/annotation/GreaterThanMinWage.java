package neoflex.gorchanyuk.vacation_pay_calculator.validator.annotation;

import neoflex.gorchanyuk.vacation_pay_calculator.validator.AvgSalaryValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AvgSalaryValidator.class)
public @interface GreaterThanMinWage {
    String message() default "Зарплата должна быть больше значения МРОТ";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}