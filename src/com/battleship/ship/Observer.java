package com.battleship.ship;

import com.battleship.fields.FieldState;

public abstract class Observer {
    /**
     * Update at call
     *
     * @param fieldState of Field
     */
    public abstract void update(FieldState fieldState);
}
