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

    public void setHostServices(HostServices hostServices) {
        this.hostServices = hostServices;
    }

    @FXML
    protected void onPlayButtonClick() {
        System.out.println("Play");
    }

    @FXML
    protected void onPlayMouseEntered() {
        String existingStyle = buttonPlay.getStyle();
        buttonPlay.setStyle(existingStyle + "-fx-background-color: #2C2C2C; -fx-border-color: #ff0000;");
        buttonPlay.setTextFill(Color.valueOf("#ff0000"));
    }

    @FXML
    protected void onPlayMouseExited() {
        String existingStyle = buttonPlay.getStyle();
        buttonPlay.setStyle(existingStyle + "-fx-background-color: #383838; -fx-border-color: #ffffff;");
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
        String existingStyle = buttonReport.getStyle();
        buttonReport.setStyle(existingStyle + "-fx-background-color: #2C2C2C; -fx-border-color: #ff0000;");
        buttonReport.setTextFill(Color.valueOf("#ff0000"));
    }

    @FXML
    protected void onReportMouseExited() {
        String existingStyle = buttonReport.getStyle();
        buttonReport.setStyle(existingStyle + "-fx-background-color: #383838; -fx-border-color: #ffffff;");
        buttonReport.setTextFill(Color.valueOf("#ffffff"));
    }

    @FXML
    protected void onExitButtonClick() {
        Platform.exit();
    }

    @FXML
    protected void onExitMouseEntered() {
        String existingStyle = buttonExit.getStyle();
        buttonExit.setStyle(existingStyle + "-fx-background-color: #2C2C2C; -fx-border-color: #ff0000;");
        buttonExit.setTextFill(Color.valueOf("#ff0000"));
    }

    @FXML
    protected void onExitMouseExited() {
        String existingStyle = buttonExit.getStyle();
        buttonExit.setStyle(existingStyle + "-fx-background-color: #383838; -fx-border-color: #ffffff;");
        buttonExit.setTextFill(Color.valueOf("#ffffff"));
    }
}