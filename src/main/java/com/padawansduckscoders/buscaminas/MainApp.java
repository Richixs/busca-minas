package com.padawansduckscoders.buscaminas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

import com.padawansduckscoders.buscaminas.controller.MenuController;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Font.loadFont(getClass().getResourceAsStream("/fonts/Modak-Regular.ttf"), 36);
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("view/buscaminas-menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 620, 720);
        MenuController controller = fxmlLoader.getController();
        controller.setHostServices(getHostServices());
        stage.setMinWidth(620);
        stage.setMinHeight(720);
        stage.setTitle("Buscaminas");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}