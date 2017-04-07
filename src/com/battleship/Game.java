package com.battleship;

public class Game {

    /**
     * Main method to start this masterpiece
     *
     * @param args
     */
    public static void main(String[] args) {
        ControllerFacade controller = new ControllerFacade(new Controller());
        //Controller actions
        controller.setupGame();
        controller.play();
        controller.kudosToWinner();
    }
}
