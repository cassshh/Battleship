package com.battleship;

import java.util.Scanner;

/**
 * Created by casvd on 30-3-2017.
 */
class Controller {

    private static Controller instance;
    private static UI ui;
    private Scanner reader = new Scanner(System.in);

    private Player player1;
    private Player player2;

    private Controller() {
        ui = UI.getInstance();
        setupPlayers();
        setupGame(player1);
        setupGame(player2);
    }

    static Controller getInstance() {
        if (instance == null) instance = new Controller();
        return instance;
    }

    private void setupPlayers() {
        ui.println("Setting up the Players...");
        ui.println("Please insert player names");
        while (player1 == null) {
            ui.print("Player 1: ");
            String name = reader.next();
            if(name.isEmpty()) return;
            player1 = new Player(name);
        }
        while (player2 == null) {
            ui.print("Player 2: ");
            String name = reader.next();
            if(name.isEmpty()) return;
            player2 = new Player(name);
        }
        ui.println("Player names set");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ui.clear();
    }

    private void setupGame(Player player) {
        ui.println("Setting up the board for " + player.getName());
        Iterable<Ship> ships = player.shipIterable();
        listShips(ships);
        for (Ship ship : ships) {
            int[] pos = new int[] {-1, -1};
            while (pos[0] < 0 || pos[1] < 0) {
                ui.print("Position of " + ship.getName() + " (" + ship.getLength() + ") :");
                String input = reader.next();
                if(validPosition(input)) {
                    pos = inputToPosition(input);
                    ui.println("Input (" + input + ") set");
                    //TODO Set ship on grid and check for location...
                } else {
                    ui.println("Input (" + input + ") is not valid...");
                }
            }
        }
    }

    private void listShips (Iterable<Ship> ships) {
        ui.println("====================");
        for (Ship ship : ships) {
            ui.println(" (" + ship.getLength() + ") " + ship.getName());
        }
        ui.println("====================");
    }

    private boolean validPosition(String position) {
        if(position.length() == 2 || position.length() == 3) {
            int[] pos = inputToPosition(position);
            if(pos[0] >= 0 && pos[0] < 10 && pos[1] > 0 && pos[1] <= 10) {
                return true;
            }
        }
        return false;
    }

    private int[] inputToPosition(String input){
        int[] pos = new int[2];
        char posX = input.charAt(0);
        String posY = input.substring(1);
        pos[0] = (int)posX - (int)'a' + 1;
        pos[1] = Integer.parseInt(posY);
        return pos;
    }
}
