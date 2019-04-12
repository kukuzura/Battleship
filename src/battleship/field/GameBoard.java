package battleship.field;

import battleship.states.CellState;

import java.util.ArrayList;

public class GameBoard {
    int size = 10;
    Cell[][] cells ;
    ArrayList<Ship> ships;
    int shipsAmount;

    public GameBoard(){
        size=10;
        cells = new Cell[size][size];
        ships=new ArrayList<>();
        shipsAmount=10;
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public void generateEnemyBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j]=new Cell();
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
                cells[i][j]=new Cell();
            }
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j].setState(CellState.EMPTY);
            }
        }
        Ship ship = new Ship();
        ships=new ArrayList<>();
        //  for(Ship s:ships){
        //     s=new Ship();
        //}
        ships.add(ship.create(this, 4, 0, 0, true));
        ships.add(ship.create(this, 3, 5, 0, true));
        ships.add(ship.create(this, 3, 0, 2, false));
        ships.add(ship.create(this, 2, 9, 8, false));
        ships.add(ship.create(this, 2, 6, 9, true));
        ships.add(ship.create(this, 2, 4, 4, true));
        ships.add(ship.create(this, 1, 7, 3, true));
        ships.add(ship.create(this, 1, 1, 7, true));
        ships.add(ship.create(this, 1, 9, 1, true));
        ships.add(ship.create(this, 1, 6, 6, true));
    }


    public void print() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j].print();
            }
            System.out.println();
        }
    }


}
