package com.battleship.fields;

public class NukedFieldState implements FieldState {

    @Override
    public boolean isBombed() {
        return true;
    }
}
