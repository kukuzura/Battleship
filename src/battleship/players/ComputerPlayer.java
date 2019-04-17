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

    public ComputerPlayer() {
        myBoard = new GameBoard();
        enemyBoard = new GameBoard();
    }

    @Override
    public boolean turn(Player player) {
        System.out.println("Ход компьютера");
        int x = (int) (Math.random() * 10);
        int y = (int) (Math.random() * 10);
        System.out.println(WorkingWithCoordinates.getTwoCoordinatesAsString(x,y));
        if (enemyBoard.getCellState(x, y) == CellState.UNDEFINED) {
            switch (player.checkDamage(x, y)) {
                case DESTROYED:
                    enemyBoard.setCellState(x, y, CellState.HITDECK);
                    enemyBoard.setMISSAroundShip(x, y);
                    return true;
                case HIT:
                    enemyBoard.setCellState(x, y, CellState.HITDECK);
                    return true;
                case MISS:
                    if(enemyBoard.getCellState(x,y)!=CellState.HITDECK) {
                        enemyBoard.setCellState(x, y, CellState.MISS);
                    }
                    return false;
            }
        }
            return false;
    }

    @Override
    public TurnState checkDamage(int x, int y) {
        Cell hitCell=myBoard.getCell(x,y);
        if(hitCell.getState()== CellState.DECK) {
            myBoard.setCellState(x,y,CellState.HITDECK);
            myBoard.setShipsDamage(x,y);
            if (myBoard.destroyedShipCheck())
                return DESTROYED;
            else return HIT;

        }
        else return MISS;
    }

    public boolean isAlive(){
        if(myBoard.getShipsAmount()==0){
            return false;
        }
        else return true;
    }

    public void generateBoards(){
        myBoard.generateMyBoard();
        enemyBoard.generateEnemyBoard();
    }

    public void randomGenerate(){
        enemyBoard.generateEnemyBoard();
        myBoard.randomGenerate();
    }

    public void printBoards(){
        System.out.printf(" %3s%2s%3s%2s%3s%2s%3s%2s%3s%2s",'а','б','в','г','д','е','ж','з','и','к');
        System.out.print("   ");
        System.out.printf(" %3s%2s%3s%2s%3s%2s%3s%2s%3s%2s",'а','б','в','г','д','е','ж','з','и','к');
        System.out.println();
        for(int i=0;i<10;i++){
            System.out.printf("%2d",i+1);
            myBoard.printString(i);
            System.out.print("  ");
            System.out.printf("%2d",i+1);
            enemyBoard.printString(i);
            System.out.println();
        }
    }
}
