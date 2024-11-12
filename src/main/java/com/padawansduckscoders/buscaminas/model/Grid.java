package com.padawansduckscoders.buscaminas.model;

import java.util.Random;

public class Grid {
    private Cell[][] boardCells;
    private int rows;
    private int cols;
    private int totalMines;
    private boolean firstReveal = true;

    public Grid(int rows, int cols, int totalMines) {
        this.rows = rows;
        this.cols = cols;
        this.totalMines = totalMines;
        boardCells = new Cell[rows][cols];
        initializeBoardCells();
    }

    private void initializeBoardCells() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                boardCells[row][col] = new Cell();
            }
        }
    }

    private void placeMines(int firstRow, int firstCol) {
        Random random = new Random();
        int minesPlaced = 0;
        while (minesPlaced < totalMines) {
            int row = random.nextInt(rows);
            int col = random.nextInt(cols);
            if (!boardCells[row][col].isMine() && 
                (row != firstRow || col != firstCol) && 
                !neighborOfFirstReveal(firstRow, firstCol, row, col)) {
                boardCells[row][col].setMine();
                minesPlaced++;
            }
        }
        calculateNearByMines();
    }

    private boolean neighborOfFirstReveal(int firstRow, int firstCol, int row, int col) {
        int rowDifference = Math.abs(firstRow - row);
        int colDifference = Math.abs(firstCol - col);
        return (rowDifference <= 1 && colDifference <= 1) && !(row == firstRow && col == firstCol);
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
        if (firstReveal) {
            firstReveal = false;
            placeMines(row, col); 
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

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public boolean isGameWon() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (!boardCells[row][col].isMine() && !boardCells[row][col].isRevealed()) {
                    return false; 
                }
            }
        }
        return true;
    }

    public Cell getCell(int row, int col) {
        return boardCells[row][col];
    }

    public Cell[][] getBoardCells() {
        return boardCells;
    }

    public void flag(int row, int col) {
        boardCells[row][col].flag();
    }
}
