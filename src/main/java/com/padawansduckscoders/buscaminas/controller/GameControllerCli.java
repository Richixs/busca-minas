package com.padawansduckscoders.buscaminas.controller;

import com.padawansduckscoders.buscaminas.model.Grid;

public class GameControllerCli {
    private Grid grid;
    private boolean gameOver;

    public void startGame(int rows, int cols, int totalMines) {
        grid = new Grid(rows, cols, totalMines);
        gameOver = false;
    }

    public void revealCell(int row, int col) {
        if (row < 0 || row >= grid.getRows() || col < 0 || col >= grid.getCols()) {
            System.out.println("Invalid coordinates! Please try again.");
            return;
        }

        boolean safe = grid.revealCell(row, col);
        if (!safe) {
            gameOver = true;
            return;
        } else {
            System.out.println("Safe!");
        }
        
        if (grid.isGameWon()) {
            gameOver = true;
        }
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isGameWon() {
        return grid.isGameWon();
    }
    
    public int getRows() {
        return grid.getRows();
    }
    
    public int getCols() {
        return grid.getCols();
    }

    public Grid getGrid() {
        return grid;
    }

    public void flag(int row, int col) {
        grid.flag(row, col);
    }
}
