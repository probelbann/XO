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

        while (i < 10) {
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
                    countMoves--;
                    showSquare();
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
                    countMoves--;
                    showSquare();
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
        Scanner scanner = new Scanner(System.in);
        System.out.println("Игрок " + player.getName() + " делает ход: ");
        String a = scanner.nextLine();
        String b = scanner.nextLine();

        /*private void putPosition(int i, int j, String value) {
            char ch = value.charAt(0);
            square[i][j] = ch;
        }


        private void addValue(int i, int j, Player player) {
            String s = Integer.toString(i)+Integer.toString(j);
            historyMove.add(s);
            if (player.getSymbol().equals("X")) {
                playerValue1.add(s);
            } else if (player.getSymbol().equals("O")) {
                playerValue2.add(s);
            }
        }*/


        try {
            int i = Integer.parseInt(a);
            int j = Integer.parseInt(b);

            if (square[i][j].equals(" ")) {
                putPosition(i, j, player.getSymbol());
                addValue(i, j, player);
            } else {
                while (iCantMove) {
                    System.out.println("Игрок " + player.getName() + " делает неверный ход повторите попытку: iCantMove");
                    i = scanner.nextInt();
                    j = scanner.nextInt();
                    if (square[i][j] == ' ') {
                        putPosition(i, j, player.getSymbol());
                        addValue(i, j, player);
                        iCantMove = false;
                    }
                }
            }

        } catch (NumberFormatException e) {
            System.out.println("NumberFormatException: ");
            String backValue = scanner.nextLine();
            if (backValue.equals("back")) {
                int lastMove = historyMove.size();
                for (int k = 0; k < lastMove-1; k++) {
                    String value = historyMove.get(k);
                    int value1 = Integer.parseInt(value)/10;
                    int value2 = Integer.parseInt(value)%10;
                    String killValue = historyMove.get(lastMove-1);
                    int eraseField1 = Integer.parseInt(killValue);
                    int eraseField2 = Integer.parseInt(killValue);
                    square[eraseField1][eraseField2] = ' ';
                    if (((k+1) % 2) == 1) {
                        square[value1][value2] = 'X';
                        System.out.println(square[value1][value2]);
                    } else {
                        square[value1][value2] = 'O';
                        System.out.println(square[value1][value2]);
                    }
                }
                valueOfMethod = false;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ArrayIndexOutOfBoundsException");
            position(player);
        }
        this.makeMove = true;
        return valueOfMethod;
    }

    public void showAllHistoryMovies(Player player) {
        if (player.getSymbol().equals("X")) {
            int size = playerHistory1.size();
            System.out.println("История ходов игрока " + player.getName() + ": ");
            for (int j = 0; j < size; j++) {
                System.out.print(playerHistory1.get(j) + " ");
            }
            System.out.println();
        } else if (player.getSymbol().equals("O")) {
            int size = playerHistory2.size();
            System.out.println("История ходов игрока " + player.getName() + ": ");
            for (int j = 0; j < size; j++) {
                System.out.print(playerHistory2.get(j) + " ");
            }
            System.out.println();
        }
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
