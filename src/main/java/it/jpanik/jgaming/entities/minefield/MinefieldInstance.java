package it.jpanik.jgaming.entities.minefield;

import it.jpanik.jgaming.enums.Level;

import javax.persistence.*;
import java.util.StringJoiner;

/**
 * @author Jacopo Cervellini
 */
@Entity
@SequenceGenerator(name = "MINEFIELD_SEQUENCE_GENERATOR", allocationSize = 1, sequenceName = "MINEFIELD_SEQ")
public class MinefieldInstance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "MINEFIELD_SEQUENCE_GENERATOR")
    private Long id;

    @Column(nullable = false)
    private int columns;

    @Column(nullable = false)
    private int bombs;

    @Column(nullable = false)
    private int treasures;

    @Column(nullable = false)
    private int life;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
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
        return new StringJoiner(", ", MinefieldInstance.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("columns=" + columns)
                .add("bombs=" + bombs)
                .add("treasures=" + treasures)
                .add("life=" + life)
                .add("name='" + name + "'")
                .toString();
    }
}
