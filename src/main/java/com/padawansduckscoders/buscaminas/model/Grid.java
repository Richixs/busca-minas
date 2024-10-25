package com.padawansduckscoders.buscaminas.model;

import java.util.Random;;

public class Grid {
    private Cell[][] boardCells;
    private int rows;
    private int cols;
    private int totalMines;

    public Grid(int rows, int cols, int totalMines) {
        this.rows = rows;
        this.cols = cols;
        this.totalMines = totalMines;
        boardCells = new Cell[rows][cols];
        initializeBoardCells();
        placeMines();
        calculateNearByMines();
    }

    private void initializeBoardCells() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                boardCells[row][col] = new Cell();
            }
        }
    }

    private void placeMines() {
        Random random = new Random();
        int minesPlaced = 0;
        while (minesPlaced < totalMines) {
            int row = random.nextInt(rows);
            int col = random.nextInt(cols);
            if (!boardCells[row][col].isMine()) {
                boardCells[row][col].setMine();
                minesPlaced++;
            }
        }
    }

    private void calculateNearByMines() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (!boardCells[row][col].isMine()) {
                    int count = countMinesAround(row, col);
                    boardCells[row][col].setNearbyMines(count);
                }
            }
        }
    }

    private int countMinesAround(int row, int col) {
        int count = 0;
        for (int yAxis = row - 1; yAxis <= row + 1; yAxis++) {
            for (int xAxis = col - 1; xAxis <= col + 1; xAxis++) {
                if (yAxis >= 0 && yAxis < rows && xAxis >= 0 && xAxis < cols && boardCells[yAxis][xAxis].isMine()) {
                    count++;
                }
            }
        }
        return count;        
    }

    public boolean revealCell(int row, int col) {
        if (boardCells[row][col].isRevealed()) {
            return true;
        }
        if (boardCells[row][col].isMine()) {
            return false;
        }
        boardCells[row][col].reveal();
        if (boardCells[row][col].getNearbyMines() == 0) {
            for (int yAxis = row - 1; yAxis <= row + 1; yAxis++) {
                for (int xAxis = col - 1; xAxis <= col + 1; xAxis++) {
                    if (yAxis >= 0 && yAxis < rows && xAxis >= 0 && xAxis < cols) {
                        if (yAxis != row || xAxis != col) {
                            revealCell(yAxis, xAxis);
                        }
                    }
                }
            }
        }
        return true;
    }

    public void displayBoard() {
        System.out.print("  ");
        for (int col = 0; col < boardCells[0].length; col++) {
            System.out.print(col + " ");
        }
        System.out.print("\n  ");
        for (int col = 0; col < boardCells[0].length; col++) {
            System.out.print("_ ");
        }
        System.out.println();
        for (int i = 0; i < rows; i++) {
            System.out.print(i + "|");
            for (int j = 0; j < cols; j++) {
                if (boardCells[i][j].isRevealed()) {
                    if (boardCells[i][j].isMine()) {
                        System.out.print("* ");
                    } else {
                        System.out.print(boardCells[i][j].getNearbyMines() + " ");
                    }
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
    }
}
