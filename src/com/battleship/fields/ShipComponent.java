package com.battleship.fields;

import com.battleship.UI;
import com.battleship.ship.Observer;

import java.util.ArrayList;
import java.util.List;

public class ShipComponent implements Field {

    /**
     * Fields
     */
    private List<Observer> observers = new ArrayList<>();
    private FieldState fieldState = new UnharmedFieldState();

    @Override
    public String hit() {
        notifyAllObservers();
        if (!fieldState.isBombed()) {
            setFieldState(new NukedFieldState());
            return UI.ANSI_YELLOW + "*Insert explosion sounds*" + UI.ANSI_RESET;
        }
        return UI.ANSI_YELLOW + "Only in M. Bay's movies things explode twice.. git gud" + UI.ANSI_RESET;
    }

    @Override
    public void setFieldState(FieldState fieldState) {
        this.fieldState = fieldState;
    }

    @Override
    public FieldState getFieldState() {
        return fieldState;
    }

    /**
     * Attach observer
     *
     * @param observer
     */
    public void attach(Observer observer) {
        observers.add(observer);
    }

    /**
     * Notify all observers
     */
    public void notifyAllObservers() {
        for (Observer observer : observers) {
            observer.update(fieldState);
        }
    }
}
