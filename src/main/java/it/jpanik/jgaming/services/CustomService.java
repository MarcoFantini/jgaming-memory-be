package it.jpanik.jgaming.services;

import it.jpanik.jgaming.dtos.AckDto;

/**
 * @author Deborah Medici
 */
public abstract class CustomService {

    protected AckDto ackOK(String message) {
        return buildAck(true, message);
    }

    protected AckDto ackKO(String message) {
        return buildAck(false, message);
    }

    private AckDto buildAck(boolean result, String message) {
        AckDto ackDto = new AckDto();
        ackDto.setResult(result);
        ackDto.setMessage(message);
        return ackDto;
    }
}
