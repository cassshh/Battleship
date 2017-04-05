package com.battleship;

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

    public String GetName() {
        return name;
    }

    public Field[][] GetBoard() {
        return board.GetBoard();
    }

    public void InitBoard() {
        board.InitBoard();
    }

    public Ship[] GetShips(){
        return board.GetShips();
    }

    public boolean CanPlaceShip(int y, int x, int dir, int length){
        return board.CanPlaceShip(y, x, dir, length);
    }

    public void PlaceShip(int y, int x, int dir, Ship ship){
        board.PlaceShip(y, x, dir, ship);
    }
}
