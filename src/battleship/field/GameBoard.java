package battleship.field;

import battleship.states.CellState;
import battleship.states.ShipState;

import java.util.ArrayList;

public class GameBoard {
    int size = 10;
    Cell[][] cells;
    ArrayList<Ship> ships;
    int shipsAmount;

    public GameBoard() {
        size = 10;
        cells = new Cell[size][size];
        ships = new ArrayList<>();
        shipsAmount = 10;
    }

    public void setCellState(int x, int y, CellState state) {
        cells[x][y].setState(state);
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public void generateEnemyBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j] = new Cell();
            }
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j].setState(CellState.UNDEFINED);
            }
        }
    }

    public void generateMyBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j] = new Cell();
            }
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j].setState(CellState.EMPTY);
            }
        }
        //Ship ship = new Ship();
        ships = new ArrayList<>();
        ships.add(Ship.create(this, 4, 0, 0, true));
        ships.add(Ship.create(this, 3, 5, 0, true));
        ships.add(Ship.create(this, 3, 0, 2, false));
        ships.add(Ship.create(this, 2, 9, 8, false));
        ships.add(Ship.create(this, 2, 6, 9, true));
        ships.add(Ship.create(this, 2, 4, 4, true));
        ships.add(Ship.create(this, 1, 7, 3, true));
        ships.add(Ship.create(this, 1, 1, 7, true));
        ships.add(Ship.create(this, 1, 9, 1, true));
        ships.add(Ship.create(this, 1, 6, 6, true));
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j].print();
            }
            System.out.println();
        }
    }

    public void printString(int stringIndex) {
        for (int i = 0; i < size; i++) {
            cells[stringIndex][i].print();
        }
    }

    public void setShipsDamage(int x, int y) {
        for (Ship ship : ships) {
            if (ship.isThereSuchCell(x, y)) {
                ship.setCellState(x, y, CellState.HITDECK);
                break;
            }
        }
    }

    public boolean destroyedShipCheck() {
        for (int i = 0; i < shipsAmount; i++) {
            ships.get(i).checkState();
            if (ships.get(i).getState() == ShipState.DESTROYED) {
                ships.remove(i);
                shipsAmount--;
                return true;
            }
        }
        return false;
    }

    public void setMISSAroundDestroyed(int lastX, int lastY) {

        while (true) {
            if (lastX + 1 != size) {
                while (lastX + 1 < size && cells[lastX + 1][lastY].state == CellState.HITDECK) {
                    lastX++;

                }
            }
            if (lastY + 1 != size) {
                while (lastY + 1 < size && cells[lastX][lastY + 1].state == CellState.HITDECK) {
                    lastY++;
                }
            }
            break;
        }

        boolean horizontal;
        if (lastY != 0 && cells[lastX][lastY - 1].state == CellState.HITDECK) {
            horizontal = true;
        } else if (lastY != size && cells[lastX][lastY + 1].state == CellState.HITDECK) {
            horizontal = true;
        } else horizontal = false;


//        if (!horizontal) {
//            while (lastX >=0 && cells[lastX][lastY].state == CellState.HITDECK) {
//                if (lastY + 1 != size) {
//                    cells[lastX][lastY + 1].setState(CellState.MISS);
//                }
//                if (lastY - 1 != -1) {
//                    cells[lastX][lastY - 1].setState(CellState.MISS);
//                }
//                lastX--;
//            }
//        } else {
//            while (lastY >= 0 && cells[lastX][lastY].state == CellState.HITDECK ) {
//                if (lastX + 1 != size) {
//                    cells[lastX + 1][lastY].setState(CellState.MISS);
//                }
//                if (lastX - 1 != -1) {
//                    cells[lastX - 1][lastY].setState(CellState.MISS);
//                }
//                lastY--;
//            }
//        }
//
//        if (lastX == 0 || lastX==9) {
//            if (lastY != size)
//                cells[lastX][lastY + 1].setState(CellState.MISS);
//            if (lastY != 0)
//                cells[lastX][lastY - 1].setState(CellState.MISS);
//
//        }


        int shipSize = 0;
        if (lastX - 1 != -1) {
            while (!horizontal && lastX >= shipSize && (cells[lastX - shipSize][lastY].state != CellState.MISS && cells[lastX - shipSize][lastY].state != CellState.UNDEFINED)) {
                shipSize++;
            }
        }
        if (shipSize != 0) {
            if (lastX + 1 != size) {
                if (lastY - 1 != -1) {
                    cells[lastX + 1][lastY - 1].setState(CellState.MISS);
                }
                cells[lastX + 1][lastY].setState(CellState.MISS);

                if (lastY + 1 != size) {
                    cells[lastX + 1][lastY + 1].setState(CellState.MISS);
                }
            }
            if (lastX >= shipSize) {
                if (lastY - 1 != -1) {
                    cells[lastX - shipSize][lastY - 1].setState(CellState.MISS);
                }
                cells[lastX - shipSize][lastY].setState(CellState.MISS);

                if (lastY + 1 != size) {
                    cells[lastX - shipSize][lastY + 1].setState(CellState.MISS);
                }
            }
            for (int i = 0; i < shipSize; i++) {
                if (lastY + 1 != size)
                    cells[lastX - i][lastY + 1].setState(CellState.MISS);
                if (lastY - 1 != -1)
                    cells[lastX - i][lastY - 1].setState(CellState.MISS);
            }
        } else {
            while (lastY >= shipSize && (cells[lastX][lastY - shipSize].state != CellState.MISS && cells[lastX][lastY - shipSize].state != CellState.UNDEFINED) && lastY > shipSize) {
                shipSize++;
            }
            if (lastY + 1 != size) {
                if (lastX + 1 != size) {
                    cells[lastX + 1][lastY + 1].setState(CellState.MISS);
                }
                cells[lastX][lastY + 1].setState(CellState.MISS);
                if (lastX - 1 != -1) {
                    cells[lastX - 1][lastY + 1].setState(CellState.MISS);
                }
            }
            if (lastY > shipSize) {
                if (lastX + 1 != size) {
                    cells[lastX + 1][lastY - shipSize].setState(CellState.MISS);
                }
                cells[lastX][lastY - shipSize].setState(CellState.MISS);
                if (lastY != -1) {
                    cells[lastX - 1][lastY - shipSize].setState(CellState.MISS);
                }
            }
            for (int i = 0; i < shipSize; i++) {
                if (lastX + 1 != size && lastY - i < 0)
                    cells[lastX + 1][lastY - i].setState(CellState.MISS);
                if (lastX - 1 != -1 && lastY - i < size)
                    cells[lastX - 1][lastY - i].setState(CellState.MISS);
            }
        }


    }

}
