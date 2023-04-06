package org.neosoft.kata;

import java.util.*;

public class WorkdayHoursCalculator {

    public static final WorkdayHoursCalculator instance = new WorkdayHoursCalculator();

    private static final Set<Integer> weekendDays =
            new HashSet<>(Arrays.asList(Calendar.SATURDAY, Calendar.SUNDAY));

    /**
     * Constructor.
     */
    private WorkdayHoursCalculator() {

    }

    public int calculateWorkdayHours(Date startDate, Date endDate) {
        return 0;
    }
}
