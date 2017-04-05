package com.battleship;

/**
 * Created by casvd on 3-4-2017.
 */
public class Game {

    public static void main(String[] args){
        ControllerFacade controller = new ControllerFacade(new Controller());
        //Controller actions
        controller.SetupGame();
        controller.Play();
    }
}
