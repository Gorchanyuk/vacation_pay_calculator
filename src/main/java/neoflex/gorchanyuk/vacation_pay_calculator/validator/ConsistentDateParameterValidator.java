package neoflex.gorchanyuk.vacation_pay_calculator.validator;

import neoflex.gorchanyuk.vacation_pay_calculator.validator.annotation.ConsistentDateParameters;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;
import java.time.LocalDate;

@SupportedValidationTarget(ValidationTarget.PARAMETERS)
public class ConsistentDateParameterValidator implements ConstraintValidator<ConsistentDateParameters, Object[]> {

    @Override
    public void initialize(ConsistentDateParameters constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object[] value, ConstraintValidatorContext context) {

        LocalDate startVacation = (LocalDate) value[0];
        LocalDate endVacation = (LocalDate) value[1];

        return endVacation.isAfter(startVacation);
    }
}