package it.jpanik.jgaming.exceptions;


import it.jpanik.jgaming.enums.ExceptionType;

public class DuplicateException extends CustomException {

    public DuplicateException(String message) {
        super(ExceptionType.DUPLICATE, message);
    }
}

