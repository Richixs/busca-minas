package com.padawansduckscoders.buscaminas.view;

import java.util.Scanner;
import com.padawansduckscoders.buscaminas.controller.GameController;

public class ConsoleView {
    private GameController gameController;
    private Scanner scanner;

    public ConsoleView(GameController gameController) {
        this.scanner = new Scanner(System.in);
        this.gameController = gameController;
    }

    public void showMenu() {
        System.out.println("Select difficulty:");
        System.out.println("1. Easy (8x8, 10 mines)");
        System.out.println("2. Medium (16x16, 40 mines)");
        System.out.println("3. Hard (16x31, 99 mines)");
        System.out.println("4. Custom (Set your own size and mines)");
        System.out.print("Choose an option: ");

        int choice = scanner.nextInt();
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
            default:
                System.out.println("Invalid option. Defaulting to Easy difficulty.");
                rows = 8;
                cols = 8;
                mines = 10;
                break;
        }

        gameController.startGame(rows, cols, mines);
    }

    private int getValidInput(String message, int min, int max) {
        int value;
        do {
            System.out.print(message);
            value = scanner.nextInt();
            if (value < min || value > max) {
                System.out.println("The value must be between " + min + " and " + max + ". Please try again.");
            }
        } while (value < min || value > max);
        return value;
    }

    public void startGame() {
        System.out.println("Welcome to Minesweeper!");
        gameController.displayBoard();
        
        while (!gameController.isGameOver()) {
            int row = getValidInput("Enter row: ", 0, gameController.getRows() - 1);
            int col = getValidInput("Enter column: ", 0, gameController.getCols() - 1);
            gameController.revealCell(row, col);
            gameController.displayBoard();
        }
        
        if (gameController.isGameWon()) {
            System.out.println("Congratulations! You've cleared the board!");
        } else {
            System.out.println("Game over! You hit a mine.");
        }
    }
}
