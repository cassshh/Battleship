package com.battleship.fields;

/**
 * Created by casvd on 3-4-2017.
 */
public interface Field {
    String hit();
    void setFieldState(FieldState fieldState);
    FieldState getFieldState();
}
