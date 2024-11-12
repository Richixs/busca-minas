package com.padawansduckscoders.buscaminas.controller;

import com.padawansduckscoders.buscaminas.model.Grid;
import com.padawansduckscoders.buscaminas.model.Cell;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class GameController {

    @FXML
    private AnchorPane gameContainer;
    @FXML
    private Text gameText;
    @FXML
    private GridPane gridGame;
    @FXML
    private ImageView title;

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

    @FXML
    private void backMenu(MouseEvent event) {
        returnToMenu();
    }

    private Button createButton(int row, int col) {
        Button button = new Button();
        button.setStyle("-fx-background-color: #383838; -fx-border-color: #ffffff; -fx-border-width: 0.8px; -fx-background-radius: 10; -fx-border-radius: 10;");
        button.setPrefSize(40, 40);        
        button.setOnAction(event -> {
            if (grid.revealCell(row, col)) {
                updateGrid();
                if (grid.isGameWon()) {
                    showGameOver("¡Has ganado!", AlertType.INFORMATION);
                }
            } else {
                updateGrid();
                showGameOver("¡Has perdido!", AlertType.ERROR);
            }
        });
        button.setOnContextMenuRequested(event -> {
            grid.flag(row, col);
            updateGrid();
        });
        return button;
    }

    private void updateGrid() {
        for (int row = 0; row < grid.getRows(); row++) {
            for (int col = 0; col < grid.getCols(); col++) {
                Button button = (Button) getNodeByRowColumnIndex(row, col);
                Cell cell = grid.getCell(row, col);
                if (cell.isRevealed()) {
                    if (cell.isMine()) {
                        button.setText("💣");
                        button.setStyle("-fx-background-color: red; -fx-border-color: #ffffff; -fx-border-width: 0.8px; -fx-background-radius: 10; -fx-border-radius: 10; -fx-alignment: center;");
                    } else {
                        button.setText(cell.getNearbyMines() == 0 ? "" : String.valueOf(cell.getNearbyMines()));
                        button.setStyle("-fx-background-color: #A9A9A9; -fx-border-color: #ffffff; -fx-border-width: 0.8px; -fx-background-radius: 10; -fx-border-radius: 10;");
                    }
                } else if (cell.isFlag()) {
                    button.setText("🚩");
                    button.setStyle("-fx-background-color: #FFCC00; -fx-border-color: #ffffff; -fx-border-width: 0.8px; -fx-background-radius: 10; -fx-border-radius: 10; -fx-alignment: center;");
                } else {
                    button.setText("");
                    button.setStyle("-fx-background-color: #383838; -fx-border-color: #ffffff; -fx-border-width: 0.8px; -fx-background-radius: 10; -fx-border-radius: 10;");
                }
            }
        }
    }

    private Button getNodeByRowColumnIndex(final int row, final int column) {
        for (javafx.scene.Node node : gridGame.getChildren()) {
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
                return (Button) node;
            }
        }
        return null;
    }

    private void showGameOver(String message, AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle("Juego Terminado");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
        returnToMenu();
    }

    private void returnToMenu() {
        Scene scene = gameContainer.getScene();
        AnchorPane parentContainer = (AnchorPane) scene.getRoot();
        parentContainer.getChildren().remove(parentContainer.getChildren().get(1));
    }
}
