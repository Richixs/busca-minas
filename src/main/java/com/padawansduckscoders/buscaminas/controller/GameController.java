package com.padawansduckscoders.buscaminas.controller;

import com.padawansduckscoders.buscaminas.model.Grid;
import com.padawansduckscoders.buscaminas.model.Cell;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.control.Alert.AlertType;
import java.util.Stack;

public class GameController {

    @FXML
    private AnchorPane gameContainer;
    @FXML
    private Text gameText;
    @FXML
    private GridPane gridGame;

    private Grid grid;

    public void initialize(int rows, int cols, int mines) {
        gameText.setText(rows + "x" + cols + " " + mines);
        gridGame.getChildren().clear();
        gridGame.setHgap(5);  
        gridGame.setVgap(5);
        
        grid = new Grid(rows, cols, mines);
        
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Button button = createButton(row, col);
                gridGame.add(button, col, row);
            }
        }
    }

    private Button createButton(int row, int col) {
        Button button = new Button();
        button.setStyle("-fx-background-color: #383838; -fx-border-color: #ffffff; -fx-border-width: 0.8px; -fx-background-radius: 10; -fx-border-radius: 10;");
        button.setPrefSize(30, 30);
        int finalRow = row;
        int finalCol = col;
        button.setOnAction(event -> handleButtonClick(finalRow, finalCol, button));
        return button;
    }

    private void handleButtonClick(int row, int col, Button button) {
        boolean isCellRevealed = grid.revealCell(row, col);
    
        if (grid.getCell(row, col).isMine()) {
            button.setText("*");
            button.setStyle("-fx-background-color: red; -fx-text-fill: white;");
            button.setDisable(true);
            showEndGameDialog("¡Perdiste! Has tocado una mina.");
        } else {
            int nearbyMines = grid.getCell(row, col).getNearbyMines();
            button.setText(Integer.toString(nearbyMines));
            button.setStyle("-fx-background-color: lightgrey; -fx-text-fill: black;");
            button.setDisable(true);
    
            if (nearbyMines == 0) {
                revealAdjacentCells(row, col);
            }
        }
    
        if (grid.isGameWon()) {
            gameText.setText("¡Has ganado!");
            showEndGameDialog("¡Felicidades! Has ganado.");
        }
    }
    
    private void revealAdjacentCells(int row, int col) {
        Stack<Cell> stack = new Stack<>();
        stack.push(grid.getCell(row, col));
        
        while (!stack.isEmpty()) {
            Cell currentCell = stack.pop();
            int currentRow = currentCell.getRow();
            int currentCol = currentCell.getCol();

            if (!currentCell.isRevealed()) {
                currentCell.reveal();
                Button button = getButtonAt(currentRow, currentCol);
                if (button != null) {
                    int nearbyMines = currentCell.getNearbyMines();
                    button.setText(nearbyMines == 0 ? "" : Integer.toString(nearbyMines));
                    button.setStyle("-fx-background-color: lightgrey; -fx-text-fill: black;");
                    button.setDisable(true);

                    if (nearbyMines == 0) {
                        for (int y = currentRow - 1; y <= currentRow + 1; y++) {
                            for (int x = currentCol - 1; x <= currentCol + 1; x++) {
                                if (y >= 0 && y < grid.getRows() && x >= 0 && x < grid.getCols()) {
                                    Cell neighbor = grid.getCell(y, x);
                                    if (!neighbor.isRevealed() && !stack.contains(neighbor)) {
                                        stack.push(neighbor);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    private Button getButtonAt(int row, int col) {
        for (javafx.scene.Node node : gridGame.getChildren()) {
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == col) {
                return (Button) node;
            }
        }
        return null;
    }

    private void showEndGameDialog(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Resultado del Juego");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
