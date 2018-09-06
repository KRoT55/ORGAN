package goncharenko.organizer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateStringConverter {
    private static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    public static String toString(LocalDate date){
        return date.format(dateFormat);
    }

    public static LocalDate toLocalDate(String dateString){
        return LocalDate.parse(dateString,dateFormat);
    }
}
