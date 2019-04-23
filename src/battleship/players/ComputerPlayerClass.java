package battleship.players;

import battleship.util.WorkingWithCoordinates;
import battleship.field.Cell;
import battleship.field.GameBoard;
import battleship.states.CellState;
import battleship.states.TurnState;

import static battleship.states.TurnState.*;

public class ComputerPlayerClass implements Player {

    GameBoard myBoard;
    GameBoard enemyBoard;

    public ComputerPlayerClass(){
        myBoard = new GameBoard();
        enemyBoard = new GameBoard();
    }

    @Override
    public boolean turn(Player player) {
        System.out.println(" \n \u2694 "+"Ход компьютера"+" \u2694 \n");
        int x = (int) (Math.random() * 10);
        int y = (int) (Math.random() * 10);
        while (enemyBoard.getCellState(x, y) != CellState.UNDEFINED) {
            x = (int) (Math.random() * 10);
            y = (int) (Math.random() * 10);
        }
        System.out.println(WorkingWithCoordinates.getTwoCoordinatesAsString(x, y));
        return getAnotherPlayerDamageCheckResult(x, y, player);
    }

    @Override
    public TurnState checkDamage(int x, int y) {
        Cell hitCell = myBoard.getCell(x, y);
        if (hitCell.getState() == CellState.DECK) {
            myBoard.setCellState(x, y, CellState.HITDECK);
            if (myBoard.destroyedShipCheck())
                return DESTROYED;
            else return HIT;

        } else return MISS;
    }

    boolean getAnotherPlayerDamageCheckResult(int x, int y, Player playerClass) {
        switch (playerClass.checkDamage(x, y)) {
            case DESTROYED:
                enemyBoard.setCellState(x, y, CellState.HITDECK);
                enemyBoard.setMISSAroundShip(x, y);
                return true;
            case HIT:
                enemyBoard.setCellState(x, y, CellState.HITDECK);
                return true;
            case MISS:
                if (enemyBoard.getCellState(x, y) != CellState.HITDECK) {
                    enemyBoard.setCellState(x, y, CellState.MISS);
                }
        }
        return false;
    }

    public boolean isAlive() {
        if (myBoard.getShipsAmount() == 0) {
            return false;
        } else return true;
    }

    public void randomGenerate() {
        enemyBoard.generateBoard(CellState.UNDEFINED);
        myBoard.randomGenerate();
    }


}
