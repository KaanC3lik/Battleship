package battleship;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static boolean hata = false;
    static boolean resume = false;
    public static void delete(StringBuilder letters, char vCoordinate, char vCoordinate2) {
        for (int j = 0; j < letters.length(); j++) {
            if (vCoordinate == letters.charAt(j)) {
                letters.delete(j + 1, letters.length());
                break;
            }
            if (vCoordinate2 == letters.charAt(j)) {
                letters.delete(0, j);
                j = 0;
            }
        }
    }

    public static void replace(StringBuilder letters, StringBuilder clearSea, int intHCoordinate, int largeCounter, int[][] damageControl) {
        hata = false;
        int counter = 0;
        for (int i = 0; i < clearSea.length(); i++) {
            for (int j = 0; j < letters.length(); j++) {
                if (clearSea.charAt(i) == letters.charAt(j)) {
                    try {
                        if (clearSea.charAt(i + intHCoordinate + 1) != 'O' && clearSea.charAt(i + intHCoordinate - 1) != 'O' && clearSea.charAt(i + intHCoordinate - 11) != 'O' && clearSea.charAt(i + intHCoordinate + 11) != 'O') {
                        }else {
                            hata = true;
                            break;
                        }
                    }catch (StringIndexOutOfBoundsException ignored) {

                    }
                }
            }
            if (hata) {
                System.out.println("Error! You placed it too close to another one. Try again:");
                break;
            }
        }
        if (!hata){
            for (int i = 0; i < clearSea.length(); i++) {
                for (int j = 0; j < letters.length(); j++) {
                    if (clearSea.charAt(i) == letters.charAt(j)) {
                        clearSea.replace(i + intHCoordinate, i + intHCoordinate + 1, "O");
                        damageControl[largeCounter][counter] = i + intHCoordinate;
                        counter++;
                        /*System.out.println(clearSea.charAt(i));
                                    System.out.println(i);
                                    System.out.println(j);
                                    System.out.print(largeCounter);
                                    System.out.println(counter);
                                    System.out.println(Arrays.deepToString(damageControl));

                                     */
                    }
                }
            }
        }
    }
    public static void placeShip(int cells, StringBuilder clearSea, String shipType, int[][] damageControl, int largeCounter) {
        Scanner scanner = new Scanner(System.in);
        hata = false;
        int counter = 0;
        while (true) {
            String input = scanner.nextLine();
            String hCoordinate = "";
            String hCoordinate2 = "";
            char vCoordinate = input.charAt(0);
            char vCoordinate2 = 0;

            for (int i = 1; i < input.length(); i++) {
                StringBuilder letters = new StringBuilder("ABCDEFGHIJ");
                for (int j = 0; j < letters.length(); j++) {
                    if (input.charAt(i) == letters.charAt(j)) {
                        String firsPart = input.substring(0, i);
                        if (firsPart.length() == 3) {
                            hCoordinate = input.substring(1, 2);
                        }else {
                            hCoordinate = input.substring(1, 3);
                        }
                        hCoordinate2 = input.substring(i + 1);
                        vCoordinate2 = input.charAt(i);
                    }
                }
            }

            int intHCoordinate = (Integer.parseInt(hCoordinate));
            int intHCoordinate2 = (Integer.parseInt(hCoordinate2));

            if (Math.abs(intHCoordinate - intHCoordinate2) + 1 != cells && Math.abs(vCoordinate2 - vCoordinate) + 1 != cells) {
                System.out.println("Error! Wrong length of the" + shipType + "! Try again:");
                continue;
            }
            StringBuilder o = new StringBuilder();
            o.append("O".repeat(Math.max(0, cells)));
            if (vCoordinate == vCoordinate2) {
                for (int i = 0; i < clearSea.length(); i++) {
                    if (clearSea.charAt(i) == vCoordinate) {
                        if (clearSea.charAt(i + 1) != 'O' || clearSea.charAt(i - 1) != 'O' || clearSea.charAt(i + 11) != 'O' || clearSea.charAt(i - 11) != 'O') {
                            if (intHCoordinate < intHCoordinate2) {
                                clearSea.replace(i + intHCoordinate, i  + intHCoordinate2 + 1, o.toString());
                                for (int j = intHCoordinate; j <= intHCoordinate2; j++) {
                                    damageControl[largeCounter][counter] = i + j;
                                    counter++;
                                    /*System.out.println(clearSea.charAt(i));
                                    System.out.println(i);
                                    System.out.println(j);
                                    System.out.print(largeCounter);
                                    System.out.println(counter);
                                    System.out.println(Arrays.deepToString(damageControl));

                                     */
                                }
                            }else {
                                clearSea.replace(i + intHCoordinate2, i + intHCoordinate + 1, o.toString());
                                for (int j = intHCoordinate2; j <= intHCoordinate; j++) {
                                    damageControl[largeCounter][counter] = i + j;
                                    counter++;
                                    /*System.out.println(clearSea.charAt(i));
                                    System.out.println(i);
                                    System.out.println(j);
                                    System.out.print(largeCounter);
                                    System.out.println(counter);
                                    System.out.println(Arrays.deepToString(damageControl));

                                     */
                                }
                            }
                        }else {
                            System.out.println("Error! You placed it too close to another one. Try again:");
                            hata = true;
                            break;
                        }
                    }
                }
            } else if (hCoordinate.equals(hCoordinate2)) {
                StringBuilder letters = new StringBuilder("ABCDEFGHIJ");
                if (vCoordinate > vCoordinate2) {
                    delete(letters, vCoordinate, vCoordinate2);
                    replace(letters, clearSea, intHCoordinate, largeCounter, damageControl);
                } else {
                    delete(letters, vCoordinate2, vCoordinate);
                    replace(letters, clearSea, intHCoordinate, largeCounter, damageControl);
                }
            }else {
                System.out.println("Error! Wrong ship location! Try again:");
                continue;
            }
            if (hata) {
                continue;
            }
            break;
        }
    }

    public static void placeAllShips (StringBuilder clearSea, int[][] damageControl) {
        Scanner scanner = new Scanner(System.in);
        int largeCounter = 0;

        for (int k = 5; k >= 1; k--, largeCounter++) {
            String shipType = "";
            int cells = k;
            switch (k) {
                case 5 -> shipType = "Aircraft Carrier";
                case 4 -> shipType = "Battleship";
                case 3 -> shipType = "Submarine";
                case 2 -> {
                    shipType = "Cruiser";
                    cells = 3;
                }
                case 1 -> {
                    shipType = "Destroyer";
                    cells = 2;
                }
            }
            System.out.println("Enter the coordinates of the " + shipType +" (" +  cells + "cells):");
            placeShip(cells, clearSea, shipType, damageControl, largeCounter);
            newField(clearSea);
        }
        System.out.println(Arrays.deepToString(damageControl));
        System.out.println();
        System.out.println("Press Enter and pass the move to another player\n" +
                "...");
        scanner.nextLine();
    }

    public static void hitShip(StringBuilder hittedShip, StringBuilder fogOfWar, int[][] damageControl, boolean[] sunk) {
        Scanner scanner = new Scanner(System.in);
        resume = false;
        while (!resume) {
            String input = scanner.nextLine();
            char vCoordinate = input.charAt(0);
            String hCoordinate = input.substring(1);
            int wrongCoordinate = 0;
            int intHCoordinate = (Integer.parseInt(hCoordinate));
            boolean hit = false;
            StringBuilder letters = new StringBuilder("ABCDEFGHIJ");
            for (int j = 0; j < letters.length(); j++) {
                if (input.charAt(0) == letters.charAt(j)) {
                    break;
                }else {
                    wrongCoordinate++;
                }
            }
            if (wrongCoordinate == letters.length() || intHCoordinate > 10) {
                System.out.println("Error! You entered the wrong coordinates! Try again:");
                continue;
            }

            for (int i = 0; i < hittedShip.length(); i++) {
                if (hittedShip.charAt(i) == vCoordinate) {
                    if (hittedShip.charAt(i + intHCoordinate) == '~') {
                        hittedShip.replace(i + intHCoordinate, i + intHCoordinate + 1, "M");
                        fogOfWar.replace(i + intHCoordinate, i + intHCoordinate + 1, "M");
                        System.out.println("You missed!");
                        System.out.println("Press Enter and pass the move to another player");
                        System.out.println("...");
                        scanner.nextLine();
                        resume = true;
                        break;
                    }else if (hittedShip.charAt(i + intHCoordinate) == 'O'){
                        hittedShip.replace(i + intHCoordinate, i + intHCoordinate + 1, "X");
                        fogOfWar.replace(i + intHCoordinate, i + intHCoordinate + 1, "X");
                        hit = true;
                        break;
                    }
                }
            }
            if (resume) {
                continue;
            }
            if (!sunk[0]) {
                sunk[0] = hittedShip.charAt(damageControl[0][0]) == 'X' && hittedShip.charAt(damageControl[0][1]) == 'X' && hittedShip.charAt(damageControl[0][2]) == 'X' && hittedShip.charAt(damageControl[0][3]) == 'X' && hittedShip.charAt(damageControl[0][4]) == 'X';
            }
            if (!sunk[1]) {
                sunk[1] = hittedShip.charAt(damageControl[1][0]) == 'X' && hittedShip.charAt(damageControl[1][1]) == 'X' && hittedShip.charAt(damageControl[1][2]) == 'X' && hittedShip.charAt(damageControl[1][3]) == 'X';
            }
            if (!sunk[2]) {
                sunk[2] = hittedShip.charAt(damageControl[2][0]) == 'X' && hittedShip.charAt(damageControl[2][1]) == 'X' && hittedShip.charAt(damageControl[2][2]) == 'X';
            }
            if (!sunk[3]) {
                sunk[3] = hittedShip.charAt(damageControl[3][0]) == 'X' && hittedShip.charAt(damageControl[3][1]) == 'X' && hittedShip.charAt(damageControl[3][2]) == 'X';
            }
            if (!sunk[4]) {
                sunk[4] = hittedShip.charAt(damageControl[4][0]) == 'X' && hittedShip.charAt(damageControl[4][1]) == 'X';
            }
            if (sunk[0] && sunk[1] && sunk[2] && sunk[3] && sunk[4]) {
                System.out.println("You sank the last ship. You won. Congratulations!");
            } else if (sunk[0] || sunk[1] || sunk[2] || sunk[3] || sunk[4]) {

                System.out.println("You sank a ship!");
                System.out.println("Press Enter and pass the move to another player");
                System.out.println("...");
                scanner.nextLine();
            }else if (hit) {
                System.out.println("You hit a ship!");
                System.out.println("Press Enter and pass the move to another player");
                System.out.println("...");
                scanner.nextLine();
            }
            break;
        }
    }

    public static void newField(StringBuilder clearSea) {
        for (int i = 0; i < clearSea.length(); i++) {
            if (i < 9) {
                System.out.print(" " + clearSea.charAt(i));
            } else if (i == 9) {
                System.out.print(" " + clearSea.charAt(i) + clearSea.charAt(i + 1));
            } else if (i == 10) {

            } else {
                System.out.print(clearSea.charAt(i) + " ");
                if (i == (i * 11) - 1)  {
                    System.out.println();
                }
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String sea = ("""
                  1 2 3 4 5 6 7 8 9 10
                A ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                B ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                C ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                D ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                E ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                F ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                G ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                H ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                I ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                J ~ ~ ~ ~ ~ ~ ~ ~ ~ ~""");

        StringBuilder player1ClearSea = new StringBuilder(sea.replace(" ", ""));
        StringBuilder player1FogOfWar = new StringBuilder(player1ClearSea);

        StringBuilder player2ClearSea = new StringBuilder(player1ClearSea);
        StringBuilder player2FogOfWar = new StringBuilder(player2ClearSea);

        int[][] damageControl = new int[5][5];
        int[][] damageControl2 = new int[5][5];

        System.out.println("Player 1, place your ships on the game field");
        newField(player1ClearSea);
        placeAllShips(player1ClearSea, damageControl);

        System.out.println("Player 2, place your ships to the game field");
        newField(player2ClearSea);
        placeAllShips(player2ClearSea, damageControl2);

        resume = false;
        boolean[] sunk = new boolean[5];
        while (!resume) {

            newField(player1FogOfWar);
            System.out.println("---------------------");
            newField(player1ClearSea);
            System.out.println("Player 1, it's your turn:");
            hitShip(player2ClearSea, player1FogOfWar, damageControl2, sunk);

            newField(player2FogOfWar);
            System.out.println("---------------------");
            newField(player2ClearSea);
            System.out.println("Player 2, it's your turn:");
            hitShip(player1ClearSea, player2FogOfWar, damageControl, sunk);
        }
    }
}