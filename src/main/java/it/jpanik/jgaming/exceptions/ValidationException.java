package it.jpanik.jgaming.exceptions;

import it.jpanik.jgaming.enums.ExceptionType;

/**
 * @author Deborah Medici
 */
public class ValidationException extends CustomException {

    public ValidationException(String message) {
        super(ExceptionType.VALIDATION, message);
    }
}
