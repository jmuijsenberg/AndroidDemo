package nl.jmuijsenberg.androiddemo.util.java.logging;

public interface LoggerListener {
    void d(String tag, String... msg);
    void e(String tag, Throwable tr, String... msg);
    void i(String tag, String... msg);
    void v(String tag, String... msg);
    void w(String tag, String... msg);
}
