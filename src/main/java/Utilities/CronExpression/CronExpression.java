package Utilities.CronExpression;

import java.time.LocalDate;

public class CronExpression {
    public String getCronExpression(LocalDate date){
        return "00 00 00 " + date.getDayOfMonth() + " " + date.getMonth().getValue() + " ? " + date.getYear();
    }
}
