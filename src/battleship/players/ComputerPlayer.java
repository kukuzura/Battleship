package battleship.players;

import battleship.WorkingWithCoordinates;
import battleship.field.Cell;
import battleship.field.GameBoard;
import battleship.interfaces.Player;
import battleship.states.CellState;
import battleship.states.TurnState;

import static battleship.states.TurnState.*;

public class ComputerPlayer implements Player {
    GameBoard myBoard;
    GameBoard enemyBoard;
    Cell currentTarget;
    Cell lastSuccessfulTarget;
    boolean targetVertical;
    boolean finishingMove=false;


    public ComputerPlayer() {
        myBoard = new GameBoard();
        enemyBoard = new GameBoard();
        currentTarget = new Cell();
        lastSuccessfulTarget = new Cell();
    }

    @Override
    public boolean turn(Player player) {
        System.out.println("Ход компьютера");
        int x = (int) (Math.random() * 10);
        int y = (int) (Math.random() * 10);
        while (enemyBoard.getCellState(x, y) != CellState.UNDEFINED) {
            x = (int) (Math.random() * 10);
            y = (int) (Math.random() * 10);
        }
        System.out.println(WorkingWithCoordinates.getTwoCoordinatesAsString(x, y));
        return getAnotherPlayerDamageCheckResult(x,y,player);
    }

    public boolean finishingMove(Player player){
        currentTarget=getCurrentTarget();
        int x = currentTarget.getX();
        int y = currentTarget.getY();
        System.out.println(WorkingWithCoordinates.getTwoCoordinatesAsString(x, y));
        return getAnotherPlayerDamageCheckResult(x,y,player);
    }

    public boolean isFinishingMove(){
        return finishingMove;
    }

    boolean getAnotherPlayerDamageCheckResult(int x,int y,Player player){
        switch (player.checkDamage(x, y)) {
            case DESTROYED:
                enemyBoard.setCellState(x, y, CellState.HITDECK);
                enemyBoard.setMISSAroundShip(x, y);
                finishingMove=false;
                return true;
            case HIT:
                enemyBoard.setCellState(x, y, CellState.HITDECK);
                lastSuccessfulTarget.setX(x);
                lastSuccessfulTarget.setY(y);
                finishingMove=true;
                return true;
            case MISS:
                if (enemyBoard.getCellState(x, y) != CellState.HITDECK) {
                    enemyBoard.setCellState(x, y, CellState.MISS);
                }
        }
        return false;
    }

    public boolean isTargetVertical(int x, int y) {
        if (x != 0 && enemyBoard.getCellState(x, y - 1) == CellState.HITDECK && enemyBoard.getCellState(x, y - 1) != CellState.MISS) {
            targetVertical = true;
        } else if (y != 9 && enemyBoard.getCellState(x, y + 1) == CellState.HITDECK && enemyBoard.getCellState(x, y + 1) != CellState.MISS) {
            targetVertical = true;
        } else targetVertical = false;
        return targetVertical;
    }

    public Cell getCurrentTarget() {
        int lastX = lastSuccessfulTarget.getX();
        int lastY = lastSuccessfulTarget.getY();
        isTargetVertical(lastX,lastY);
        if (targetVertical) {
            if (lastX + 1 != 10 && enemyBoard.getCellState(lastX + 1, lastY) == CellState.UNDEFINED) {
                currentTarget.setX(lastX + 1);
                currentTarget.setY(lastY);
            }
            if (lastX - 1 != -1 && enemyBoard.getCellState(lastX - 1, lastY) == CellState.UNDEFINED) {
                currentTarget.setX(lastX - 1);
                currentTarget.setY(lastY);
            }
        } else {
            if (lastY + 1 != 10 && enemyBoard.getCellState(lastX, lastY + 1) == CellState.UNDEFINED) {
                currentTarget.setX(lastX);
                currentTarget.setY(lastY + 1);
            }
            if (lastY - 1 != -1 && enemyBoard.getCellState(lastX, lastY - 1) == CellState.UNDEFINED) {
                currentTarget.setX(lastX);
                currentTarget.setY(lastY - 1);
            }
        }
        return currentTarget;
    }

    @Override
    public TurnState checkDamage(int x, int y) {
        Cell hitCell = myBoard.getCell(x, y);
        if (hitCell.getState() == CellState.DECK) {
            myBoard.setCellState(x, y, CellState.HITDECK);
            // myBoard.setShipsDamage(x,y);
            if (myBoard.destroyedShipCheck())
                return DESTROYED;
            else return HIT;

        } else return MISS;
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

    public void printBoards() {
        System.out.printf(" %3s%2s%3s%2s%3s%2s%3s%2s%3s%2s", 'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з', 'и', 'к');
        System.out.print("   ");
        System.out.printf(" %3s%2s%3s%2s%3s%2s%3s%2s%3s%2s", 'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з', 'и', 'к');
        System.out.println();
        for (int i = 0; i < 10; i++) {
            System.out.printf("%2d", i + 1);
            myBoard.printString(i);
            System.out.print("  ");
            System.out.printf("%2d", i + 1);
            enemyBoard.printString(i);
            System.out.println();
        }
    }
}
