package Oracle.pack.XO.garbage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Fields {

    public static final int SIZE_SQUARE = 3;
    public static final ArrayList<String>  WIN_VALUE = new ArrayList<String>();

    public static ArrayList<String> historyMove = new ArrayList<String>();
    public static ArrayList<String> playerHistory1 = new ArrayList<String>();
    public static ArrayList<String> playerHistory2 = new ArrayList<String>();

    static String[][] square = new String[SIZE_SQUARE][SIZE_SQUARE];

    final static String playerSymbol1 = "X";
    final static String playerSymbol2 = "O";

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
    
    public void initGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имя первого и второго игрока: ");
        String playerName1 = scanner.next();
        String playerName2 = scanner.next();

        Player player1 = new Player(playerName1, playerSymbol1);
        Player player2 = new Player(playerName2, playerSymbol2);

        System.out.println("Имя первого игрока: "+player1.getName()+" и он обозначается "+player1.getSymbol());
        System.out.println("Имя второго игрока: "+player2.getName()+" и он обозначается "+player2.getSymbol());

        initSquare();
    }

    private void initSquare() {

        System.out.println();
    System.out.println("Инициализация поля");
    for (int i = 0; i < SIZE_SQUARE; i++) {
        for (int j = 0; j < SIZE_SQUARE; j++) {
            square[i][j] = " ";
        }
    }
    showSquare();
}
    
    public void startGame() {
        int countMoves = 1;
        int i = 1;

        while (i < 10) {
            if (i%2 == 1) {
                System.out.println("Ход # " + countMoves);
                if (player1.setPosition(player1)) {
                    showSquare();
                    if (countMoves >= 3) {
                        if (winner(player1, player2, countMoves)) {
                            break;
                        }
                    }
                    countMoves++;
                } else {
                    countMoves--;
                    showSquare();
                }
            } else {
                System.out.println("Ход # " + countMoves);
                if (player1.setPosition(player2)) {
                    showSquare();
                    if (countMoves >= 3) {
                        if (winner(player1, player2, countMoves)) {
                            break;
                        }
                    }
                    countMoves++;
                } else {
                    countMoves--;
                    showSquare();
                }
            }
        }
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
            int size = Fields.playerHistory1.size();
            System.out.println("История ходов игрока " + player.getName() + ": ");
            for (int j = 0; j < size; j++) {
                System.out.print(Fields.playerHistory1.get(j) + " ");
            }
            System.out.println();
        } else if (player.getSymbol().equals("O")) {
            int size = Fields.playerHistory2.size();
            System.out.println("История ходов игрока " + player.getName() + ": ");
            for (int j = 0; j < size; j++) {
                System.out.print(Fields.playerHistory2.get(j) + " ");
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
                if (playerHistory1.containsAll(Arrays.asList(arrWinnerValue))) {
                    winner = true;
                }
            } else if (player.getSymbol().equals("O")) {
                if (playerHistory2.containsAll(Arrays.asList(arrWinnerValue))) {
                    winner = true;
                }
            }
        }
        return winner;
    }
}