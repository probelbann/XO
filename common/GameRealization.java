package Oracle.pack.XO.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Администратор on 16.03.2015.
 */
public class GameRealization extends Fields {

    public static final ArrayList<String> WIN_VALUE = new ArrayList<String>();
    public static ArrayList<String> historyOfGame = new ArrayList<String>();
    public static ArrayList<String> playerHistory1 = new ArrayList<String>();
    public static ArrayList<String> playerHistory2 = new ArrayList<String>();
    private boolean makeMove;
    private Player player1;
    private Player player2;

    static {
        WIN_VALUE.add("00 01 02");
        WIN_VALUE.add("01 11 21");
        WIN_VALUE.add("20 21 22");
        WIN_VALUE.add("00 10 20");
        WIN_VALUE.add("01 11 21");
        WIN_VALUE.add("02 12 22");
        WIN_VALUE.add("00 11 22");
        WIN_VALUE.add("20 11 02");
    }

    private void initGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имя первого и второго игрока: ");
        String playerName1 = scanner.next();
        String playerName2 = scanner.next();

        player1 = new Player(playerName1, Symbol.X);
        player2 = new Player(playerName2, Symbol.O);

        System.out.println("Имя первого игрока: "+player1.getName()+" и он обозначается "+player1.getSymbol());
        System.out.println("Имя второго игрока: "+player2.getName()+" и он обозначается "+player2.getSymbol());
    }

    public void startGame() {
        initGame();
        int countMoves = 1;
        int i = 1;

        for (; i < 10; i++) {
            if (i%2 == 1) {
                System.out.println("Ход # " + countMoves);
                if (setPosition(player1)) {
                    showSquare();
                    if (countMoves >= 3) {
                        if (showWinner(player1, player2, countMoves)) {
                            break;
                        }
                    }
                    countMoves++;
                } else {
                    if(countMoves > 0) {
                        countMoves--;
                        showSquare();
                    }
                }
            } else {
                System.out.println("Ход # " + countMoves);
                if (setPosition(player2)) {
                    showSquare();
                    if (countMoves >= 3) {
                        if (showWinner(player1, player2, countMoves)) {
                            break;
                        }
                    }
                    countMoves++;
                } else {
                    if(countMoves > 0) {
                        countMoves--;
                        showSquare();
                    }
                }
            }
        }
    }

    private boolean setPosition(Player player) {
        boolean valueOfMethod = true;
        this.makeMove = false;
        while (!makeMove)
            valueOfMethod = position(player);
        return valueOfMethod;
    }

    private boolean position(Player player) {
        boolean valueOfMethod = true;
        boolean iCantMove = true;
        String backValue = "";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Игрок " + player.getName() + " делает ход: ");
        String a = scanner.nextLine();
        String b = scanner.nextLine();

        try {
            int i = Integer.parseInt(a);
            int j = Integer.parseInt(b);

            if (square[i][j].equals(" ")) {
                Fields.addPosition(i, j, player.getSymbol());
                addPositionToHistory(i, j, player);
            } else {
                while (iCantMove) {
                    System.out.println("Игрок " + player.getName() + " делает неверный ход повторите попытку: iCantMove");
                    i = scanner.nextInt();
                    j = scanner.nextInt();
                    if (square[i][j].equals(" ")) {
                        Fields.addPosition(i, j, player.getSymbol());
                        addPositionToHistory(i, j, player);
                        iCantMove = false;
                    }
                }
            }

        } catch (NumberFormatException e) {
            System.out.println("NumberFormatException: введите числа от 0-2 или слово back для возврата хода");
            backValue = scanner.nextLine();
            int lastElement = historyOfGame.size()-1;
            if (backValue.equals("back")) {
                String value = historyOfGame.get(lastElement);
                int value1 = Integer.parseInt(value)/10;
                int value2 = Integer.parseInt(value)%10;
                Fields.killPosition(value1, value2);
                historyOfGame.remove(lastElement);
                valueOfMethod = false;
                }

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ArrayIndexOutOfBoundsException");
            position(player);
        }
        if (backValue.equals("back")) {
            this.makeMove = false;
        } else { this.makeMove = true; }
        return valueOfMethod;
    }

    private void addPositionToHistory(int i, int j, Player player) {
            String s = Integer.toString(i)+Integer.toString(j);
            historyOfGame.add(s);
            if (player.getSymbol() == Symbol.X) {
                playerHistory1.add(s);
            } else if (player.getSymbol() == Symbol.O) {
                playerHistory2.add(s);
            }
    }

    public void showHistoryMovies(ArrayList<String> history) {
        int size = history.size();
        for (int j = 0; j < size; j++) {
            System.out.print(history.get(j) + " ");
            }
            System.out.println();
    }

    private boolean showWinner(Player player1, Player player2, int countMoves) {
        boolean winner = false;
        if (winner(player1)) {
            System.out.println("ПОБЕДИТЕЛЬ: " + player1.getName());
            winner = true;
        } else if (winner(player2)) {
            System.out.println("ПОБЕДИТЕЛЬ: " + player2.getName());
            winner = true;
        } else {
            if (countMoves == 9) {
                System.out.println("Cостоялась ничья!");
            }
        }
        return winner;
    }

    private boolean winner(Player player) {
        boolean winner = false;
        int size = WIN_VALUE.size();
        for (int i = 0; i < size; i++) {
            String sWinnerValue = WIN_VALUE.get(i);
            String[] arrWinnerValue = sWinnerValue.split(" ");
            if (player.getSymbol() == Symbol.X) {
                if (playerHistory1.containsAll(Arrays.asList(arrWinnerValue))) {
                    winner = true;
                }
            } else if (player.getSymbol() == Symbol.O) {
                if (playerHistory2.containsAll(Arrays.asList(arrWinnerValue))) {
                    winner = true;
                }
            }
        }
        return winner;
    }

}