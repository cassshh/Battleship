package com.battleship;

import com.battleship.fields.Field;
import com.battleship.ships.Ship;

/**
 * Created by casvd on 3-4-2017.
 */
public class Player {
    private String name;
    private Board board;

    public Player(String name) {
        this.name = name;
        this.board = new Board();
    }

    public String getName() {
        return name;
    }

    public Field[][] getBoard() {
        return board.getBoard();
    }

    public void initBoard() {
        board.initBoard();
    }

    public Ship[] getShips(){
        return board.getShips();
    }

    public boolean canPlaceShip(int y, int x, int dir, int length){
        return board.canPlaceShip(y, x, dir, length);
    }

    public void placeShip(int y, int x, int dir, Ship ship){
        board.placeShip(y, x, dir, ship);
    }

    public String hit(int[] pos) {
        return board.hit(pos);
    }
}
