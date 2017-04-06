package com.battleship.ships;

/**
 * Created by casvd on 6-4-2017.
 */
public class AircraftCarrier implements Ship {

    private final String NAME = "Aircraft Carrier";
    private final int LENGTH = 5;

    @Override
    public String GetName() {
        return NAME;
    }

    @Override
    public int GetLength() {
        return LENGTH;
    }
}
