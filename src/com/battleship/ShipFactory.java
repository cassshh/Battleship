package com.battleship;

import com.battleship.ships.*;

/**
 * Created by casvd on 6-4-2017.
 */
public class ShipFactory {

    public Ship MakeShip(ShipType type) {
        switch (type) {
            case AIRCRAFT_CARRIER:
                return new AircraftCarrier();
            case BATTLESHIP:
                return new Battleship();
            case SUBMARINE:
                return new Submarine();
            case DESTROYER:
                return new Destroyer();
            case PATROL_BOAT:
                return new PatrolBoat();
            default:
                return null;
        }
    }
}
