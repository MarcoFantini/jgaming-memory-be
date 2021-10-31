package it.jpanik.jgaming.dtos.memory;

import java.util.StringJoiner;

/**
 * @author Marco Fantini
 */
public class MemoryInstanceDto {

    private Long id;
    private String name;
    private int columns;
    private int rows;
    private int maxErrors;
    private int victoryPoints;
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
        return new StringJoiner(", ", MemoryInstanceDto.class.getSimpleName() + "[", "]")
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
