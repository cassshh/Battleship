package com.battleship;

import java.util.Arrays;

/**
 * Created by casvd on 30-3-2017.
 */
public class Grid {
    private Field[][] grid;
    private Ship[] ships;

    Grid() {
        grid = new Field[10][10];
        fillGrid();
        ships = new Ship[]{
                new Ship("Aircraft Carrier", 5),
                new Ship("Battleship", 4),
                new Ship("Submarine", 3),
                new Ship("Destroyer", 3),
                new Ship("Patrol Boat", 2)
        };
    }

    private void fillGrid() {
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {
                grid[x][y] = new Water();
            }
        }
    }

    Field[][] getGrid() {
        return grid;
    }

    void hit(int x, int y) {
        if (x > 9 || y > 9) return;
        grid[x][y].hit();
    }

    Iterable<Ship> shipIterable() {
        return Arrays.asList(ships);
    }

    boolean isPositionAvailable(int[] pos) {
        if (grid[pos[0]][pos[1]] instanceof Water) {
            return true;
        }
        return false;
    }

    boolean[] getDirections(int[] pos, Ship ship) {
        return new boolean[] {
                isDirClear(pos, 0, 1, ship.getLength()),
                isDirClear(pos, 1, 0, ship.getLength()),
                isDirClear(pos, 0, -1, ship.getLength()),
                isDirClear(pos, -1, 0, ship.getLength())
        };
    }

    boolean isDirClear(int[] pos, int xDir, int yDir, int i) {
        int x = pos[0];
        int y = pos[1];

        if(xDir > 0) {
            if(x + i <= grid.length) {
                for (int j = 0; j < i; j++) {
                    if(grid[x+j][y] instanceof ShipComponent) {
                        return false;
                    }
                }
                return true;
            }
        } else if(xDir < 0) {
            if(x - i + 1 >= 0) {
                for (int j = 0; j < i; j++) {
                    if(grid[x-j][y] instanceof ShipComponent) {
                        return false;
                    }
                }
                return true;
            }
        } else if (yDir > 0) {
            if(y - i + 1 >= 0) {
                for (int j = 0; j < i; j++) {
                    if(grid[x][y-j] instanceof ShipComponent) {
                        return false;
                    }
                }
                return true;
            }
        } else if (yDir < 0) {
            if(y + i <= grid[x].length) {
                for (int j = 0; j < i; j++) {
                    if(grid[x][y+j] instanceof ShipComponent) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }
}
