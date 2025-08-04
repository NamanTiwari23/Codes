public class CustomCalendar {

    public static void main(String[] args) {
        // Define the calendar parameters
        int daysInYear = 365; // Total days in a year
        int daysInMonth = 30; // Total days in a month
        int daysInWeek = 7;   // Total days in a week

        // Validate calendar configuration
        String configMessage = validateCalendar(daysInYear, daysInMonth, daysInWeek);
        System.out.println(configMessage);

        // Validate a specific date
        String dateString = "2023-05-15";
        String[] dateParts = dateString.split("-");
        int year = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]);
        int day = Integer.parseInt(dateParts[2]);

        String dateMessage = validateDate(year, month, day, daysInMonth);
        System.out.println(dateMessage);

        // Calculate the day of the week if the date is valid
        if (dateMessage.equals("Valid date.")) {
            String dayOfWeek = calculateDayOfWeek(year, month, day, daysInMonth, daysInWeek);
            System.out.println("The day of the week for " + dateString + " is: " + dayOfWeek);
        }
    }

    public static String validateCalendar(int daysInYear, int daysInMonth, int daysInWeek) {
        if (daysInYear <= 0 || daysInMonth <= 0 || daysInWeek <= 0) {
            return "Days must be positive.";
        }

        if (daysInYear > 99 || daysInMonth > 99 || daysInWeek > 99) {
            return "Values must not exceed two digits.";
        }

        int monthsInYear = daysInYear / daysInMonth;
        if (daysInYear % daysInMonth != 0) {
            return "Days in year must be evenly divisible by days in month.";
        }

        if (daysInWeek > daysInMonth) {
            return "Days in week can't exceed days in month.";
        }

        return "Valid calendar configuration.";
    }

    public static String validateDate(int year, int month, int day, int daysInMonth) {
        if (year <= 0) {
            return "Year must be positive.";
        }

        int monthsInYear = 365 / daysInMonth; // Adjust based on your configuration
        if (month < 1 || month > monthsInYear) {
            return "Month is out of range.";
        }

        if (day < 1 || day > daysInMonth) {
            return "Day is out of range.";
        }

        return "Valid date.";
    }

    public static String calculateDayOfWeek(int year, int month, int day, int daysInMonth, int daysInWeek) {
        int totalDays = (year - 1) * (365) + (month - 1) * (daysInMonth) + (day - 1);
        int dayOfWeekIndex = totalDays % daysInWeek;

        // Assuming days are labeled A, B, C, ..., based on daysInWeek
        char dayOfWeek = (char) ('A' + dayOfWeekIndex);
        return String.valueOf(dayOfWeek);
    }
}
