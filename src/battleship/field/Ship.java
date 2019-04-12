package battleship.field;

import battleship.states.CellState;
import battleship.states.ShipState;

import static battleship.states.ShipState.SAFE;

public class Ship {
    ShipState state;
    Cell[] cells;
    int size;

    public Cell[] getCells() {
        return cells;
    }

    public int getSize() {
        return size;
    }

    public ShipState getState() {
        return state;
    }

    public void setState(ShipState state) {
        cells = new Cell[size];
        for (int i = 0; i < size; i++) {
            cells[i] = new Cell();
        }
        int hintsAmount = 0;
        for (int i = 0; i < size; i++) {
            if (cells[i].state == CellState.HITDECK) {
                hintsAmount++;
                setState(ShipState.HIT);
            }
        }
        if (hintsAmount == size) {
            state = ShipState.DESTROYED;
        } else if (hintsAmount != 0) {
            state = ShipState.HIT;
        } else state = ShipState.SAFE;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setCells(Cell[] cells) {
        this.cells = cells;
    }

    public boolean shoot(int x, int y) { ////????
        return false;
    }

    public Ship create(GameBoard board, int size, int x, int y, boolean horizontal) {
        cells = new Cell[size];
        for (int i = 0; i < size; i++) {
            cells[i] = new Cell();
        }
        for (int i = 0; i < size; i++) {
            if (horizontal) {
                cells[i].setX(x + i);
                cells[i].setY(y);
                cells[i].setState(CellState.DECK);
                board.cells[x + i][y].setState(CellState.DECK);
            } else {
                cells[i].setX(x);
                cells[i].setY(y + i);
                cells[i].setState(CellState.DECK);
                board.cells[x][y + i].setState(CellState.DECK);
            }
        }
        setState(SAFE);
        return this;
    }
}


