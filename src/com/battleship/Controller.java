package com.battleship;

import com.battleship.fields.Field;
import com.battleship.fields.ShipComponent;
import com.battleship.fields.Water;
import com.battleship.ships.Ship;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by casvd on 3-4-2017.
 */
public class Controller {
    private Player player1;
    private Player player2;
    private Scanner reader = new Scanner(System.in);
    private boolean turnOne = true;

    public void setupPlayers() {
        while (player1 == null) player1 = setupPlayer("1");
        while (player2 == null) player2 = setupPlayer("2");
    }

    private Player setupPlayer(String id) {
       UI.printf("Player %s: ", id);
        String name = reader.next();
        if (name.isEmpty()) return null;
        return new Player(name);
    }

    public void setupBoards() {
        setupBoard(player1);
        setupBoard(player2);
    }

    private void setupBoard(Player player) {
        UI.println("========================================");
        UI.printf(UI.ANSI_PURPLE + "[%s] Setting up board %n" + UI.ANSI_RESET, player.getName());
        player.initBoard();
        printBoard(player, false);
        setupShips(player);
        UI.clear();
        sleep(2000);
    }

    private void printBoard(Player player, boolean enemy) {
        Field[][] board = player.getBoard();
        int sizeX = board.length;
        for (int i = 0; i <= sizeX; i++) {
            UI.print(i == 0 ? "[ ]" : "[" + i + "]");
        }
        UI.println("");
        for (int i = 0; i < sizeX; i++) {
            char c = (char) ((int) 'a' + i);
            UI.print("[" + c + "]");

            int sizeY = board[i].length;
            for (int j = 0; j < sizeY; j++) {
                //UI.print("["+ i + "|" + j +"]");
                Field field = board[i][j];
                if (enemy) {
                    printFieldEnemy(field);
                } else {
                    printField(field);
                }
            }
            UI.println("");
        }
    }

    private void printField(Field field) {
        if (field instanceof Water) {
            UI.print("[ ]");
        } else if (field instanceof ShipComponent) {
            UI.print(UI.ANSI_GREEN + "[X]" + UI.ANSI_RESET);
        }
    }

    private void printFieldEnemy(Field field) {
        if (field instanceof Water) {
            if (field.getFieldState().isBombed()) {
                UI.print(UI.ANSI_BLUE + "[X]" + UI.ANSI_RESET);
            } else {
                UI.print("[ ]");
            }
        } else if (field instanceof ShipComponent) {
            if (field.getFieldState().isBombed()) {
                UI.print(UI.ANSI_RED + "[X]" + UI.ANSI_RESET);
            } else {
                UI.print("[ ]");
            }
        }
    }

    private void setupShips(Player player) {
        Ship[] ships = player.getShips();
        printShips(ships);
        for (Ship ship : ships) {
            setupShip(player, ship);
            printBoard(player, false);
        }
    }

    private void setupShip(Player player, Ship ship) {
        UI.print("Position of " + ship.getName() + " (" + ship.getLength() + ") :");
        String inputPosition = reader.next();
        int[] pos = inputToPosition(inputPosition);
        if (!isPositionInRange(pos, player.getBoard())) {
            //Position invalid, try again mate
            UI.println(UI.ANSI_YELLOW + "Did ya even look at teh board ya muppet,," + UI.ANSI_RESET);
            setupShip(player, ship);
            return;
        }

        List<String> directions = new ArrayList<>();
        if (player.canPlaceShip(pos[0], pos[1], Board.UP, ship.getLength())) {
            directions.add("Up");
        }
        if (player.canPlaceShip(pos[0], pos[1], Board.RIGHT, ship.getLength())) {
            directions.add("Right");
        }
        if (player.canPlaceShip(pos[0], pos[1], Board.DOWN, ship.getLength())) {
            directions.add("Down");
        }
        if (player.canPlaceShip(pos[0], pos[1], Board.LEFT, ship.getLength())) {
            directions.add("Left");
        }

        boolean isPlaced = false;
        if (directions.size() > 0) {
            while (!isPlaced) {
                for (String dir : directions) {
                    UI.printf("- %s %n", dir);
                }
                UI.print("Direction: ");
                String inputDirection = reader.next();
                if (player.canPlaceShip(pos[0], pos[1], inputDirectionToPosition(inputDirection), ship.getLength())) {
                    //Set Ship
                    player.placeShip(pos[0], pos[1], inputDirectionToPosition(inputDirection), ship);
                    UI.println(UI.ANSI_GREEN + "Good stuff pirate *high fives in pirate*" + UI.ANSI_RESET);
                    isPlaced = true;
                } else {
                    UI.println(UI.ANSI_RED + "That wasnt an option ya fuckn weapon" + UI.ANSI_RESET);
                }
            }
        } else {
            UI.println(UI.ANSI_YELLOW + "Did you even try... No places here mate..." + UI.ANSI_RESET);
            setupShip(player, ship);
        }
    }

    private void printShips(Ship[] ships) {
        for (Ship ship : ships) {
            UI.println(" (" + ship.getLength() + ") " + ship.getName());
        }
    }

    private int[] inputToPosition(String input) {
        int[] pos = {-1, -1};
        char posY = input.charAt(0);
        String posX = input.substring(1);
        pos[0] = (int) posY - (int) 'a';
        try {
            pos[1] = Integer.parseInt(posX) - 1;
        } catch (NumberFormatException ex) {
            UI.println(UI.ANSI_RED + "Watch your input bro " + UI.ANSI_YELLOW + "[a-j][1-10]" + UI.ANSI_RESET);
        }
        return pos;
    }

    private boolean isPositionInRange(int[] pos, Field[][] board) {
        int y = pos[0];
        int x = pos[1];
        if (y >= 0 && y < board.length
                && x >= 0 && x < board[y].length) return true;
        return false;
    }

    private int inputDirectionToPosition(String input) {
        switch (input.toLowerCase()) {
            case "u":
            case "up":
                return Board.UP;
            case "r":
            case "right":
                return Board.RIGHT;
            case "d":
            case "down":
                return Board.DOWN;
            case "l":
            case "left":
                return Board.LEFT;
            default:
                return -1;
        }
    }

    public boolean isGameOnGoing() {
        return isPlayerStillBreathing(player1) && isPlayerStillBreathing(player2);
    }

    private boolean isPlayerStillBreathing(Player player) {
        for (Ship ship : player.getShips()) {
            if (ship.getHealth() > 0) {
                return true;
            }
        }
        return false;
    }

    public void play() {
        UI.println("========================================");
        if (turnOne) {
            printBoard(player2, true);
            play(player1, player2);
            printBoard(player2, true);
        } else {
            printBoard(player1, true);
            play(player2, player1);
            printBoard(player1, true);
        }
        turnOne = !turnOne;
        UI.clear();
        sleep(2000);
    }

    private void play(Player player, Player enemy) {
        UI.printf(UI.ANSI_PURPLE + "[%s] What position u trynna hit arrrr: " + UI.ANSI_RESET, player.getName());
        String inputPosition = reader.next();
        int[] pos = inputToPosition(inputPosition);
        if (isPositionInRange(pos, player.getBoard())) {
            UI.println(enemy.hit(pos));
        } else {
            UI.println(UI.ANSI_YELLOW + "Knob Head open yer eyes, dat aint on dis board" + UI.ANSI_RESET);
            play(player, enemy);
        }
        sleep(1000);
    }

    public void kudosToWinner() {
        String message = UI.ANSI_GREEN + "Kudos %s !" + UI.ANSI_RED + " Ya absobloodylutely beat up wazzock %s :)" + UI.ANSI_RESET;
        if (isPlayerStillBreathing(player1)) {
            UI.printf(message, player1.getName(), player2.getName());
        } else if (isPlayerStillBreathing(player2)) {
            UI.printf(message, player2.getName(), player1.getName());
        }
        sleep(2500);
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
