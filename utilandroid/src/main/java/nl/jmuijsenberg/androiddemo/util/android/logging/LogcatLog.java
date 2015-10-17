package nl.jmuijsenberg.androiddemo.util.android.logging;

import android.util.Log;

import nl.jmuijsenberg.androiddemo.util.java.logging.LogListener;

public class LogcatLog implements LogListener {
    @Override
    public void d(String tag, String... msg) {
        Log.d(tag, format(msg));
    }

    @Override
    public void e(String tag, Throwable tr, String... msg) {
        Log.e(tag, format(msg), tr);
    }

    @Override
    public void i(String tag, String... msg) {
        Log.i(tag, format(msg));
    }

    @Override
    public void v(String tag, String... msg) {
        Log.v(tag, format(msg));
    }

    @Override
    public void w(String tag, String... msg) {
        Log.w(tag, format(msg));
    }

    private String format(String... msg) {
        StringBuilder builder = new StringBuilder();
        for (String arg : msg) {
            builder.append(arg);
        }
        return builder.toString();
    }
}
