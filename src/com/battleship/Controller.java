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

    public void SetupPlayers() {
        while (player1 == null) player1 = SetupPlayer("1");
        while (player2 == null) player2 = SetupPlayer("2");
    }

    private Player SetupPlayer(String id) {
       UI.printf("Player %s: ", id);
        String name = reader.next();
        if (name.isEmpty()) return null;
        return new Player(name);
    }

    public void SetupBoards() {
        SetupBoard(player1);
        SetupBoard(player2);
    }

    private void SetupBoard(Player player) {
        UI.println("========================================");
        UI.printf(UI.ANSI_PURPLE + "[%s] Setting up board %n" + UI.ANSI_RESET, player.GetName());
        player.InitBoard();
        PrintBoard(player, false);
        SetupShips(player);
        UI.clear();
        Sleep(2000);
    }

    private void PrintBoard(Player player, boolean enemy) {
        Field[][] board = player.GetBoard();
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
                    PrintFieldEnemy(field);
                } else {
                    PrintField(field);
                }
            }
            UI.println("");
        }
    }

    private void PrintField(Field field) {
        if (field instanceof Water) {
            UI.print("[ ]");
        } else if (field instanceof ShipComponent) {
            UI.print(UI.ANSI_GREEN + "[X]" + UI.ANSI_RESET);
        }
    }

    private void PrintFieldEnemy(Field field) {
        if (field instanceof Water) {
            if (field.GetFieldState().IsBombed()) {
                UI.print(UI.ANSI_BLUE + "[X]" + UI.ANSI_RESET);
            } else {
                UI.print("[ ]");
            }
        } else if (field instanceof ShipComponent) {
            if (field.GetFieldState().IsBombed()) {
                UI.print(UI.ANSI_RED + "[X]" + UI.ANSI_RESET);
            } else {
                UI.print("[ ]");
            }
        }
    }

    private void SetupShips(Player player) {
        Ship[] ships = player.GetShips();
        PrintShips(ships);
        for (Ship ship : ships) {
            SetupShip(player, ship);
            PrintBoard(player, false);
        }
    }

    private void SetupShip(Player player, Ship ship) {
        UI.print("Position of " + ship.GetName() + " (" + ship.GetLength() + ") :");
        String inputPosition = reader.next();
        int[] pos = InputToPosition(inputPosition);
        if (!IsPositionInRange(pos, player.GetBoard())) {
            //Position invalid, try again mate
            UI.println(UI.ANSI_YELLOW + "Did ya even look at teh board ya muppet,," + UI.ANSI_RESET);
            SetupShip(player, ship);
            return;
        }

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
                    UI.printf("- %s %n", dir);
                }
                UI.print("Direction: ");
                String inputDirection = reader.next();
                if (player.CanPlaceShip(pos[0], pos[1], InputDirectionToPosition(inputDirection), ship.GetLength())) {
                    //Set Ship
                    player.PlaceShip(pos[0], pos[1], InputDirectionToPosition(inputDirection), ship);
                    UI.println(UI.ANSI_GREEN + "Good stuff pirate *high fives in pirate*" + UI.ANSI_RESET);
                    isPlaced = true;
                } else {
                    UI.println(UI.ANSI_RED + "That wasnt an option ya fuckn weapon" + UI.ANSI_RESET);
                }
            }
        } else {
            UI.println(UI.ANSI_YELLOW + "Did you even try... No places here mate..." + UI.ANSI_RESET);
            SetupShip(player, ship);
        }
    }

    private void PrintShips(Ship[] ships) {
        for (Ship ship : ships) {
            UI.println(" (" + ship.GetLength() + ") " + ship.GetName());
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
            UI.println(UI.ANSI_RED + "Watch your input bro " + UI.ANSI_YELLOW + "[a-j][1-10]" + UI.ANSI_RESET);
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

    public boolean IsGameOnGoing() {
        return IsPlayerStillBreathing(player1) && IsPlayerStillBreathing(player2);
    }

    private boolean IsPlayerStillBreathing(Player player) {
        for (Ship ship : player.GetShips()) {
            if (ship.GetHealth() > 0) {
                return true;
            }
        }
        return false;
    }

    public void Play() {
        UI.println("========================================");
        if (turnOne) {
            PrintBoard(player2, true);
            Play(player1, player2);
            PrintBoard(player2, true);
        } else {
            PrintBoard(player1, true);
            Play(player2, player1);
            PrintBoard(player1, true);
        }
        turnOne = !turnOne;
        UI.clear();
        Sleep(2000);
    }

    private void Play(Player player, Player enemy) {
        UI.printf(UI.ANSI_PURPLE + "[%s] What position u trynna hit arrrr: " + UI.ANSI_RESET, player.GetName());
        String inputPosition = reader.next();
        int[] pos = InputToPosition(inputPosition);
        if (IsPositionInRange(pos, player.GetBoard())) {
            UI.println(enemy.Hit(pos));
        } else {
            UI.println(UI.ANSI_YELLOW + "Knob Head open yer eyes, dat aint on dis board" + UI.ANSI_RESET);
            Play(player, enemy);
        }
        Sleep(1000);
    }

    public void KudosToWinner() {
        String message = UI.ANSI_GREEN + "Kudos %s !" + UI.ANSI_RED + " Ya absobloodylutely beat up wazzock %s :)" + UI.ANSI_RESET;
        if (IsPlayerStillBreathing(player1)) {
            UI.printf(message, player1.GetName(), player2.GetName());
        } else if (IsPlayerStillBreathing(player2)) {
            UI.printf(message, player2.GetName(), player1.GetName());
        }
        Sleep(2500);
    }

    private void Sleep(int mili) {
        try {
            Thread.sleep(mili);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
