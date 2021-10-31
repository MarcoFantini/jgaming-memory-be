package it.jpanik.jgaming.dtos.minefield;

import it.jpanik.jgaming.enums.Level;

import java.util.Arrays;
import java.util.StringJoiner;

/**
 * @author Jacopo Cervellini
 */
public class MinefieldGameDto {

    private String name;
    private MinefieldCellDto[][] cells;
    private int life;
    private int treasures;
    private int columns;
    private int row;
    private Level level;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MinefieldCellDto[][] getCells() {
        return cells;
    }

    public void setCells(MinefieldCellDto[][] cells) {
        this.cells = cells;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getTreasures() {
        return treasures;
    }

    public void setTreasures(int treasures) {
        this.treasures = treasures;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "MinefieldGameDto{" +
                "name='" + name + '\'' +
                ", cells=" + Arrays.toString(cells) +
                ", life=" + life +
                ", treasures=" + treasures +
                ", columns=" + columns +
                ", row=" + row +
                ", level=" + level +
                '}';
    }
}

