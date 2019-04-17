package battleship.field;

import battleship.states.CellState;
import battleship.states.ShipState;

import java.util.ArrayList;

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
           // System.out.print(" \u2500");
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
                if (lastY - 1 != -1) {
                    cells[lastX + 1][lastY - 1].setState(MISS);
                }
                cells[lastX + 1][lastY].setState(MISS);

                if (lastY + 1 != size) {
                    cells[lastX + 1][lastY + 1].setState(MISS);
                }
            }
            if (lastX >= shipSize) {
                if (lastY - 1 != -1) {
                    cells[lastX - shipSize][lastY - 1].setState(MISS);
                }
                cells[lastX - shipSize][lastY].setState(MISS);

                if (lastY + 1 != size) {
                    cells[lastX - shipSize][lastY + 1].setState(MISS);
                }
            }
            for (int i = 0; i < shipSize; i++) {
                if (lastY + 1 != size)
                    cells[lastX - i][lastY + 1].setState(MISS);
                if (lastY - 1 != -1)
                    cells[lastX - i][lastY - 1].setState(MISS);
            }
        } else {
            while (lastY >= shipSize && cells[lastX][lastY - shipSize].state == HITDECK) {
                shipSize++;
            }
            if (lastY + 1 != size) {
                if (lastX + 1 != size) {
                    cells[lastX + 1][lastY + 1].setState(MISS);
                }
                cells[lastX][lastY + 1].setState(MISS);
                if (lastX - 1 != -1) {
                    cells[lastX - 1][lastY + 1].setState(MISS);
                }
            }
            if (lastY > shipSize) {
                if (lastX + 1 != size) {
                    cells[lastX + 1][lastY - shipSize].setState(MISS);
                }
                cells[lastX][lastY - shipSize].setState(MISS);
                if (lastX != 0) {
                    cells[lastX - 1][lastY - shipSize].setState(MISS);
                }
            }
            for (int i = 0; i < shipSize; i++) {
                if (lastX + 1 != size && lastY - i > 0)
                    cells[lastX + 1][lastY - i].setState(MISS);
                if (lastX - 1 != -1 && lastY - i < size)
                    cells[lastX - 1][lastY - i].setState(MISS);
            }
        }


    }

    public CellState getCellState(int x, int y) {
        return cells[x][y].state;
    }

    public void randomGenerate() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j] = new Cell();
                cells[i][j].setState(CellState.EMPTY);
            }
        }
        int x = 0;
        int y = 0;
        boolean vertical = true;
        ships = new ArrayList<>();


        for (int shipSize = 4, insertAmount = 1; shipSize >= 1 && insertAmount <= 5 - shipSize; shipSize--, insertAmount++) {
            int i = 0;
            while (i < insertAmount) {
                x = (int) (Math.random() * 10);
                y = (int) (Math.random() * 10);
                if (Math.random() > 0.5)                  //что-то другое(м.б функция?)
                    vertical = false;
                else vertical = true;
                while (!checkInsertion(shipSize, x, y, vertical)) {
                    x = (int) (Math.random() * 10);
                    y = (int) (Math.random() * 10);
                }
                ships.add(Ship.create(this, shipSize, x, y, vertical));
                i++;
            }
        }
    }

    public boolean checkInsertion(int shipSize, int x, int y, boolean vertical) {
        if (vertical) {
            if (x + shipSize > size - 1) {
                return false;
            }

            if (y + 1 != size && cells[x + shipSize][y + 1].state == CellState.DECK || y - 1 != -1 && cells[x + shipSize][y - 1].state == CellState.DECK) {
                return false;
            }

            if (y + 1 != size && cells[x][y + 1].state == CellState.DECK || y - 1 != -1 && cells[x][y - 1].state == CellState.DECK) {
                return false;
            }

            for (int i = x - 1; i <= x + shipSize; i++) {
                if (x - 1 != -1) {
                    if (cells[i][y].state == CellState.DECK) {
                        return false;
                    }
                    if (y + 1 != size && cells[i][y + 1].state == CellState.DECK) {
                        return false;
                    }
                    if (y - 1 != -1 && cells[i][y - 1].state == CellState.DECK) {
                        return false;
                    }
                }

            }
        } else {
            if (y + shipSize > size - 1) {
                return false;
            }

            if (x + 1 != size && cells[x + 1][y + shipSize].state == CellState.DECK || x - 1 != -1 && cells[x - 1][y + shipSize].state == CellState.DECK) {
                return false;
            }

            if (x + 1 != size && cells[x + 1][y].state == CellState.DECK || x - 1 != -1 && cells[x - 1][y].state == CellState.DECK) {
                return false;
            }

            for (int i = y - 1; i <= y + shipSize; i++) {
                if (y - 1 != -1) {
                    if (cells[x][i].state == CellState.DECK) {
                        return false;
                    }
                    if (x + 1 != size && cells[x + 1][i].state == CellState.DECK) {
                        return false;
                    }
                    if (x - 1 != -1 && cells[x - 1][i].state == CellState.DECK) {
                        return false;
                    }
                }
            }

        }


        return true;
    }
}
