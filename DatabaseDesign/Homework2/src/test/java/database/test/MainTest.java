package database.test;


import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;

import static org.junit.Assert.assertEquals;


public class MainTest {
    @Test
    void testDateEquality() {
        LocalDateTime dateTime1 = YearMonth.from(LocalDateTime.now()).atDay(1).atStartOfDay();
        LocalDateTime dateTime2 = YearMonth.from(LocalDateTime.now()).atDay(1).atStartOfDay();

        assertEquals(dateTime1, dateTime2);

    }
}
