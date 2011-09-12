package am.ik.yalf.exception;

import java.util.Locale;

import am.ik.yalf.message.MessageManager;

public class ExceptionConfig {
    private static final String CONFIG_FILE = "META-INF/yalf.properties";
    protected static final MessageManager MESSAGE_MANAGER = new MessageManager(
            CONFIG_FILE);
    private static final ThreadLocal<Locale> locale = new ThreadLocal<Locale>();

    public static Locale getLocale() {
        return locale.get();
    }

    public static void setLocale(Locale locale) {
        ExceptionConfig.locale.set(locale);
    }

    public static String getMessage(String errorId, Object... args) {
        return MESSAGE_MANAGER.getMessage(false, errorId, getLocale(), args);
    }
}
