package main;

import java.util.Scanner;
import main.model.Grid;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Grid grid = new Grid(10, 10, 10);
        boolean gameInProgress = true;
        while (gameInProgress) {
            grid.displayBoard();
            System.out.println("Enter row and column: ");
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            boolean result = grid.revealCell(row, col);
            if (!result) {
                System.out.println("You hit a mine! Game over!");
                gameInProgress = false;
            }
        }
        scanner.close();
    }
}
