package battleship.players;

import battleship.util.WorkingWithCoordinates;
import battleship.field.Cell;
import battleship.states.CellState;

public class AdvancedComputerPlayerClass extends ComputerPlayerClass {
    Cell currentTarget;
    Cell lastSuccessfulTarget;
    boolean targetVertical=false;
    boolean finishingMove = false;
    boolean increaseCoordinate = true;

    public AdvancedComputerPlayerClass() {
        super();
        currentTarget = new Cell();
        lastSuccessfulTarget = new Cell();
    }

    public boolean finishingMove(Player playerClass) {
        currentTarget = getCurrentTarget();
        int x = currentTarget.getX();
        int y = currentTarget.getY();
        System.out.println(WorkingWithCoordinates.getTwoCoordinatesAsString(x, y));
        boolean moveFlag=getAnotherPlayerDamageCheckResult(x, y, playerClass);
        if(!moveFlag){
            isTargetVertical(lastSuccessfulTarget.getX(),lastSuccessfulTarget.getY());
        }
        return moveFlag;
    }

    public boolean isFinishingMove() {
        return finishingMove;
    }

    public boolean getAnotherPlayerDamageCheckResult(int x, int y, Player playerClass) {
        switch (playerClass.checkDamage(x, y)) {
            case DESTROYED:
                enemyBoard.setCellState(x, y, CellState.HITDECK);
                enemyBoard.setMISSAroundShip(x, y);
                finishingMove = false;
                targetVertical=false;
                increaseCoordinate=true;
                return true;
            case HIT:
                enemyBoard.setCellState(x, y, CellState.HITDECK);
                lastSuccessfulTarget.setX(x);
                lastSuccessfulTarget.setY(y);
                finishingMove = true;
                return true;
            case MISS:
                if (enemyBoard.getCellState(x, y) != CellState.HITDECK) {
                    enemyBoard.setCellState(x, y, CellState.MISS);
                }
                return false;
        }
        return false;
    }

    public boolean isTargetVertical(int x, int y) {
        try{
            if (enemyBoard.getCellState(x, y - 1) == CellState.MISS && enemyBoard.getCellState(x, y + 1) == CellState.MISS)
                targetVertical=true;
        }
        catch (ArrayIndexOutOfBoundsException ex){
            targetVertical=true;
        }
        if (x != 0 && (enemyBoard.getCellState(x-1, y ) ==CellState.HITDECK || x != 9 && (enemyBoard.getCellState(x+1, y) == CellState.HITDECK))){
            targetVertical = true;
        }
        return targetVertical;
    }

    public Cell getCurrentTarget() {
        int lastX = lastSuccessfulTarget.getX();
        int lastY = lastSuccessfulTarget.getY();
       // isTargetVertical(lastX, lastY);
        //boolean flag=false;
        if (targetVertical) {
            while (lastX < 10 && enemyBoard.getCellState(lastX , lastY) != CellState.UNDEFINED) {
                if(enemyBoard.getCellState(lastX,lastY)==CellState.MISS||lastX==9){
                    increaseCoordinate=false;
                    break;
                }
                lastX++;
            }
            while (lastX > 0 && !increaseCoordinate && enemyBoard.getCellState(lastX , lastY) != CellState.UNDEFINED) {
                lastX--;
                if(enemyBoard.getCellState(lastX,lastY)==CellState.MISS){
                    break;
                }
            }
        } else {
            while (lastY < 10 && enemyBoard.getCellState(lastX, lastY) != CellState.UNDEFINED) {
                if(enemyBoard.getCellState(lastX,lastY)==CellState.MISS || lastY==9){
                    increaseCoordinate=false;
                    break;
                }
                lastY++;
            }
            while (lastY > 0 && !increaseCoordinate && enemyBoard.getCellState(lastX, lastY) != CellState.UNDEFINED) {
                lastY--;
                if(enemyBoard.getCellState(lastX,lastY)==CellState.MISS){
                    break;
                }
            }

        }
//        if(currentTarget.getX()==lastX&&currentTarget.getY()==lastY){
//            System.out.println("что то не то");
 //       }
        currentTarget.setX(lastX);
        currentTarget.setY(lastY);
        return currentTarget;
    }
}
