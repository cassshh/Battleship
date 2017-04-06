package com.battleship.ships;

import com.battleship.UI;
import com.battleship.fields.FieldState;

/**
 * Created by casvd on 6-4-2017.
 */
public class Battleship extends Ship {

    private final String NAME = "Battleship";
    private final int LENGTH = 4;
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
            UI.printf(UI.ANSI_RED + "%s is gettin' battled real gud%n" + UI.ANSI_RESET, NAME);
        }
    }
}
