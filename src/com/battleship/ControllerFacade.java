package com.battleship;

/**
 * Created by casvd on 3-4-2017.
 */
public class ControllerFacade {

    private Controller controller;

    public ControllerFacade(Controller controller) {
        this.controller = controller;
    }

    public void SetupGame() {
        controller.SetupPlayers();
        controller.SetupBoards();
    }

    public void Play(){
        //Play stuff
    }
}
