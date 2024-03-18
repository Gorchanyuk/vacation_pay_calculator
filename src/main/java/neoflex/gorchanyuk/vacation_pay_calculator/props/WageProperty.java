package neoflex.gorchanyuk.vacation_pay_calculator.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "props.wage")
public class WageProperty {

    private double minWage;
}
