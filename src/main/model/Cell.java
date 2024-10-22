package main.model;

public class Cell {
    private boolean isMine;
    private boolean revealed;
    private int nearByMines;

    public Cell() {
        this.isMine = false;
        this.revealed = false;
        this.nearByMines = 0;
    }

    public boolean isMine() {
        return isMine;
    }

    public void setMine() {
        this.isMine = true;
    }

    public boolean isRevealed() {
        return revealed;
    }

    public void reveal() {
        this.revealed = true;
    }

    public int getNearByMines() {
        return nearByMines;
    }

    public void incrementNearByMines() {
        this.nearByMines++;
    }
}
