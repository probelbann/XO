package Oracle.pack.XO;

import Oracle.pack.XO.common.Fields;
import Oracle.pack.XO.common.Player;

import java.util.Scanner;

public class XO {

    final static String playerSymbol1 = "X";
    final static String playerSymbol2 = "O";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Fields game = new Fields();

        System.out.println("Введите имя первого и второго игрока: ");
        String playerName1 = scanner.next();
        String playerName2 = scanner.next();

        Player player1 = new Player(playerName1, playerSymbol1);
        Player player2 = new Player(playerName2, playerSymbol2);

        System.out.println("Имя первого игрока: "+player1.getName()+" и он обозначается "+player1.getSymbol());
        System.out.println("Имя второго игрока: "+player2.getName()+" и он обозначается "+player2.getSymbol());

        game.initSquare();

        int countMoves = 1;
        for (int i = 1; i < 10; i++) {
            if (i%2 == 1) {
                System.out.println("Ход # " + countMoves);
                if (player1.setPosition(player1)) {
                    game.showSquare();
                    if (countMoves >= 3) {
                        if (game.winner(player1, player2, countMoves)) {
                            break;
                        }
                    }
                    countMoves++;
                } else {
                    countMoves--;
                    game.showSquare();
                }
            } else {
                System.out.println("Ход # " + countMoves);
                if (player1.setPosition(player2)) {
                    game.showSquare();
                    if (countMoves >= 3) {
                        if (game.winner(player1, player2, countMoves)) {
                            break;
                        }
                    }
                    countMoves++;
                } else {
                    countMoves--;
                    game.showSquare();
                   }
                }
            }

        game.showPlayerMoves(player1);
        game.showPlayerMoves(player2);
        game.showAllHistoryMoves();
    }
    //00 01 02
    //01 11 21
    //20 21 22
    //00 10 20
    //01 11 21
    //02 12 22
    //00 11 22
    //20 11 02
}
