package com.battleship.ships;

import com.battleship.Observer;
import com.battleship.fields.ShipComponent;

/**
 * Created by casvd on 6-4-2017.
 */
public abstract class Ship extends Observer{
    public void addComponent(ShipComponent subject) {
        subject.attach(this);
    }
    public abstract String getName();
    public abstract int getLength();
    public abstract int getHealth();
}
