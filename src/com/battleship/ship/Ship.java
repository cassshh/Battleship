package com.battleship.ship;

import com.battleship.fields.ShipComponent;

public abstract class Ship extends Observer {
    /**
     * Add component
     *
     * @param subject component of ship
     */
    public void addComponent(ShipComponent subject) {
        subject.attach(this);
    }

    /**
     * Get name of ship
     *
     * @return name
     */
    public abstract String getName();

    /**
     * Get length of ship
     *
     * @return length
     */
    public abstract int getLength();

    /**
     * Get health of ship
     *
     * @return health
     */
    public abstract int getHealth();
}
