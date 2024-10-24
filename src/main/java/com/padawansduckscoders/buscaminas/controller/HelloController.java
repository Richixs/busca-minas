package com.padawansduckscoders.buscaminas.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("\uF31A HelloWorld \uDB82\uDCC7");
        welcomeText.setFont(new Font("Hack Nerd Font Regular", 13.00));
    }
}