package it.jpanik.jgaming.dtos.memory;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * @author Marco Fantini
 */
public class GussetDto {

    private int id;
    private String name;

    public GussetDto(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GussetDto gussetDto = (GussetDto) o;
        return name.equals(gussetDto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", GussetDto.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .toString();
    }
}
