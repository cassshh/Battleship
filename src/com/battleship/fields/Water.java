package com.battleship.fields;

import com.battleship.UI;

public class Water implements Field {

    /**
     * Fields
     */
    private FieldState fieldState = new UnharmedFieldState();

    @Override
    public String hit() {
        if (!fieldState.isBombed()) {
            setFieldState(new NukedFieldState());
            return UI.ANSI_CYAN + "Splooosshh!" + UI.ANSI_RESET;
        }
        return UI.ANSI_CYAN + "hit it again, its gonna be splooshed thrice!" + UI.ANSI_RESET;
    }

    @Override
    public void setFieldState(FieldState fieldState) {
        this.fieldState = fieldState;
    }

    @Override
    public FieldState getFieldState() {
        return fieldState;
    }
}
