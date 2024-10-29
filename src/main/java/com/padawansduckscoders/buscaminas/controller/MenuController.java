package com.padawansduckscoders.buscaminas.controller;

import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class MenuController {
    @FXML
    private Button buttonPlay, buttonReport, buttonExit, buttonEasy, buttonNormal, buttonHard, buttonCustom, buttonBack, buttonStartGame;
    @FXML
    private VBox vboxMenuButtons, vboxPlayButtons;
    @FXML
    private HBox hboxPlayButtons;
    
    private HostServices hostServices;

    private final String BUTTONSTYLE = "-fx-background-radius: 20; -fx-border-radius: 20; -fx-background-color: #383838; -fx-border-color: #ffffff;";

    public void setHostServices(HostServices hostServices) {
        this.hostServices = hostServices;
    }

    @FXML
    protected void onPlayButtonClick() {
        vboxMenuButtons.setVisible(false);
        vboxPlayButtons.setVisible(true);
        hboxPlayButtons.setVisible(true);
    }

    @FXML
    protected void onPlayMouseEntered() {
        buttonPlay.setStyle(BUTTONSTYLE + "-fx-background-color: #2C2C2C; -fx-border-color: #ff0000;");
        buttonPlay.setTextFill(Color.valueOf("#ff0000"));
    }

    @FXML
    protected void onPlayMouseExited() {
        buttonPlay.setStyle(BUTTONSTYLE);
        buttonPlay.setTextFill(Color.valueOf("#ffffff"));
    }

    @FXML
    protected void onReportButtonClick() {
        if (hostServices != null) {
            hostServices.showDocument("https://github.com/Richixs/busca-minas/issues");
        }
    }

    @FXML
    protected void onReportMouseEntered() {
        buttonReport.setStyle(BUTTONSTYLE + "-fx-background-color: #2C2C2C; -fx-border-color: #ff0000;");
        buttonReport.setTextFill(Color.valueOf("#ff0000"));
    }

    @FXML
    protected void onReportMouseExited() {
        buttonReport.setStyle(BUTTONSTYLE);
        buttonReport.setTextFill(Color.valueOf("#ffffff"));
    }

    @FXML
    protected void onExitButtonClick() {
        Platform.exit();
    }

    @FXML
    protected void onExitMouseEntered() {
        buttonExit.setStyle(BUTTONSTYLE + "-fx-background-color: #2C2C2C; -fx-border-color: #ff0000;");
        buttonExit.setTextFill(Color.valueOf("#ff0000"));
    }

    @FXML
    protected void onExitMouseExited() {
        buttonExit.setStyle(BUTTONSTYLE);
        buttonExit.setTextFill(Color.valueOf("#ffffff"));
    }

    @FXML
    protected void onEasyButtonClick() {
        System.out.println("easy");
    }

    @FXML
    protected void onEasyMouseEntered() {
        buttonEasy.setStyle(BUTTONSTYLE + "-fx-background-color: #2C2C2C; -fx-border-color: #ff0000;");
        buttonEasy.setTextFill(Color.valueOf("#ff0000"));
    }

    @FXML
    protected void onEasyMouseExited() {
        buttonEasy.setStyle(BUTTONSTYLE);
        buttonEasy.setTextFill(Color.valueOf("#ffffff"));
    }

    @FXML
    protected void onNormalButtonClick() {
        System.out.println("normal");
    }

    @FXML
    protected void onNormalMouseEntered() {
        buttonNormal.setStyle(BUTTONSTYLE + "-fx-background-color: #2C2C2C; -fx-border-color: #ff0000;");
        buttonNormal.setTextFill(Color.valueOf("#ff0000"));
    }

    @FXML
    protected void onNormalMouseExited() {
        buttonNormal.setStyle(BUTTONSTYLE);
        buttonNormal.setTextFill(Color.valueOf("#ffffff"));
    }

    @FXML
    protected void onHardButtonClick() {
        System.out.println("hard");
    }

    @FXML
    protected void onHardMouseEntered() {
        buttonHard.setStyle(BUTTONSTYLE + "-fx-background-color: #2C2C2C; -fx-border-color: #ff0000;");
        buttonHard.setTextFill(Color.valueOf("#ff0000"));
    }

    @FXML
    protected void onHardMouseExited() {
        buttonHard.setStyle(BUTTONSTYLE);
        buttonHard.setTextFill(Color.valueOf("#ffffff"));
    }

    @FXML
    protected void onCustomButtonClick() {
        System.out.println("custom");
    }

    @FXML
    protected void onCustomMouseEntered() {
        buttonCustom.setStyle(BUTTONSTYLE + "-fx-background-color: #2C2C2C; -fx-border-color: #ff0000;");
        buttonCustom.setTextFill(Color.valueOf("#ff0000"));
    }

    @FXML
    protected void onCustomMouseExited() {
        buttonCustom.setStyle(BUTTONSTYLE);
        buttonCustom.setTextFill(Color.valueOf("#ffffff"));
    }

    @FXML
    protected void onStartGameButtonClick() {
        System.out.println("StartGame");
    }

    @FXML
    protected void onStartGameMouseEntered() {
        buttonStartGame.setStyle(BUTTONSTYLE + "-fx-background-color: #2C2C2C; -fx-border-color: #ff0000;");
        buttonStartGame.setTextFill(Color.valueOf("#ff0000"));
    }

    @FXML
    protected void onStartGameMouseExited() {
        buttonStartGame.setStyle(BUTTONSTYLE);
        buttonStartGame.setTextFill(Color.valueOf("#ffffff"));
    }

    @FXML
    protected void onBackButtonClick() {
        vboxMenuButtons.setVisible(true);
        vboxPlayButtons.setVisible(false);
        hboxPlayButtons.setVisible(false);
    }

    @FXML
    protected void onBackMouseEntered() {
        buttonBack.setStyle(BUTTONSTYLE + "-fx-background-color: #2C2C2C; -fx-border-color: #ff0000;");
        buttonBack.setTextFill(Color.valueOf("#ff0000"));
    }

    @FXML
    protected void onBackMouseExited() {
        buttonBack.setStyle(BUTTONSTYLE);
        buttonBack.setTextFill(Color.valueOf("#ffffff"));
    }
}