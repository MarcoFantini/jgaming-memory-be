package it.jpanik.jgaming.utils;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author Deborah Medici
 */
public class MessageUtils {
    public static MessageUtils INSTANCE = new MessageUtils();

    ResourceBundle bundle;

    private MessageUtils() {
        Locale locale = new Locale("it", "IT");
        bundle = ResourceBundle.getBundle("messages", locale);
    }

    public String getMessage(String key) {
        return bundle.getString(key);
    }
}
