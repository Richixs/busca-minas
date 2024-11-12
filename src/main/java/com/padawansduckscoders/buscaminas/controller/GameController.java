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
        button.setOnContextMenuRequested(event -> button.setStyle("-fx-background-color: #FFCC00; -fx-border-color: #ffffff; -fx-border-width: 0.8px; -fx-background-radius: 10; -fx-border-radius: 10;"));
        return button;
    }
}
