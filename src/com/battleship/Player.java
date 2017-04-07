package com.battleship;

import com.battleship.fields.Field;
import com.battleship.ship.Ship;

public class Player {

    /**
     * Fields
     */
    private String name;
    private Board board;

    /**
     * Constructor
     *
     * @param name of player
     */
    public Player(String name) {
        this.name = name;
        this.board = new Board();
    }

    /**
     * Get name
     *
     * @return name
     */
    public String getName() {
        return name;
    }


    /**
     * {@link Board#getBoard()}
     */
    public Field[][] getBoard() {
        return board.getBoard();
    }

    /**
     * {@link Board#initBoard()}
     */
    public void initBoard() {
        board.initBoard();
    }

    /**
     * {@link Board#getShips()}
     */
    public Ship[] getShips() {
        return board.getShips();
    }

    /**
     * {@link Board#canPlaceShip(int, int, int, int)}
     */
    public boolean canPlaceShip(int y, int x, int dir, int length) {
        return board.canPlaceShip(y, x, dir, length);
    }

    /**
     * {@link Board#placeShip(int, int, int, Ship)}
     */
    public void placeShip(int y, int x, int dir, Ship ship) {
        board.placeShip(y, x, dir, ship);
    }

    /**
     * {@link Board#hit(int[])}
     */
    public String hit(int[] pos) {
        return board.hit(pos);
    }
}
