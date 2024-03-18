package neoflex.gorchanyuk.vacation_pay_calculator.validator;

import lombok.RequiredArgsConstructor;
import neoflex.gorchanyuk.vacation_pay_calculator.validator.annotation.GreaterThanMinWage;
import neoflex.gorchanyuk.vacation_pay_calculator.props.WageProperty;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@RequiredArgsConstructor
public class AvgSalaryValidator implements ConstraintValidator<GreaterThanMinWage, Double> {

    private final WageProperty wageProperty;

    @Override
    public void initialize(GreaterThanMinWage constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Double avgSalary, ConstraintValidatorContext context) {

        return avgSalary > wageProperty.getMinWage();
    }
}