package Oracle.pack.XO.common;

import java.util.ArrayList;
import java.util.Arrays;

public class Fields {

    public static final int SIZE_SQUARE = 3;

    public static final ArrayList<String>  WIN_VALUE = new ArrayList<String>();

    public static ArrayList<String> historyMove = new ArrayList<String>();
    public static ArrayList<String> playerValue1 = new ArrayList<String>();
    public static ArrayList<String> playerValue2 = new ArrayList<String>();

    static char[][] square = new char[SIZE_SQUARE][SIZE_SQUARE];

    public Fields(){
        WIN_VALUE.add("00 01 02");
        WIN_VALUE.add("01 11 21");
        WIN_VALUE.add("20 21 22");
        WIN_VALUE.add("00 10 20");
        WIN_VALUE.add("01 11 21");
        WIN_VALUE.add("02 12 22");
        WIN_VALUE.add("00 11 22");
        WIN_VALUE.add("20 11 02");
    }

    public void initSquare() {

        System.out.println();
        System.out.println("Инициализация поля");
        for (int i = 0; i < SIZE_SQUARE; i++) {
            for (int j = 0; j < SIZE_SQUARE; j++) {
                square[i][j] = ' ';
            }
        }
        showSquare();
    }

    public void showSquare() {
        for (int i = 0; i < SIZE_SQUARE; i++) {
            for (int j = 0; j < SIZE_SQUARE; j++) {
                System.out.print("{"+square[i][j]+"} ");
            }
            System.out.println();
        }
            System.out.println();
    }

    public void showPlayerMoves(Player player) {
        if (player.getSymbol().equals("X")) {
            int size = Fields.playerValue1.size();
            System.out.println("История ходов игрока " + player.getName() + ": ");
            for (int j = 0; j < size; j++) {
                System.out.print(Fields.playerValue1.get(j) + " ");
            }
            System.out.println();
        } else if (player.getSymbol().equals("O")) {
            int size = Fields.playerValue2.size();
            System.out.println("История ходов игрока " + player.getName() + ": ");
            for (int j = 0; j < size; j++) {
                System.out.print(Fields.playerValue2.get(j) + " ");
            }
            System.out.println();
        }
    }

    public void showAllHistoryMoves () {
        int size = Fields.historyMove.size();
        System.out.println("История всех ходов: ");
        for (int j = 0; j < size; j++) {
            System.out.print(Fields.historyMove.get(j) + " ");
        }
        System.out.println();
    }

    public boolean winner(Player player1, Player player2, int countMoves) {
        boolean winner = false;
        if (showWinner(player1)) {
            System.out.println("ПОБЕДИТЕЛЬ: " + player1.getName());
            winner = true;
        } else if (showWinner(player2)) {
            System.out.println("ПОБЕДИТЕЛЬ: " + player2.getName());
            winner = true;
        } else {
            if (countMoves == 9) {
                System.out.println("Cостоялась ничья!");
            }
        }
        return winner;
    }

    public boolean showWinner(Player player) {
        boolean winner = false;
        int size = WIN_VALUE.size();
        for (int i = 0; i < size; i++) {
            String sWinnerValue = WIN_VALUE.get(i);
            String[] arrWinnerValue = sWinnerValue.split(" ");
            if (player.getSymbol().equals("X")) {
                if (playerValue1.containsAll(Arrays.asList(arrWinnerValue))) {
                    winner = true;
                }
            } else if (player.getSymbol().equals("O")) {
                if (playerValue2.containsAll(Arrays.asList(arrWinnerValue))) {
                    winner = true;
                }
            }
        }
        return winner;
    }
}