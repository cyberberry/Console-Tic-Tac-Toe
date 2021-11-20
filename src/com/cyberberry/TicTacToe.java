package com.cyberberry;

import java.util.*;

public class TicTacToe {

    static ArrayList<Integer> playerPositions = new ArrayList<>();
    static ArrayList<Integer> cpuPositions = new ArrayList<>();

    public static void main(String[] args) {

        char[][] gameBoard = {
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}
        };

        while (true) {
            makeMove(gameBoard, "player");
            makeMove(gameBoard, "cpu");
            printTheGameBoard(gameBoard);
            String winning = checkTheWinner();

            if (winning.equalsIgnoreCase("")) {
                continue;
            } else {
                System.out.println(winning);
                break;
            }
        }


    }

    protected static void printTheGameBoard(char[][] gameBoard) {
        Arrays.stream(gameBoard).forEach(System.out::println);
    }


    protected static void makeMove(char[][] gameBoard, String user) {

        char symbol = ' ';

        int pos = 0;

        if (user.equalsIgnoreCase("player")) {

            symbol = 'X';
            pos = getThePosForPlayer();
            playerPositions.add(pos);


        } else {

            if (playerPositions.size() + cpuPositions.size() == 9) {
                return;
            }
            symbol = '0';
            pos = getThePosForCpu();
            cpuPositions.add(pos);
        }

        switch (pos) {
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                break;

        }
    }


    protected static int getThePosForPlayer() {
        int position = 0;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please the placement from 1 to 9");
        position = scanner.nextInt();

        while (playerPositions.contains(position) || cpuPositions.contains(position) || position > 9 || position < 1) {
            position = scanner.nextInt();
        }


        return position;
    }

    protected static int getThePosForCpu() {


        Random rand = new Random();

        int position = rand.nextInt(9) + 1;

        while (cpuPositions.contains(position) || playerPositions.contains(position)) {

            position = rand.nextInt(9) + 1;
        }

        return position;

    }

    protected static String checkTheWinner() {

        List topRow = Arrays.asList(1, 2, 3);
        List middleRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);

        List leftCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);

        List cross1 = Arrays.asList(1, 5, 9);
        List cross2 = Arrays.asList(3, 5, 7);

        List<List> winning = new ArrayList<>();
        winning.add(topRow);
        winning.add(middleRow);
        winning.add(botRow);
        winning.add(leftCol);
        winning.add(topRow);
        winning.add(midCol);
        winning.add(rightCol);
        winning.add(cross1);
        winning.add(cross2);

        for (List list : winning) {
            if (playerPositions.containsAll(list)) {
                return "Congrats!!!! You won!";

            } else if (cpuPositions.containsAll(list)) {
                return "CPU won. Sorry(";

            } else if (playerPositions.size() + cpuPositions.size() == 9 && !(playerPositions.containsAll(list) ||
                    !(cpuPositions.containsAll(list)))) {
                return "CAT!";
            }

        }

        return "";
    }
}
