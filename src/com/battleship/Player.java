package com.battleship;

/**
 * Created by casvd on 30-3-2017.
 */
public class Player {
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

    boolean isPositionAvailable(int[] pos) {
        return grid.isPositionAvailable(pos);
    }

    boolean[] getDirections(int[] pos, Ship ship) {
        return grid.getDirections(pos, ship);
    }

    Grid getGrid() {
        return grid;
    }
}
