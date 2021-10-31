package it.jpanik.jgaming.dtos.minefield;

import it.jpanik.jgaming.enums.Level;

import java.util.StringJoiner;

/**
 * @author Jacopo Cervellini
 */
public class MinefieldInstanceDto {

    private Long id;
    private int columns;
    private int bombs;
    private int treasures;
    private int life;
    private String name;
    private Level level;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getBombs() {
        return bombs;
    }

    public void setBombs(int bombs) {
        this.bombs = bombs;
    }

    public int getTreasures() {
        return treasures;
    }

    public void setTreasures(int treasures) {
        this.treasures = treasures;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", MinefieldInstanceDto.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("columns=" + columns)
                .add("bombs=" + bombs)
                .add("treasures=" + treasures)
                .add("life=" + life)
                .add("name='" + name + "'")
                .toString();
    }
}
