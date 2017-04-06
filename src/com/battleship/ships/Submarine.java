package com.battleship.ships;

import com.battleship.UI;
import com.battleship.fields.FieldState;

/**
 * Created by casvd on 6-4-2017.
 */
public class Submarine extends Ship {

    private final String NAME = "Submarine";
    private final int LENGTH = 3;
    private int health = LENGTH;

    @Override
    public String GetName() {
        return NAME;
    }

    @Override
    public int GetLength() {
        return LENGTH;
    }

    @Override
    public int GetHealth() {
        return health;
    }

    @Override
    public void update(FieldState fieldState) {
        if(!fieldState.IsBombed() && health > 0) {
            health--;
            UI.printf(UI.ANSI_RED + "%s is bein' torpedoed%n" + UI.ANSI_RESET, NAME);
        }
    }
}
