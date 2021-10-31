package it.jpanik.jgaming.validators;

import it.jpanik.jgaming.exceptions.ValidationException;

import java.util.List;

/**
 * @author Deborah Medici
 */
public abstract class CustomValidator {

    public static void checkEmpty(String value, String message) throws ValidationException {
        if (value == null || value.trim().isEmpty()) {
            throw new ValidationException(message);
        }
    }

    public static void checkEmpty(List<?> values, String message) throws ValidationException {
        if (values == null || values.isEmpty()) {
            throw new ValidationException(message);
        }
    }

    public static void checkNull(Object obj, String message) throws ValidationException {
        if (obj == null) {
            throw new ValidationException(message);
        }
    }

    public static void checkRange(int value, int min, int max, String message) throws ValidationException {
        if (value < min || value > max) {
            throw new ValidationException(message);
        };
    }
}
