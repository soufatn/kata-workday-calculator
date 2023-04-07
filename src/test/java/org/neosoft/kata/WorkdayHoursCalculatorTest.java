package org.neosoft.kata;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

public class WorkdayHoursCalculatorTest {

    @Test
    void testCalculateWorkdayHoursTest() throws ParseException {
        SimpleDateFormat formatter = WorkdayHoursCalculator.dateFormatter;
        final Date startDate = formatter.parse("2023-03-31T13:00:00Z");
        final Date endDate = formatter.parse("2023-04-04T12:00:00Z");

        assertEquals(17, WorkdayHoursCalculator.instance.calculateWorkdayHours(startDate, endDate));
    }

}
