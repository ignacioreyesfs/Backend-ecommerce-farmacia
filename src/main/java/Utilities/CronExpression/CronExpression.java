package Utilities.CronExpression;

import java.time.LocalDateTime;

public final class CronExpression {
    public static String getCronExpressionLocalDateTime(LocalDateTime dateTime){
        return dateTime.getSecond() + " " + dateTime.getMinute() + " " + dateTime.getHour() + " "
            + dateTime.getDayOfMonth() + " " + dateTime.getMonth().getValue() + " ? " + dateTime.getYear();
    }
}
