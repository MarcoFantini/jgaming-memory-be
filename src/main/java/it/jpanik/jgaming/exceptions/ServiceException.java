package it.jpanik.jgaming.exceptions;

import it.jpanik.jgaming.enums.ExceptionType;

/**
 * @author Deborah Medici
 */
public class ServiceException extends CustomException {

    public ServiceException(String message) {
        super(ExceptionType.SERVICE, message);
    }
}