package com.padawansduckscoders.buscaminas;

import com.padawansduckscoders.buscaminas.view.ConsoleView;
import com.padawansduckscoders.buscaminas.controller.GameController;;;

public class Main {
    public static void main(String[] args) {
        ConsoleView view = new ConsoleView(new GameController());
        view.showMenu();
        view.startGame();
    }
}
