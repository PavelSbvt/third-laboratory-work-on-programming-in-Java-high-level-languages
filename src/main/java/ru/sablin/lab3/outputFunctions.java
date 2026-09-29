package ru.sablin.lab3;

import org.fusesource.jansi.AnsiConsole;
import static org.fusesource.jansi.Ansi.*;


public class outputFunctions {
    static {
        AnsiConsole.systemInstall();
    }

    public static void simpleLog(String message) {
        System.out.println(ansi().fgBrightCyan().a("[INFO] ").reset().a(message));
    }

    public static void warningLog(String message) {
        System.out.println(ansi().fgYellow().a("[WARN] ВНИМАНИЕ: ").reset().a(message));
    }

    public static void debugLog(String message) {
        System.out.println(ansi().fgBrightBlack().a("[Debug] ").reset().a(message));
    }
}
