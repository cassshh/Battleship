package com.battleship.fields;

/**
 * Created by casvd on 6-4-2017.
 */
public class NukedFieldState implements FieldState {

    @Override
    public boolean IsBombed() {
        return true;
    }
}
