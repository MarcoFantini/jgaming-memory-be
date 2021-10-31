package it.jpanik.jgaming.dtos.memory;

import java.util.Arrays;
import java.util.StringJoiner;

/**
 * @author Marco Fantini
 */
public class MemoryGameDto {

    private String name;
    private int maxErrors;
    private int victoryPoints;
    private int columns;
    private int rows;
    private GussetDto[][] gussetDtos;
    private int timer;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public GussetDto[][] getGussets() {
        return gussetDtos;
    }

    public void setGussets(GussetDto[][] gussetDtos) {
        this.gussetDtos = gussetDtos;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    public String gussetsConfigToString(){
        String result = "";
        for (int i=0; i < this.getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
                result = result.concat(this.gussetDtos[i][j].toString());
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", MemoryGameDto.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("maxErrors=" + maxErrors)
                .add("victoryPoints=" + victoryPoints)
                .add("columns=" + columns)
                .add("rows=" + rows)
                .add("gussetDtos=" + Arrays.toString(gussetDtos))
                .add("timer=" + timer)
                .toString();
    }
}
