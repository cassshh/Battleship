package com.battleship.ships;

import com.battleship.Observer;
import com.battleship.fields.ShipComponent;

/**
 * Created by casvd on 6-4-2017.
 */
public abstract class Ship extends Observer{
    public void AddComponent(ShipComponent subject) {
        subject.Attach(this);
    }
    public abstract String GetName();
    public abstract int GetLength();
    public abstract int GetHealth();
}
