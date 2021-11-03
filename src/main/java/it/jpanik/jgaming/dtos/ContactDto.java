package it.jpanik.jgaming.dtos;

import java.util.StringJoiner;

/**
 * @author Marco Fantini
 */
public class ContactDto {

    private String email;
    private String object;
    private String message;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ContactDto.class.getSimpleName() + "[", "]")
                .add("email='" + email + "'")
                .add("object='" + object + "'")
                .add("message='" + message + "'")
                .toString();
    }


}
