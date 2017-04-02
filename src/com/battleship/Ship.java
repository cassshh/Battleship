package com.battleship;

/**
 * Created by casvd on 30-3-2017.
 */
public class Ship {
    private String name;
    private int length;

    Ship(String name, int length) {
        this.name = name;
        this.length = length;
    }

    String getName() {
        return name;
    }

    int getLength () {
        return length;
    }
}
