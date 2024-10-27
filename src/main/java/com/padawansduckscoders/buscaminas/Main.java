package com.padawansduckscoders.buscaminas;

import com.padawansduckscoders.buscaminas.view.ConsoleView;
import com.padawansduckscoders.buscaminas.controller.GameControllerCli;;;

public class Main {
    public static void main(String[] args) {
        ConsoleView view = new ConsoleView(new GameControllerCli());
        view.showMenu();
        view.startGame();
    }
}
