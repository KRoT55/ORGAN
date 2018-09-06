package goncharenko.organizer.database_class.event;

import goncharenko.organizer.LocalDateStringConverter;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EventDate {
    private LocalDate beginDate;
    private LocalDate endDate;
//    private DateTimeFormatter dateFormat;

    public EventDate(String beginDate, String endDate) {
//        dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.beginDate = LocalDateStringConverter.toLocalDate(beginDate);
        this.endDate = LocalDateStringConverter.toLocalDate(endDate);
    }

    public EventDate(LocalDate beginDate, LocalDate endDate){
        this.beginDate=beginDate;
        this.endDate=endDate;
    }

    @Override
    public int hashCode() {
        return beginDate.hashCode() + endDate.hashCode();
    }

    public LocalDate getBeginDate() {
        return this.beginDate;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }

    @Override
    public String toString() {
        return beginDate + "\n" + endDate;
    }

    public int compareTo(EventDate eventDateObjectToCompare) {
        return beginDate.compareTo(eventDateObjectToCompare.getBeginDate());
    }
}
