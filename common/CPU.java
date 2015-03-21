package Oracle.pack.XO.common;

/**
 * Created by Администратор on 20.03.2015.
 */
public class CPU extends Player{

    private static String name;
    private static Symbol symbol;

    CPU() {

    }

    CPU(String name, Symbol symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    protected String getName() {
        return name;
    }

    public Symbol getSymbol() {
        return symbol;
    }

}
