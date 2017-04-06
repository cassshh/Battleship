package com.battleship.fields;

/**
 * Created by casvd on 3-4-2017.
 */
public interface Field {
    String Hit();
    void SetFieldState(FieldState fieldState);
    FieldState GetFieldState();
}
