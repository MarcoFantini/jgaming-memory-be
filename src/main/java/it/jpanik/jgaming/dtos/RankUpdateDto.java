package it.jpanik.jgaming.dtos;

import it.jpanik.jgaming.enums.GameType;

/**
 * @author Jacopo Cervellini
 */
public class RankUpdateDto {

    private Long userId;
    private GameType gameType;
    private boolean result;
    private int victoryPoints;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long id) {
        this.userId = id;
    }

    public GameType getGameType() {
        return gameType;
    }

    public void setGameType(GameType gameType) {
        this.gameType = gameType;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }

    public void setVictoryPoints(int victoryPoints) {
        this.victoryPoints = victoryPoints;
    }

    @Override
    public String toString() {
        return "RankUpdateDto{" +
                "userId=" + userId +
                ", gameType=" + gameType +
                ", result=" + result +
                ", victoryPoints=" + victoryPoints +
                '}';
    }
}
