package it.jpanik.jgaming.exceptions;

import it.jpanik.jgaming.enums.ExceptionType;

/**
 * @author Deborah Medici
 */
public abstract class CustomException extends Exception {

    private ExceptionType type;

    public CustomException(ExceptionType type, String message) {
        super(message);
        this.type = type;
    }

    public ExceptionType getType() {
        return type;
    }

    public void setType(ExceptionType type) {
        this.type = type;
    }

}
