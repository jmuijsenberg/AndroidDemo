package nl.jmuijsenberg.androiddemo.util.java.logging;

import java.util.ArrayList;
import java.util.List;

public class Log {
    private static List<LogListener> sListeners = new ArrayList<>();

    private Log() {
    }

    public static void attachListener(LogListener listener) {
        sListeners.add(listener);
    }
    public static void detachListener(LogListener listener) {
        sListeners.remove(listener);
    }

    public static void d(String tag, String... msg) {
        for (LogListener l : sListeners)
        {
            l.d(tag, msg);
        }
     }

    public static void e(String tag, Throwable tr, String... msg) {
        for (LogListener l : sListeners)
        {
            l.e(tag, tr, msg);
        }
    }

    public static void i(String tag, String... msg) {
        for (LogListener l : sListeners)
        {
            l.i(tag, msg);
        }
    }

    public static void v(String tag, String... msg) {
        for (LogListener l : sListeners)
        {
            l.v(tag, msg);
        }
    }

    public static void w(String tag, String... msg) {
        for (LogListener l : sListeners)
        {
            l.w(tag, msg);
        }
    }
}
