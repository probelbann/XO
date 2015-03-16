package Oracle.pack.XO.common;

import java.util.Scanner;

public class Player extends Fields {

    private String name;
    private String symbol;
    private boolean makeMove;

    public Player(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public boolean setPosition(Player player) {
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

        try {
            int i = Integer.parseInt(a);
            int j = Integer.parseInt(b);

            if (square[i][j] == ' ') {
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

    private void putPosition(int i, int j, String value) {
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
    }
}