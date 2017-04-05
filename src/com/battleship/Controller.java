package com.battleship;

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

    public void SetupPlayers() {
        while (player1 == null) player1 = SetupPlayer("1");
        while (player2 == null) player2 = SetupPlayer("2");
    }

    private Player SetupPlayer(String id) {
        System.out.printf("Player %s: ", id);
        String name = reader.next();
        if (name.isEmpty()) return null;
        return new Player(name);
    }

    public void SetupBoards() {
        SetupBoard(player1);
        SetupBoard(player2);
    }

    private void SetupBoard(Player player) {
        System.out.printf("[%s] Setting up board %n", player.GetName());
        player.InitBoard();
        PrintBoard(player);
        SetupShips(player);
    }

    private void PrintBoard(Player player) {
        Field[][] board = player.GetBoard();
        int sizeX = board.length;
        for (int i = 0; i <= sizeX; i++) {
            System.out.print(i == 0 ? "[ ]" : "[" + i + "]");
        }
        System.out.println();
        for (int i = 0; i < sizeX; i++) {
            char c = (char) ((int) 'a' + i);
            System.out.print("[" + c + "]");

            int sizeY = board[i].length;
            for (int j = 0; j < sizeY; j++) {
                //System.out.print("["+ i + "|" + j +"]");
                Field field = board[i][j];
                if (field instanceof Water) {
                    System.out.print("[ ]");
                } else if (field instanceof ShipComponent) {
                    System.out.print("[X]");
                }
            }
            System.out.println();
        }
    }

    private void SetupShips(Player player) {
        Ship[] ships = player.GetShips();
        PrintShips(ships);
        for (Ship ship : ships) {
            SetupShip(player, ship);
            PrintBoard(player);
        }
    }

    private void SetupShip(Player player, Ship ship) {
        System.out.print("Position of " + ship.GetName() + " (" + ship.GetLength() + ") :");
        String inputPosition = reader.next();
        int[] pos = InputToPosition(inputPosition);
        if (!IsPositionInRange(pos, player.GetBoard())) SetupShip(player, ship);

        List<String> directions = new ArrayList<>();
        if (player.CanPlaceShip(pos[0], pos[1], Board.UP, ship.GetLength())) {
            directions.add("Up");
        }
        if (player.CanPlaceShip(pos[0], pos[1], Board.RIGHT, ship.GetLength())) {
            directions.add("Right");
        }
        if (player.CanPlaceShip(pos[0], pos[1], Board.DOWN, ship.GetLength())) {
            directions.add("Down");
        }
        if (player.CanPlaceShip(pos[0], pos[1], Board.LEFT, ship.GetLength())) {
            directions.add("Left");
        }

        boolean isPlaced = false;
        if (directions.size() > 0) {
            while (!isPlaced) {
                for (String dir : directions) {
                    System.out.printf("- %s %n", dir);
                }
                System.out.print("Direction: ");
                String inputDirection = reader.next();
                if (player.CanPlaceShip(pos[0], pos[1], InputDirectionToPosition(inputDirection), ship.GetLength())) {
                    //Set Ship
                    player.PlaceShip(pos[0], pos[1], InputDirectionToPosition(inputDirection), ship);
                    System.out.println("Good stuff pirate");
                    isPlaced = true;
                } else {
                    System.out.println("That wasnt an option ya fuckn weapon");
                }
            }
        } else {
            System.out.println("Did you even try... No places here mate...");
            SetupShip(player, ship);
        }
    }

    private void PrintShips(Ship[] ships) {
        for (Ship ship : ships) {
            System.out.println(" (" + ship.GetLength() + ") " + ship.GetName());
        }
    }

    private int[] InputToPosition(String input) {
        int[] pos = {-1, -1};
        char posY = input.charAt(0);
        String posX = input.substring(1);
        pos[0] = (int) posY - (int) 'a';
        try {
            pos[1] = Integer.parseInt(posX) - 1;
        } catch (NumberFormatException ex) {
            System.out.println("Watch your input bro");
        }
        return pos;
    }

    private boolean IsPositionInRange(int[] pos, Field[][] board) {
        int y = pos[0];
        int x = pos[1];
        if (y >= 0 && y < board.length
                && x >= 0 && x < board[y].length) return true;
        return false;
    }

    private int InputDirectionToPosition(String input) {
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
}
