package battleship.players;

import battleship.field.Cell;
import battleship.field.GameBoard;
import battleship.interfaces.Player;
import battleship.states.CellState;
import battleship.states.TurnState;

public class ComputerPlayer implements Player {
    GameBoard myBoard;
    GameBoard enemyBoard;

    public ComputerPlayer() {
        myBoard = new GameBoard();
        enemyBoard = new GameBoard();
    }

    @Override
    public boolean turn(Player player) {
        return false;
    }

    @Override
    public TurnState checkDamage(int x, int y) {
        Cell hitCell=myBoard.getCell(x,y);
        if(hitCell.getState()== CellState.DECK) {
            myBoard.setCellState(x,y,CellState.HITDECK);
            myBoard.setShipsDamage(x,y);
            if (myBoard.destroyedShipCheck())
                return TurnState.DESTROYED;
            else return TurnState.HIT;

        }
        else return TurnState.MISS;
    }

    public void generateBoards(){
        myBoard.generateMyBoard();
        enemyBoard.generateEnemyBoard();
    }

    public void printBoards(){
        System.out.println("   а  б  в  г  д  е  ж  з  и  к"+"     "+" а  б  в  г  д  е  ж  з  и  к");
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
