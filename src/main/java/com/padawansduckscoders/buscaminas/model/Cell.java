package main.model;

public class Cell {
    private boolean isMine;
    private boolean revealed;
    private int nearbyMines;

    public Cell() {
        this.isMine = false;
        this.revealed = false;
        this.nearbyMines = 0;
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

    public int getNearbyMines() {
        return nearbyMines;
    }

    public void setNearbyMines(int mines) {
        this.nearbyMines = mines;
    }
}
