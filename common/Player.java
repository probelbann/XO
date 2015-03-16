package Oracle.pack.XO.common;

/**
 * Created by Администратор on 16.03.2015.
 */
public class Player {

    private String name;
    private Symbol symbol;

    Player() {

    }

    Player(String name, Symbol symbol) {
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
