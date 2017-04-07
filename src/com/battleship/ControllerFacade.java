package com.battleship;

public class ControllerFacade {

    /**
     * Fields
     */
    private Controller controller;

    /**
     * Constructor
     *
     * @param controller to control
     */
    public ControllerFacade(Controller controller) {
        this.controller = controller;
    }

    /**
     * Setting up the game
     */
    public void setupGame() {
        controller.setupPlayers();
        controller.setupBoards();
    }

    /**
     * Play the game
     */
    public void play() {
        //play stuff
        while (controller.isGameOnGoing()) {
            controller.play();
        }
    }

    /**
     * Winner winner chicken dinner
     */
    public void kudosToWinner() {
        controller.kudosToWinner();
    }
}
