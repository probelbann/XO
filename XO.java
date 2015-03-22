package Oracle.pack.XO;

import Oracle.pack.XO.common.GameRealization;

public class XO extends GameRealization{

    public static void main(String[] args) {

        GameRealization game = new GameRealization();
        game.initGame();
        game.showHistoryMovies();

    }
    /*Выйгрышные комбинации
    00 01 02
    01 11 21
    20 21 22
    00 10 20
    01 11 21
    02 12 22
    00 11 22
    20 11 02*/
}
