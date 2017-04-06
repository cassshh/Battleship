package com.battleship.fields;

import com.battleship.Observer;
import com.battleship.UI;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by casvd on 3-4-2017.
 */
public class ShipComponent implements Field {

    private List<Observer> observers = new ArrayList<>();
    private FieldState fieldState = new UnharmedFieldState();

    @Override
    public String Hit() {
        NotifyAllObservers();
        if (!fieldState.IsBombed()){
            SetFieldState(new NukedFieldState());
            return UI.ANSI_YELLOW + "*Insert explosion sounds*" + UI.ANSI_RESET;
        }
        return UI.ANSI_YELLOW + "Only in M. Bay's movies things explode twice.. git gud" + UI.ANSI_RESET;
    }

    @Override
    public void SetFieldState(FieldState fieldState) {
        this.fieldState = fieldState;
    }

    @Override
    public FieldState GetFieldState() {
        return fieldState;
    }

    public void Attach(Observer observer) {
        observers.add(observer);
    }

    public void NotifyAllObservers() {
        for (Observer observer : observers) {
            observer.update(fieldState);
        }
    }
}
