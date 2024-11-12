package com.padawansduckscoders.buscaminas.model;

public class Cell {
    private boolean isMine;
    private boolean isFlag;
    private boolean revealed;
    private int nearbyMines;

    public Cell() {
        this.isMine = false;
        this.revealed = false;
        this.isFlag = false;
        this.nearbyMines = 0;
    }

    public boolean isFlag() {
        return isFlag;
    }

    public void flag() {
        isFlag = !isFlag;
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
