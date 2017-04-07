package com.battleship.ships;

import com.battleship.UI;
import com.battleship.fields.FieldState;
import com.battleship.ship.Ship;

public class PatrolBoat extends Ship {

    /**
     * Fields
     */
    private final String NAME = "Patrol Board";
    private final int LENGTH = 2;
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
            UI.printf(UI.ANSI_RED + "%s invaded by pirates, aarrr%n" + UI.ANSI_RESET, NAME);
        }
    }
}
