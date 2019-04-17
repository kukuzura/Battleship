package battleship.field;

import battleship.states.CellState;

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
        switch (state) {
            case EMPTY:
               // System.out.print("[ ]");
                //System.out.print(" \u20DE");
                System.out.print(" \u2610");
                //System.out.print(" \u23A2");
                break;
            case MISS:
                System.out.print(" \u25A3");
               // System.out.print("[.]");
                break;
            case HITDECK:
                System.out.print(" \u2612");
               // System.out.print("[x]");
                break;
            case DECK:
              //  System.out.print("\u25A7");
                //System.out.print("\u23A2");
                //System.out.print(" \u25A8");
                //System.out.print(" \u23A2");
                System.out.print(" \u25A9");
               // System.out.print("[d]");
                break;
            case UNDEFINED:
                System.out.print(" \u2610");
                //System.out.print("[ ]");
                break;
        }

    }

    boolean isThereShip(){
        if(state==CellState.DECK)
            return true;
        else return false;
    }
}
