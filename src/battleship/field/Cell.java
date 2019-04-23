package battleship.field;

import battleship.states.CellState;

import java.util.function.Consumer;

public class Cell {
    int x, y;
    CellState state;

    public CellState getState() {
        return state;
    }

    public void setState(CellState state) {
        this.state = state;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    void print() {
        Consumer<String> printer = s-> System.out.print(s);

        switch (state) {
            case EMPTY:
                printer.accept(" \u2610");
                break;
            case MISS:
                printer.accept(" \u25A3");
                break;
            case HITDECK:
                printer.accept(" \u2612");
                break;
            case DECK:
                printer.accept(" \u25A9");
                break;
            case UNDEFINED:
                printer.accept(" \u2610");
                break;
        }

    }
}

