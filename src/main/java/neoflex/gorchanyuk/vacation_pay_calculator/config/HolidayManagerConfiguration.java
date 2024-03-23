package neoflex.gorchanyuk.vacation_pay_calculator.config;

import de.jollyday.HolidayCalendar;
import de.jollyday.HolidayManager;
import de.jollyday.ManagerParameters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HolidayManagerConfiguration {

    @Bean
    public HolidayManager getHolidayManager(){
        return HolidayManager.getInstance(ManagerParameters.create(HolidayCalendar.RUSSIA));
    }
}
