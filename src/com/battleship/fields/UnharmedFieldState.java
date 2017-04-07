package com.battleship.fields;

public class UnharmedFieldState implements FieldState {
    @Override
    public boolean isBombed() {
        return false;
    }
}
