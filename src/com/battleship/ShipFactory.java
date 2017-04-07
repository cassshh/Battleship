package com.battleship;

import com.battleship.fields.FieldState;
import com.battleship.ships.*;

/**
 * Created by casvd on 6-4-2017.
 */
public class ShipFactory {

    public Ship makeShip(ShipType type) {
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
                return new Ship() {
                    @Override
                    public String getName() {
                        return "Stealthy McStealthFace";
                    }

                    @Override
                    public int getLength() {
                        return 0;
                    }

                    @Override
                    public int getHealth() {
                        return 0;
                    }

                    @Override
                    public void update(FieldState fieldState) {
                    }
                };
        }
    }
}
