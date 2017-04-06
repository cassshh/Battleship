package com.battleship.ships;

/**
 * Created by casvd on 6-4-2017.
 */
public class Battleship implements Ship {

    private final String NAME = "Battleship";
    private final int LENGTH = 4;

    @Override
    public String GetName() {
        return NAME;
    }

    @Override
    public int GetLength() {
        return LENGTH;
    }
}
