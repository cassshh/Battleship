package com.battleship.ships;

/**
 * Created by casvd on 6-4-2017.
 */
public class PatrolBoat implements Ship {

    private final String NAME = "Patrol Board";
    private final int LENGTH = 2;

    @Override
    public String GetName() {
        return NAME;
    }

    @Override
    public int GetLength() {
        return LENGTH;
    }
}
