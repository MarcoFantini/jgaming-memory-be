package it.jpanik.jgaming.dtos;

/**
 * @author Deborah Medici
 */
public class AckDto {

    private boolean result;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
