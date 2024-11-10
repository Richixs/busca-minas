package com.padawansduckscoders.buscaminas.model;

public class Cell {
    private int row; 
    private int col; 
    private boolean isMine;
    private boolean revealed;
    private int nearbyMines;

    public Cell() {
        this.row = row;
        this.col = col;
        this.isMine = false;
        this.revealed = false;
        this.nearbyMines = 0;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
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
