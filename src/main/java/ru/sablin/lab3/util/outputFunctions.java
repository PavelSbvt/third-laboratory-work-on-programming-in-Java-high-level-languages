package ru.sablin.lab3.util;

import org.fusesource.jansi.AnsiConsole;

import static org.fusesource.jansi.Ansi.*;


public class outputFunctions {
    static {
        AnsiConsole.systemInstall();
    }

    public static String getTime() {
        final short MILLISECONDS_PER_SECOND = 1000;
        final byte SECONDS_PER_MINUTE = 60;
        final byte MINUTES_PER_HOUR = 60;
        final byte HOURS_PER_DAY = 24;

        long currentHour;
        long currentMinute;
        long currentSecond;

        long totalSeconds;
        long totalMinutes;
        long totalHours;

        long totalMilliseconds = System.currentTimeMillis();

        totalSeconds = totalMilliseconds / MILLISECONDS_PER_SECOND;
        currentSecond = totalSeconds % SECONDS_PER_MINUTE;

        totalMinutes = totalSeconds / SECONDS_PER_MINUTE;
        currentMinute = totalMinutes % MINUTES_PER_HOUR;

        totalHours = totalMinutes / MINUTES_PER_HOUR;
        totalHours += 3;
        currentHour = totalHours % HOURS_PER_DAY;

        String helpSeconds = ""; // Вспомогательная строка для подставленеия 0 перед секундами, если значение секунд менее 10

        if (currentSecond < 10){
            helpSeconds = "0";
        }

        String helpMinutes = ""; // Вспомогательная строка для подставленеия 0 перед минутами, если значение минут менее 10

        if (currentMinute < 10){
            helpMinutes = "0";
        }

        String helpHours = ""; // Вспомогательная строка для подставленеия 0 перед часами выбранного часового пояса, если значение часов менее 10

        if (currentHour < 10){
            helpHours = "0";
        }

        String outputStringTime = String.format("[%s%d:%s%d:%s%d]",
                helpHours, currentHour,helpMinutes, currentMinute, helpSeconds, currentSecond);

//        System.out.println(outputStringTime);
        return outputStringTime;
    }

    public static void simpleLog(String message) {
        String outString = getTime() + " [INFO] " + message;
        System.out.println(ansi().fgBrightCyan().a(outString).reset());
    }

    public static void warningLog(String message) {
        String outString = getTime() + " [WARNING] " + message;
        System.out.println(ansi().fgYellow().a(outString).reset());
    }

    public static void debugLog(String message) {
        String outString = getTime() + " [DEBUG] " + message;
        System.out.println(ansi().fgBrightBlack().a(outString).reset());
    }
}
