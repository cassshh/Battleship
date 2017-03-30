package com.battleship;

import java.util.Arrays;

/**
 * Created by casvd on 30-3-2017.
 */
class Player {
    private String name;
    private Grid grid;

    Player(String name) {
        this.name = name;
        grid = new Grid();
    }

    String getName() {
        return name;
    }

    Iterable<Ship> shipIterable () {
        return grid.shipIterable();
    }

    void hit(int x, int y) {
        grid.hit(x, y);
    }
}
