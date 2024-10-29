package com.padawansduckscoders.buscaminas.controller;

import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class MenuController {
    @FXML
    private Button buttonPlay, buttonReport, buttonExit;
    
    private HostServices hostServices;

    private final String BUTTONSTYLE = "-fx-background-radius: 20; -fx-border-radius: 20; -fx-background-color: #383838; -fx-border-color: #ffffff;";

    public void setHostServices(HostServices hostServices) {
        this.hostServices = hostServices;
    }

    @FXML
    protected void onPlayButtonClick() {
        System.out.println("Play");
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
}