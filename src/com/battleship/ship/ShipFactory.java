package com.battleship.ship;

import com.battleship.fields.FieldState;
import com.battleship.ships.*;

public class ShipFactory {

    /**
     * Creating ship according to type
     *
     * @param type of ship
     * @return Ship of given type
     */
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
