package com.battleship;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws InterruptedException, IOException {
	// write your code here
        Field f = new Water();
        Field f1 = new ShipComponent();
        System.out.println(f.hit());
        System.out.println(f1.hit());
        for (int i = 0; i <= 100; i++) {
            String data = "\r" + i ;
            System.out.write(data.getBytes());
            Thread.sleep(100);
        }
    }
}
