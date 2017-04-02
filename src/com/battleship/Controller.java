package com.battleship;

import java.util.Scanner;

/**
 * Created by casvd on 30-3-2017.
 */
public class Controller {

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
        printBoard(player);
        Iterable<Ship> ships = player.shipIterable();
        listShips(ships);
        for (Ship ship : ships) {
            int[] pos = {-1, -1};
            while (pos[0] < 0 || pos[1] < 0) {
                ui.print("Position of " + ship.getName() + " (" + ship.getLength() + ") :");
                String input = reader.next();
                if(validPosition(input)) {
                    int[] temp = inputToPosition(input);
                    if(player.isPositionAvailable(temp)){
                        boolean[] directions = player.getDirections(temp, ship);
                        if(directions[0]){
                            ui.println("Up is freeee...");
                        }if(directions[1]){
                            ui.println("Right is freeee...");
                        }if(directions[2]){
                            ui.println("Down is freeee...");
                        }if(directions[3]){
                            ui.println("Left is freeee...");
                        }
                        //TODO Set ship on grid
                        pos = temp;
                    } else {
                        ui.println("Input (" + input + ") is not available (:");
                    }
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
        pos[0] = (int)posX - (int)'a';
        pos[1] = Integer.parseInt(posY);
        return pos;
    }

    private void printBoard(Player player) {
        Field[][] grid = player.getGrid().getGrid();
        for (int i = 0; i <= grid.length; i ++){
            ui.print(i == 0 ? "[ ]" :"[" + i + "]");
        }
        ui.println("");
        for (int x = 0; x < grid.length; x++) {
            char c = (char)((int)'a' + x);
            ui.print("[" + c + "]");
            for (Field field: grid[x]) {
                if(field instanceof Water) {
                    ui.print("[ ]");
                } else if (field instanceof ShipComponent) {
                    ui.print(UI.ANSI_CYAN + "[X]" + UI.ANSI_RESET);
                }
            }
            ui.println("");
        }
    }
}
