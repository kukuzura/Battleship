package battleship.field;

import battleship.states.CellState;
import battleship.states.ShipState;

import java.util.ArrayList;
import java.util.function.Predicate;

import static battleship.states.CellState.HITDECK;
import static battleship.states.CellState.MISS;

public class GameBoard {
    int size = 10;
    Cell[][] cells;
    ArrayList<Ship> ships;
    int shipsAmount;

    public GameBoard() {
        size = 10;
        cells = new Cell[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j] = new Cell();
            }
        }
        ships = new ArrayList<>();
        shipsAmount = 10;
    }

    public int getShipsAmount() {
        return shipsAmount;
    }

    public void setCellState(int x, int y, CellState state) {
        cells[x][y].setState(state);
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public void generateBoard(CellState cellState) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j].setState(cellState);
            }
        }
    }

    public void printString(int stringIndex) {
        for (int i = 0; i < size; i++) {
            cells[stringIndex][i].print();
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

    public void setMISSAroundShip(int lastX, int lastY) {

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
        } else if (lastY != size - 1 && cells[lastX][lastY + 1].state == CellState.HITDECK) {
            horizontal = true;
        } else horizontal = false;

        int shipSize = 0;
        if (lastX - 1 != -1) {
            while (!horizontal && lastX >= shipSize && cells[lastX - shipSize][lastY].state == HITDECK) {
                shipSize++;
            }
        }
        if (shipSize != 0) {

            if (lastX + 1 != size) {
                cells[lastX+1][lastY].setState(MISS);
            }
            if (lastX - shipSize != -1) {
                cells[lastX - shipSize][lastY].setState(MISS);
            }

            for (int i = -1; i <= shipSize; i++) {
                try {
                    cells[lastX - i][lastY + 1].setState(MISS);
                } catch (ArrayIndexOutOfBoundsException e) {
                }
                try {
                    cells[lastX - i][lastY - 1].setState(MISS);
                } catch (ArrayIndexOutOfBoundsException e) {
                }
            }
        } else {
            while (lastY >= shipSize && cells[lastX][lastY - shipSize].state == HITDECK) {
                shipSize++;
            }

            if (lastY + 1 != size) {
                cells[lastX][lastY + 1].setState(MISS);
            }
            if (lastY - shipSize != -1) {
                cells[lastX][lastY - shipSize].setState(MISS);
            }
            for (int i = -1; i <= shipSize; i++) {
                try {
                    cells[lastX + 1][lastY - i].setState(MISS);
                } catch (ArrayIndexOutOfBoundsException e) {
                }

                try {
                    cells[lastX - 1][lastY - i].setState(MISS);
                } catch (ArrayIndexOutOfBoundsException e) {
                }
            }
        }


    }

    public CellState getCellState(int x, int y) {
        return cells[x][y].state;
    }

    public void randomGenerate() {
        Predicate<Double> isVertical = x -> x > 0.5;
        generateBoard(CellState.EMPTY);
        int x = 0;
        int y = 0;
        boolean vertical = true;
        ships = new ArrayList<>();


        for (int shipSize = 4, insertAmount = 1; shipSize >= 1 && insertAmount <= 5 - shipSize; shipSize--, insertAmount++) {
            int i = 0;
            while (i < insertAmount) {
                x = (int) (Math.random() * 10);
                y = (int) (Math.random() * 10);

                vertical = isVertical.test(Math.random());

                while (!checkInsertion(shipSize, x, y, vertical)) {
                    x = (int) (Math.random() * 10);
                    y = (int) (Math.random() * 10);
                }
                ships.add(Ship.create(this, shipSize, x, y, vertical));
                i++;
            }
        }


//        if (checkInsertion(4, 7, 0, true)) {
//            ships.add(Ship.create(this, 4, 7, 0, true));
//        }

    }

    public boolean checkInsertion(int shipSize, int x, int y, boolean vertical) {
        if (vertical) {
            if (x + shipSize > size) {
                return false;
            }

            if (y + 1 != size && cells[x + shipSize - 1][y + 1].state == CellState.DECK || y - 1 != -1 && cells[x + shipSize - 1][y - 1].state == CellState.DECK) {
                return false;
            }

            if (y + 1 != size && cells[x][y + 1].state == CellState.DECK || y - 1 != -1 && cells[x][y - 1].state == CellState.DECK) {
                return false;
            }
            for (int i = x - 1; i <= x + shipSize; i++) {
                try {
                    if (cells[i][y].state == CellState.DECK) {
                        return false;
                    }
                    if (y + 1 != size && cells[i][y + 1].state == CellState.DECK) {
                        return false;
                    }
                    if (y - 1 != -1 && cells[i][y - 1].state == CellState.DECK) {
                        return false;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {

                }

            }
        } else {
            if (y + shipSize > size) {
                return false;
            }

            if (x + 1 != size && cells[x + 1][y + shipSize - 1].state == CellState.DECK || x - 1 != -1 && cells[x - 1][y + shipSize - 1].state == CellState.DECK) {
                return false;
            }

            if (x + 1 != size && cells[x + 1][y].state == CellState.DECK || x - 1 != -1 && cells[x - 1][y].state == CellState.DECK) {
                return false;
            }

            for (int i = y - 1; i <= y + shipSize; i++) {
                try {
                    if (cells[x][i].state == CellState.DECK) {
                        return false;
                    }
                    if (x + 1 != size && cells[x + 1][i].state == CellState.DECK) {
                        return false;
                    }
                    if (x - 1 != -1 && cells[x - 1][i].state == CellState.DECK) {
                        return false;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                }

            }

        }
        return true;
    }
}
