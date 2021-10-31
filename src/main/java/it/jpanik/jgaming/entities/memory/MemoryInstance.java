package it.jpanik.jgaming.entities.memory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import java.util.StringJoiner;

/**
 * @author Marco Fantini
 */
@Entity
@SequenceGenerator(name = "MEMORY_SEQUENCE_GENERATOR", allocationSize = 1, sequenceName = "MEMORY_SEQ")
public class MemoryInstance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "MEMORY_SEQUENCE_GENERATOR")
    private Long id;

    @Column(nullable = false,unique = true)
    private String name;

    @Column(nullable = false)
    private int columns;

    @Column(nullable = false)
    private int rows;

    @Column(nullable = false)
    private int maxErrors;

    @Column(nullable = false)
    private int victoryPoints;

    @Column(nullable = false)
    private int timer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getMaxErrors() {
        return maxErrors;
    }

    public void setMaxErrors(int maxErrors) {
        this.maxErrors = maxErrors;
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }

    public void setVictoryPoints(int victoryPoints) {
        this.victoryPoints = victoryPoints;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", MemoryInstance.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("columns=" + columns)
                .add("rows=" + rows)
                .add("maxErrors=" + maxErrors)
                .add("victoryPoints=" + victoryPoints)
                .add("timer=" + timer)
                .toString();
    }
}
