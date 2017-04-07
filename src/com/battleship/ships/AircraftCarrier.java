package com.battleship.ships;

import com.battleship.UI;
import com.battleship.fields.FieldState;
import com.battleship.ship.Ship;

public class AircraftCarrier extends Ship {

    /**
     * Fields
     */
    private final String NAME = "Aircraft Carrier";
    private final int LENGTH = 5;
    private int health = LENGTH;

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public int getLength() {
        return LENGTH;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void update(FieldState fieldState) {
        if (!fieldState.isBombed() && health > 0) {
            health--;
            UI.printf(UI.ANSI_RED + "%s got nuked%n" + UI.ANSI_RESET, NAME);
        }
    }
}
