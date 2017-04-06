package com.battleship;

import com.battleship.fields.FieldState;
/**
 * Created by casvd on 6-4-2017.
 */
public abstract class Observer {
    public abstract void update(FieldState fieldState);
}
