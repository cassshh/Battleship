package com.battleship.ships;

/**
 * Created by casvd on 6-4-2017.
 */
public class Destroyer implements Ship {

    private final String NAME = "Destroyer";
    private final int LENGTH = 3;

    @Override
    public String GetName() {
        return NAME;
    }

    @Override
    public int GetLength() {
        return LENGTH;
    }
}
