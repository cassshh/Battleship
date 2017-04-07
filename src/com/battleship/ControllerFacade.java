package com.battleship;

/**
 * Created by casvd on 3-4-2017.
 */
public class ControllerFacade {

    private Controller controller;

    public ControllerFacade(Controller controller) {
        this.controller = controller;
    }

    public void setupGame() {
        controller.setupPlayers();
        controller.setupBoards();
    }

    public void play() {
        //play stuff
        while (controller.isGameOnGoing()) {
            controller.play();
        }
    }

    public void kudosToWinner() {
        controller.kudosToWinner();
    }
}
