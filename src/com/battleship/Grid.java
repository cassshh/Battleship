package com.battleship;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by casvd on 30-3-2017.
 */
class Grid {
    private Field[][] grid;
    private Ship[] ships;

    Grid () {
        grid = new Field[10][10];
        ships = new Ship[] {
                new Ship("Aircraft Carrier", 5),
                new Ship("Battleship", 4),
                new Ship("Submarine", 3),
                new Ship("Destroyer", 3),
                new Ship("Patrol Boat", 2)
        };
    }

    void hit(int x, int y) {
        if(x > 9 || y > 9) return;
        grid[x][y].hit();
    }

    Iterable<Ship> shipIterable () {
        Iterable<Ship> iShip = Arrays.asList(ships);
        return iShip;
    }

    Field[][] getGrid () {
        return grid;
    }
}
