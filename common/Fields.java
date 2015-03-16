package Oracle.pack.XO.common;

/**
 * Created by Администратор on 16.03.2015.
 */
public class Fields extends Player{

    public static final int SIZE_SQUARE = 3;
    static String[][] square = new String[SIZE_SQUARE][SIZE_SQUARE];

    static {
        System.out.println();
        System.out.println("Инициализация поля");
        for (int i = 0; i < SIZE_SQUARE; i++) {
            for (int j = 0; j < SIZE_SQUARE; j++) {
                square[i][j] = " ";
            }
        }
        showSquare();
    }

    public static void addPostition(int i, int j) {

    }

    public static void killPosition(int i, int j) {

    }

    protected static void showSquare() {
        for (int i = 0; i < SIZE_SQUARE; i++) {
            for (int j = 0; j < SIZE_SQUARE; j++) {
                System.out.print("{"+square[i][j]+"} ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
