package it.jpanik.jgaming.exceptions;

import it.jpanik.jgaming.constants.Messages;
import it.jpanik.jgaming.enums.ExceptionType;
import it.jpanik.jgaming.utils.MessageUtils;

/**
 * @author Deborah Medici
 */
public class NotImplementedException extends CustomException {

    public NotImplementedException() {
        super(ExceptionType.NOT_IMPLEMENTED, MessageUtils.INSTANCE.getMessage(Messages.ERROR_NOT_IMPLEMENTED));
    }
}
