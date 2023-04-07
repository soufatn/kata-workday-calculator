package org.neosoft.kata;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class WorkdayHoursCalculator {

    public static final WorkdayHoursCalculator instance = new WorkdayHoursCalculator();

    public static final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.FRENCH);

    private DateFormat timeFormatter = new SimpleDateFormat("HH:mm");
    private static final Set<Integer> weekendDays =
            new HashSet<>(Arrays.asList(Calendar.SATURDAY, Calendar.SUNDAY));

    /**
     * Constructor.
     */
    private WorkdayHoursCalculator() {
    }

    /**
     * @return Get number of working hours between two given dates.
     */
    public int calculateWorkdayHours(Date startDate, Date endDate) {
        int wokdayHours = 0;

        Calendar startCal = Calendar.getInstance();
        Calendar endCal = Calendar.getInstance();

        startCal.setTime(startDate);
        endCal.setTime(endDate);

        while (startCal.getTimeInMillis() <= endCal.getTimeInMillis()) {

            if (isWeekend(startCal)) {
                startCal.add(Calendar.DAY_OF_MONTH, 1);
                continue;
            }

            if (!isWorkingHour(startCal, endCal)) {
                startCal.add(Calendar.HOUR, 1);
                continue;
            }
            wokdayHours++;
            startCal.add(Calendar.HOUR, 1);
        }
        return wokdayHours;
    }

    /**
     * This method checks if the {#dateCal} is in the weekend days
     *
     * @return <code>true</code> if the {#startDate} is in the weekend days.
     */
    private boolean isWeekend(Calendar dateCal) {
        return weekendDays.contains(dateCal.get(Calendar.DAY_OF_WEEK));
    }

    /**
     * This method checks if the {#startCal} is in the working hour
     *
     * @return <code>true</code> if the {#startDate} is in the working hour of weekdays.
     */
    private boolean isWorkingHour(Calendar startCal, Calendar endCal) {
        Date startDayTime, endDayTime;
        Date startDate = startCal.getTime();
        Date endDate = endCal.getTime();

        Calendar startWorkdayCal = Calendar.getInstance();
        Calendar endWorkdayCal = Calendar.getInstance();

        try {
            startDayTime = timeFormatter.parse("09:00");
            endDayTime = timeFormatter.parse("18:00");
        } catch (ParseException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }

        startWorkdayCal.setTime(startDayTime);
        endWorkdayCal.setTime(endDayTime);

        startWorkdayCal.set(Calendar.YEAR, startCal.get(Calendar.YEAR));
        startWorkdayCal.set(Calendar.MONTH, startCal.get(Calendar.MONTH));
        startWorkdayCal.set(Calendar.DATE, startCal.get(Calendar.DATE));

        endWorkdayCal.set(Calendar.YEAR, startCal.get(Calendar.YEAR));
        endWorkdayCal.set(Calendar.MONTH, startCal.get(Calendar.MONTH));
        endWorkdayCal.set(Calendar.DATE, startCal.get(Calendar.DATE));

        Date startWorkdayDate = startWorkdayCal.getTime();
        Date endWorkdayDate = endWorkdayCal.getTime();

        if (startWorkdayDate.before(startDate)) {
            startWorkdayDate = startDate;
        }

        if (endWorkdayDate.after(endDate)) {
            endWorkdayDate = endDate;
        }

        Date date = startCal.getTime();

        return (date.equals(startWorkdayDate) || date.after(startWorkdayDate)) && date.before(endWorkdayDate);
    }

}
