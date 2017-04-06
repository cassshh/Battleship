package com.battleship.fields;

import com.battleship.UI;

/**
 * Created by casvd on 3-4-2017.
 */
public class Water implements Field {

    private FieldState fieldState = new UnharmedFieldState();

    @Override
    public String Hit() {
        if (!fieldState.IsBombed()){
            SetFieldState(new NukedFieldState());
            return UI.ANSI_CYAN + "Splooosshh!" + UI.ANSI_RESET;
        }
        return UI.ANSI_CYAN + "Hit it again, its gonna be splooshed thrice!" + UI.ANSI_RESET;
    }

    @Override
    public void SetFieldState(FieldState fieldState) {
        this.fieldState = fieldState;
    }

    @Override
    public FieldState GetFieldState() {
        return fieldState;
    }
}
