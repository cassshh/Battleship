package com.battleship;

/**
 * Created by casvd on 30-3-2017.
 */
public class Game {

    private static Controller controller;

    public static void main(String[] args){
        controller = Controller.getInstance();
    }
}
