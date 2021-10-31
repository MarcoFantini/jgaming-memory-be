package it.jpanik.jgaming.dtos;

import it.jpanik.jgaming.constants.Messages;
import it.jpanik.jgaming.enums.ExceptionType;
import it.jpanik.jgaming.exceptions.DuplicateException;
import it.jpanik.jgaming.exceptions.ServiceException;
import it.jpanik.jgaming.exceptions.ValidationException;
import it.jpanik.jgaming.utils.MessageUtils;

import java.util.StringJoiner;

/**
 * @author Deborah Medici
 */
public class ErrorDto {

    private ExceptionType type;
    private String message;

    public ErrorDto() {
        this.type = ExceptionType.UNEXPECTED;
        this.message = MessageUtils.INSTANCE.getMessage(Messages.ERROR_UNEXPECTED);
    }

    public ErrorDto(ValidationException ex) {
        this.type = ex.getType();
        this.message = ex.getMessage();
    }

    public ErrorDto(ServiceException ex) {
        this.type = ex.getType();
        this.message = ex.getMessage();
    }

    public ExceptionType getType() {
        return type;
    }

    public void setType(ExceptionType type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ErrorDto.class.getSimpleName() + "[", "]")
                .add("type=" + type)
                .add("message='" + message + "'")
                .toString();
    }
}
