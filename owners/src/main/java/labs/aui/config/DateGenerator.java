package labs.aui.config;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;


public class DateGenerator {

    private static final LocalDate END_DATE_EXCLUSIVE = LocalDate.now();

    public static Date generateRandomDate() {

        LocalDate START_DATE_INCLUSIVE;

        START_DATE_INCLUSIVE = LocalDate.now().minusYears(100);

        long startDay = START_DATE_INCLUSIVE.toEpochDay();
        long endDay = END_DATE_EXCLUSIVE.toEpochDay();

        long randomDay = ThreadLocalRandom.current().nextLong(startDay, endDay);

        LocalDate randomLocalDate = LocalDate.ofEpochDay(randomDay);

        return Date.from(
                randomLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant()
        );
    }
}
