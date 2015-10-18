package nl.jmuijsenberg.androiddemo.util.java.logging;

import java.util.ArrayList;
import java.util.List;

public class Logger {
    private static List<LoggerListener> sListeners = new ArrayList<>();

    private Logger() {
    }

    public static void attachListener(LoggerListener listener) {
        sListeners.add(listener);
    }
    public static void detachListener(LoggerListener listener) {
        sListeners.remove(listener);
    }

    public static void d(String tag, String... msg) {
        for (LoggerListener l : sListeners)
        {
            l.d(tag, msg);
        }
     }

    public static void e(String tag, Throwable tr, String... msg) {
        for (LoggerListener l : sListeners)
        {
            l.e(tag, tr, msg);
        }
    }

    public static void i(String tag, String... msg) {
        for (LoggerListener l : sListeners)
        {
            l.i(tag, msg);
        }
    }

    public static void v(String tag, String... msg) {
        for (LoggerListener l : sListeners)
        {
            l.v(tag, msg);
        }
    }

    public static void w(String tag, String... msg) {
        for (LoggerListener l : sListeners)
        {
            l.w(tag, msg);
        }
    }
}
