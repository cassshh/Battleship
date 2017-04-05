package com.battleship;

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
        InitBoard();
        InitShips();
    }

    public void InitBoard() {
        board = new Field[SIZE][SIZE];
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                /*int r = new Random().nextInt(5);
                board[y][x] = r == 1 ? new ShipComponent() : new Water();*/
                board[y][x] = new Water();
            }
        }
    }

    public void InitShips() {
        ships = new Ship[]{
                new Ship("Aircraft Carrier", 5),
                new Ship("Battleship", 4),
                new Ship("Submarine", 3),
                new Ship("Destroyer", 3),
                new Ship("Patrol Boat", 2)
        };
    }

    public Field[][] GetBoard() {
        return board;
    }

    public Ship[] GetShips (){
        return ships;
    }

    public boolean CanPlaceShip(int y, int x, int dir, int length) {
        if ((dir == UP && y - length + 1 >= 0)
                || (dir == RIGHT && x + length <= SIZE)
                || (dir == DOWN && y + length <= SIZE)
                || (dir == LEFT && x - length + 1 >= 0)) {
            Field[] fields = GetFields(y, x, dir, length);
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

    private Field[] GetFields(int y, int x, int dir, int length) {
        Field[] fields = new Field[length];
        for (int i = 0; i < length; i++) {
            Field field = null;

            if (dir == UP) field = GetAt(y - i, x);
            else if (dir == RIGHT) field = GetAt(y, x + i);
            else if (dir == DOWN) field = GetAt(y + i, x);
            else if (dir == LEFT) field = GetAt(y, x - i);

            fields[i] = field;
        }
        return fields;
    }

    private Field GetAt(int y, int x) {
        return board[y][x];
    }

    public void PlaceShip(int y, int x, int dir, Ship ship) {
        for (int i = 0; i < ship.GetLength(); i++) {
            if (dir == UP) board[y - i][x] = new ShipComponent();
            else if (dir == RIGHT) board[y][x + i] = new ShipComponent();
            else if (dir == DOWN) board[y + i][x] = new ShipComponent();
            else if (dir == LEFT) board[y][x - i] = new ShipComponent();
        }
    }
}