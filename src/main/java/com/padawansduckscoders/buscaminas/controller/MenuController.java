package com.padawansduckscoders.buscaminas.controller;

import java.io.IOException;
import java.util.Optional;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.scene.control.TextInputDialog;

public class MenuController {
    @FXML
    private Button buttonPlay, buttonReport, buttonExit, buttonEasy, buttonNormal, buttonHard, buttonCustom, buttonBack, buttonStartGame;
    @FXML
    private VBox vboxMenuButtons, vboxPlayButtons;
    @FXML
    private HBox hboxPlayButtons;
    @FXML
    private AnchorPane menuContainer;
    @FXML
    private Text textGame;
    
    private HostServices hostServices;

    private final String BUTTON_STYLE_DEFAULT = "-fx-background-radius: 20; -fx-border-radius: 20; -fx-background-color: #383838; -fx-border-color: #ffffff;";
    private final String BUTTON_STYLE_HOVER = "-fx-background-radius: 20; -fx-border-radius: 20; -fx-background-color: #2C2C2C; -fx-border-color: #ff0000;";
    private final Color TEXT_COLOR_DEFAULT = Color.valueOf("#ffffff");
    private final Color TEXT_COLOR_HOVER = Color.valueOf("#ff0000");
    private int rows = 8;
    private int cols = 8;
    private int mines = 10;

    public void setHostServices(HostServices hostServices) {
        this.hostServices = hostServices;
    }

    private void applyDefaultStyle(Button button) {
        button.setStyle(BUTTON_STYLE_DEFAULT);
        button.setTextFill(TEXT_COLOR_DEFAULT);
    }

    private void applyHoverStyle(Button button) {
        button.setStyle(BUTTON_STYLE_HOVER);
        button.setTextFill(TEXT_COLOR_HOVER);
    }

    @FXML
    protected void onPlayButtonClick() {
        vboxMenuButtons.setVisible(false);
        vboxPlayButtons.setVisible(true);
        hboxPlayButtons.setVisible(true);
    }

    @FXML
    protected void onPlayMouseEntered() {
        applyHoverStyle(buttonPlay);
    }

    @FXML
    protected void onPlayMouseExited() {
        applyDefaultStyle(buttonPlay);
    }

    @FXML
    protected void onReportButtonClick() {
        if (hostServices != null) {
            hostServices.showDocument("https://github.com/Richixs/busca-minas/issues");
        }
    }

    @FXML
    protected void onReportMouseEntered() {
        applyHoverStyle(buttonReport);
    }

    @FXML
    protected void onReportMouseExited() {
        applyDefaultStyle(buttonReport);
    }

    @FXML
    protected void onExitButtonClick() {
        Platform.exit();
    }

    @FXML
    protected void onExitMouseEntered() {
        applyHoverStyle(buttonExit);
    }

    @FXML
    protected void onExitMouseExited() {
        applyDefaultStyle(buttonExit);
    }

    @FXML
    protected void onEasyButtonClick() {
        rows = 8;
        cols = 8;
        mines = 10;
        textGame.setText("8x8 10");
    }

    @FXML
    protected void onEasyMouseEntered() {
        applyHoverStyle(buttonEasy);
    }

    @FXML
    protected void onEasyMouseExited() {
        applyDefaultStyle(buttonEasy);
    }

    @FXML
    protected void onNormalButtonClick() {
        rows = 16;
        cols = 16;
        mines = 40;
        textGame.setText("16x16 40");
    }

    @FXML
    protected void onNormalMouseEntered() {
        applyHoverStyle(buttonNormal);
    }

    @FXML
    protected void onNormalMouseExited() {
        applyDefaultStyle(buttonNormal);
    }

    @FXML
    protected void onHardButtonClick() {
        rows = 16;
        cols = 31;
        mines = 99;
        textGame.setText("16x31 99");
    }

    @FXML
    protected void onHardMouseEntered() {
        applyHoverStyle(buttonHard);
    }

    @FXML
    protected void onHardMouseExited() {
        applyDefaultStyle(buttonHard);
    }

    @FXML
    protected void onCustomButtonClick() {
        TextInputDialog rowsDialog = new TextInputDialog();
        rowsDialog.setTitle("Personalizar");
        rowsDialog.setHeaderText("Ingrese las filas (entre 8 y 24):");
        rowsDialog.setContentText("Filas:");
        Optional<String> rowsResult = rowsDialog.showAndWait();
        if (rowsResult.isEmpty() || !isIntegerInRange(rowsResult.get(), 8, 24)) {
            showAlert("Error", "Solo se permiten números enteros entre 8 y 24 para las filas.");
            return;
        }
        TextInputDialog colsDialog = new TextInputDialog();
        colsDialog.setTitle("Personalizar");
        colsDialog.setHeaderText("Ingrese las columnas (entre 8 y 32):");
        colsDialog.setContentText("Columnas:");
        Optional<String> colsResult = colsDialog.showAndWait();
        if (colsResult.isEmpty() || !isIntegerInRange(colsResult.get(), 8, 32)) {
            showAlert("Error", "Solo se permiten números enteros entre 8 y 32 para las columnas.");
            return;
        }
        int totalCells = Integer.parseInt(rowsResult.get()) * Integer.parseInt(colsResult.get());
        int maxMines = totalCells / 3;
        TextInputDialog minesDialog = new TextInputDialog();
        minesDialog.setTitle("Personalizar");
        minesDialog.setHeaderText("Ingrese el número de minas (entre 1 y " + maxMines + "):");
        minesDialog.setContentText("Minas:");
        Optional<String> minesResult = minesDialog.showAndWait();
        if (minesResult.isEmpty() || !isIntegerInRange(minesResult.get(), 1, maxMines)) {
            showAlert("Error", "Solo se permiten números enteros entre 1 y " + maxMines + " para las minas.");
            return;
        }
        rows = Integer.parseInt(rowsResult.get());
        cols = Integer.parseInt(colsResult.get());
        mines = Integer.parseInt(minesResult.get());
        textGame.setText(rows + "x" + cols + " " + mines);
    }

    private boolean isIntegerInRange(String input, int min, int max) {
        try {
            int value = Integer.parseInt(input);
            return value >= min && value <= max;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    private void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    protected void onCustomMouseEntered() {
        applyHoverStyle(buttonCustom);
    }

    @FXML
    protected void onCustomMouseExited() {
        applyDefaultStyle(buttonCustom);
    }

    @FXML
    protected void onStartGameButtonClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/padawansduckscoders/buscaminas/view/buscaminas-game.fxml"));
        Parent root = loader.load();
        GameController gameController = loader.getController();
        gameController.initialize(rows, cols, mines);
        Scene scene = menuContainer.getScene();
        root.translateXProperty().set(scene.getWidth());
        AnchorPane parentContainer = (AnchorPane) scene.getRoot();
        parentContainer.getChildren().add(root);
        AnchorPane.setLeftAnchor(parentContainer.getChildren().get(1), 0.00);
        AnchorPane.setRightAnchor(parentContainer.getChildren().get(1), 0.00);
        AnchorPane.setTopAnchor(parentContainer.getChildren().get(1), 0.00);
        AnchorPane.setBottomAnchor(parentContainer.getChildren().get(1), 0.00);
        Timeline timeline = new Timeline();
        KeyValue keyValue = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.2), keyValue);
        timeline.getKeyFrames().add(keyFrame);
        timeline.setOnFinished(event1 -> {
            parentContainer.getChildren().remove(menuContainer);
            System.out.println(parentContainer.getChildren().size());

        });
        timeline.play();
    }


    @FXML
    protected void onStartGameMouseEntered() {
        applyHoverStyle(buttonStartGame);
    }

    @FXML
    protected void onStartGameMouseExited() {
        applyDefaultStyle(buttonStartGame);
    }

    @FXML
    protected void onBackButtonClick() {
        vboxMenuButtons.setVisible(true);
        vboxPlayButtons.setVisible(false);
        hboxPlayButtons.setVisible(false);
    }

    @FXML
    protected void onBackMouseEntered() {
        applyHoverStyle(buttonBack);
    }

    @FXML
    protected void onBackMouseExited() {
        applyDefaultStyle(buttonBack);
    }
}