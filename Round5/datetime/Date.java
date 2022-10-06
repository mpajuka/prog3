
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Date {
    private int year;
    private int month;
    private int day;
    public Date (int year, int month, int day)
            throws DateException {
        String date = String.format("%d%02d%02d", year, month, day);
        try {
            LocalDate.parse(date, DateTimeFormatter.BASIC_ISO_DATE);
        } catch (DateTimeParseException e) {
            throw new DateException(String.format("Illegal date %02d.%02d.%d",
                    day, month, year));
        }
        this.year = year;
        this.month = month;
        this.day = day;
    }
    public int getDay() {
        return day;
    }
    public int getMonth() {
        return month;
    }
    public int getYear() {
        return year;
    }

    public String toString() {
        return String.format("%02d.%02d.%d", day, month, year);
    }
}