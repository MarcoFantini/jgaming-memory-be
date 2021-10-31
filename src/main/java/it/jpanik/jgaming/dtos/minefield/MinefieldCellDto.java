package it.jpanik.jgaming.dtos.minefield;

/**
 * @author Jacopo Cervellini
 */
public class MinefieldCellDto {

    private boolean treasure;
    private boolean bomb;

    public boolean isTreasure() {
        return treasure;
    }

    public void setTreasure(boolean treasure) {
        this.treasure = treasure;
    }

    public boolean isBomb() {
        return bomb;
    }

    public void setBomb(boolean bomb) {
        this.bomb = bomb;
    }

    @Override
    public String toString() {
        return "MinefieldCellDto{" +
                "treasure=" + treasure +
                ", bomb=" + bomb +
                '}';
    }
}
