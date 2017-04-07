package com.battleship;

import com.battleship.fields.Field;
import com.battleship.fields.ShipComponent;
import com.battleship.fields.Water;
import com.battleship.ships.Ship;
/**
 * Created by casvd on 3-4-2017.
 */
public class Board {
    private final int SIZE = 10;
    private Field[][] board;
    private Ship[] ships;

    public final static int UP = 0;
    public final static int RIGHT = 1;
    public final static int DOWN = 2;
    public final static int LEFT = 3;

    public Board() {
        initBoard();
        initShips();
    }

    public void initBoard() {
        board = new Field[SIZE][SIZE];
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                board[y][x] = new Water();
            }
        }
    }

    public void initShips() {
        ShipFactory shipFactory = new ShipFactory();
        ships = new Ship[]{
                shipFactory.makeShip(ShipType.AIRCRAFT_CARRIER),
                shipFactory.makeShip(ShipType.BATTLESHIP),
                shipFactory.makeShip(ShipType.SUBMARINE),
                shipFactory.makeShip(ShipType.DESTROYER),
                shipFactory.makeShip(ShipType.PATROL_BOAT)
        };
    }

    public Field[][] getBoard() {
        return board;
    }

    public Ship[] getShips() {
        return ships;
    }

    public boolean canPlaceShip(int y, int x, int dir, int length) {
        if ((dir == UP && y - length + 1 >= 0)
                || (dir == RIGHT && x + length <= SIZE)
                || (dir == DOWN && y + length <= SIZE)
                || (dir == LEFT && x - length + 1 >= 0)) {
            Field[] fields = getFields(y, x, dir, length);
            for (Field field : fields) {
                if (field instanceof ShipComponent) {
                    //Ship is in its way. abort mission.. ABORT
                    return false;
                }
            }
            return true;
        }
        //Outta reach
        return false;
    }

    private Field[] getFields(int y, int x, int dir, int length) {
        Field[] fields = new Field[length];
        for (int i = 0; i < length; i++) {
            Field field = null;

            if (dir == UP) field = getAt(y - i, x);
            else if (dir == RIGHT) field = getAt(y, x + i);
            else if (dir == DOWN) field = getAt(y + i, x);
            else if (dir == LEFT) field = getAt(y, x - i);

            fields[i] = field;
        }
        return fields;
    }

    private Field getAt(int y, int x) {
        return board[y][x];
    }

    public void placeShip(int y, int x, int dir, Ship ship) {
        for (int i = 0; i < ship.getLength(); i++) {
            ShipComponent shipComponent = new ShipComponent();
            if (dir == UP) board[y - i][x] = shipComponent;
            else if (dir == RIGHT) board[y][x + i] = shipComponent;
            else if (dir == DOWN) board[y + i][x] = shipComponent;
            else if (dir == LEFT) board[y][x - i] = shipComponent;
            ship.addComponent(shipComponent);
        }
    }

    public String hit(int[] pos) {
        return getAt(pos[0], pos[1]).hit();
    }
}
