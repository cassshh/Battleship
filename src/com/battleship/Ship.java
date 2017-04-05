package com.battleship;

/**
 * Created by casvd on 3-4-2017.
 */
public class Ship {
    private String name;
    private int length;

    Ship(String name, int length) {
        this.name = name;
        this.length = length;
    }

    public String GetName() {
        return name;
    }

    public int GetLength() {
        return length;
    }
}
