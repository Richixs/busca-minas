package com.padawansduckscoders.buscaminas.view;

import java.util.InputMismatchException;
import java.util.Scanner;
import com.padawansduckscoders.buscaminas.controller.GameControllerCli;
import com.padawansduckscoders.buscaminas.model.Cell;
import com.padawansduckscoders.buscaminas.model.Grid;

public class ConsoleView {
    private GameControllerCli gameController;
    private Scanner scanner;

    public ConsoleView(GameControllerCli gameController) {
        this.scanner = new Scanner(System.in);
        this.gameController = gameController;
    }

    public void showMenu() {
        int choice = getValidDifficulty();
        int rows = 0, cols = 0, mines = 0;
        switch (choice) {
            case 1:
                rows = 8;
                cols = 8;
                mines = 10;
                break;
            case 2:
                rows = 16;
                cols = 16;
                mines = 40;
                break;
            case 3:
                rows = 16;
                cols = 31;
                mines = 99;
                break;
            case 4:
                rows = getValidInput("Enter number of rows (1-16): ", 1, 16);
                cols = getValidInput("Enter number of columns (1-31): ", 1, 31);
                mines = getValidInput("Enter number of mines (0 or more): ", 0, Integer.MAX_VALUE);
                break;
        }
        gameController.startGame(rows, cols, mines);
    }

    private int getValidInput(String message, int min, int max) {
        int value = 0;
        boolean valid = false;
        while (!valid) {
            System.out.print(message);
            try {
                value = scanner.nextInt();
                if (value < min || value > max) {
                    System.out.println("The value must be between " + min + " and " + max + ". Please try again.");
                } else {
                    valid = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter a valid integer.");
                scanner.next();
            }
        }
        return value;
    }

    private int getValidDifficulty() {
        int input = 0;
        boolean valid = false;
        while (!valid) {
            try {
                System.out.println("Select difficulty:");
                System.out.println("1. Easy (8x8, 10 mines)");
                System.out.println("2. Medium (16x16, 40 mines)");
                System.out.println("3. Hard (16x31, 99 mines)");
                System.out.println("4. Custom (Set your own size and mines)");
                System.out.print("Choose an option: ");
                input = scanner.nextInt();
                if (input < 1 || input > 4) {
                    throw new IllegalArgumentException("The number must be 1 or 4.");
                }
                valid = true;
            } catch (InputMismatchException e) {
                System.out.println("Error: You must enter an integer.");
                scanner.next();
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        return input;
    }

    private int getValidAction() {
        int input = 0;
        boolean valid = false;
        while (!valid) {
            try {
                System.out.println("Select action:");
                System.out.println("1. Reveal cell");
                System.out.println("2. Mark cell");
                input = scanner.nextInt();
                if (input < 1 || input > 2) {
                    throw new IllegalArgumentException("The number must be 1 or 2.");
                }
                valid = true;
            } catch (InputMismatchException e) {
                System.out.println("Error: You must enter an integer.");
                scanner.next();
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        return input;
    }

    public void startGame() {
        System.out.println("Welcome to Minesweeper!");
        displayBoard();
        while (!gameController.isGameOver()) {
            int row = getValidInput("Enter row: ", 0, gameController.getRows() - 1);
            int col = getValidInput("Enter column: ", 0, gameController.getCols() - 1);
            int action = getValidAction();
            switch (action) {
                case 1:
                    gameController.revealCell(row, col); 
                    break;
                case 2:
                    gameController.flag(row, col);
                    break;
            }
            displayBoard();
        }
        if (gameController.isGameWon()) {
            System.out.println("Congratulations! You've cleared the board!");
        } else {
            System.out.println("Game over! You hit a mine.");
            gameController.getGrid().revealAllMines();
            displayBoard();
        }
    }

    private void displayBoard() {
        Grid grid = gameController.getGrid();
        Cell[][] boardCells = grid.getBoardCells();
    
        System.out.print("   ");
        for (int col = 0; col < boardCells[0].length; col++) {
            System.out.print(String.format("%2d ", col));
        }
        System.out.print("\n   ");
        for (int col = 0; col < boardCells[0].length; col++) {
            System.out.print("-- ");
        }
        System.out.println();
    
        for (int i = 0; i < gameController.getRows(); i++) {
            System.out.print(String.format("%2d|", i));
            for (int j = 0; j < gameController.getCols(); j++) {
                if (boardCells[i][j].isFlag() && !boardCells[i][j].isRevealed()) {
                    System.out.print("🚩 ");
                } else if (boardCells[i][j].isRevealed()) {
                    if (boardCells[i][j].isMine()) {
                        System.out.print("💣 ");
                    } else {
                        System.out.print(String.format("%2d ", boardCells[i][j].getNearbyMines()));
                    }
                } else {
                    System.out.print(" - ");
                }
            }
            System.out.println();
        }
    }    
}
