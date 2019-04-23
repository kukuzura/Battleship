package battleship.field;

import battleship.states.CellState;
import battleship.states.ShipState;

import static battleship.states.ShipState.SAFE;

public class Ship {
    ShipState state;
    Cell[] cells;
    int size;
    boolean horizontal;

    public Cell[] getCells() {
        return cells;
    }

    public void setState(ShipState state) {
        this.state = state;
    }

    public int getSize() {
        return size;
    }

    public ShipState getState() {
        return state;
    }

    public boolean isThereSuchCell(int x, int y) {
        for (int i = 0; i < size; i++) {
            if (cells[i].x == x && cells[i].y == y) {
                return true;
            }
        }
        return false;
    }

    public void setCellState(int x, int y, CellState state) {
        for (int i = 0; i < size; i++) {
            if (cells[i].x == x && cells[i].y == y) {
                cells[i].state = state;
            }
        }
    }

    public void checkState() {
        int hintsAmount = 0;
        for (int i = 0; i < size; i++) {
            if (cells[i].state == CellState.HITDECK) {
                hintsAmount++;
                state = ShipState.HIT;
            }
        }
        if (hintsAmount == size) {
            state = ShipState.DESTROYED;
        } else if (hintsAmount != 0) {
            state = ShipState.HIT;
        } else state = ShipState.SAFE;
    }

    public static Ship create(GameBoard board, int size, int x, int y, boolean horizontal) {

        Ship ship = new Ship();
        ship.cells = new Cell[size];
        ship.size = size;
        for (int i = 0; i < size; i++) {
            if (horizontal) {
                //ship.cells[i].setX(x + i);
                //ship.cells[i].setY(y);
                //ship.cells[i].setState(CellState.DECK);
                ship.horizontal = horizontal;
                board.cells[x + i][y].setState(CellState.DECK);
                ship.cells[i] = board.cells[x + i][y];
            } else {
                //ship.cells[i].setX(x);
                //ship.cells[i].setY(y + i);
                //ship.cells[i].setState(CellState.DECK);
                ship.horizontal = horizontal;
                board.cells[x][y + i].setState(CellState.DECK);
                ship.cells[i] = board.cells[x][y + i];
            }
        }
        ship.setState(SAFE);
        return ship;
    }
}


