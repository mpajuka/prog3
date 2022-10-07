import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;

public class Dates {
    public static class DateDiff {
        private final String start;
        private final String end;
        private final int diff;
        private DateDiff(String start, String end, int diff) {
            this.start = start;
            this.end = end;
            this.diff = diff;
        }
        public String getStart() {
            return this.start;
        }
        public String getEnd() {
            return this.end;
        }
        public int getDiff() {
            return this.diff;
        }
        @Override
        public String toString() {
            DateTimeFormatter finnishFormat = DateTimeFormatter.
                    ofPattern("dd.MM.yyyy");
            LocalDate startDay = LocalDate.parse(start);
            LocalDate endDay = LocalDate.parse(end);

            String startDate = startDay.format(finnishFormat);
            String endDate = endDay.format(finnishFormat);

            String startDayOfWeek = startDay.getDayOfWeek().toString();
            String sWeekDay = startDayOfWeek.substring(0, 1).toUpperCase() +
                    startDayOfWeek.substring(1).toLowerCase();

            String endDayOfWeek = endDay.getDayOfWeek().toString();
            String eWeekDay = endDayOfWeek.substring(0, 1).toUpperCase() +
                    endDayOfWeek.substring(1).toLowerCase();

            return String.format("%s %s --> %s %s: %d days",
                    sWeekDay, startDate, eWeekDay, endDate, diff);
        }
    }
    public static DateDiff[] dateDiffs(String... dateStrs) {
        if (dateStrs.length < 2) {
            return new DateDiff[0];
        }

        DateTimeFormatter formatter = DateTimeFormatter.
                ofPattern("[d.M.uuuu][uuuu-MM-dd]")
                .withResolverStyle(ResolverStyle.STRICT);

        ArrayList<LocalDate> dates = new ArrayList<>();


        for (String dateStr : dateStrs) {
            try {
                LocalDate date = LocalDate.parse(dateStr, formatter);
                dates.add(date);
            } catch (DateTimeParseException e) {
                System.out.format("The date " + """
                        "%s" """ + " is illegal!%n", dateStr);
            }
        }

        Collections.sort(dates);
        
        DateDiff[] DateDiffArray = new DateDiff[dates.size() - 1];
        
        for (int i = 0; i < dates.size(); i++) {
            if (i + 1 < dates.size()) {
                int diff = Math.toIntExact(dates.get(i).until(dates.get(i + 1),
                        ChronoUnit.DAYS));
                String start = dates.get(i).toString();
                String end = dates.get(i + 1).toString();
                DateDiffArray[i] = new DateDiff(start, end, diff);
            }
        }
        return DateDiffArray;
    }
}
