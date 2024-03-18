package neoflex.gorchanyuk.vacation_pay_calculator.validator.annotation;

import neoflex.gorchanyuk.vacation_pay_calculator.validator.ConsistentDateParameterValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ConsistentDateParameterValidator.class)
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ConsistentDateParameters {
    String message() default "Дата окончания отпуска должна быть больше даты начала";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}