package it.jpanik.jgaming.validators;

import it.jpanik.jgaming.constants.Messages;
import it.jpanik.jgaming.dtos.ContactDto;
import it.jpanik.jgaming.exceptions.ValidationException;
import it.jpanik.jgaming.utils.MessageUtils;

/**
 * @author Deborah Medici
 */
public class ContactValidator extends CustomValidator {

    public static void validate(ContactDto dto) throws ValidationException {
        checkEmpty(dto.getEmail(), MessageUtils.INSTANCE.getMessage(Messages.ERROR_VALIDATION_CONTACT_EMAIL));
        checkEmpty(dto.getObject(), MessageUtils.INSTANCE.getMessage(Messages.ERROR_VALIDATION_CONTACT_OBJECT));
        checkEmpty(dto.getMessage(), MessageUtils.INSTANCE.getMessage(Messages.ERROR_VALIDATION_CONTACT_TEXT));
    }

}
