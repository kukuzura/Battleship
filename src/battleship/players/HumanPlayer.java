package battleship.players;

import battleship.field.Cell;
import battleship.field.GameBoard;
import battleship.interfaces.Player;
import battleship.states.CellState;
import battleship.states.TurnState;

import java.util.Scanner;

public class HumanPlayer implements Player {
    GameBoard myBoard;
    GameBoard enemyBoard;

    public HumanPlayer() {
        myBoard = new GameBoard();
        enemyBoard = new GameBoard();
    }

    public void generateBoards() {
        myBoard.generateMyBoard();
        enemyBoard.generateEnemyBoard();
    }

    public void printBoards() {
        System.out.println("   а  б  в  г  д  е  ж  з  и  к" + "     " + " а  б  в  г  д  е  ж  з  и  к");
        for (int i = 0; i < 10; i++) {
            System.out.printf("%2d", i + 1);
            myBoard.printString(i);
            System.out.print("  ");
            System.out.printf("%2d", i + 1);
            enemyBoard.printString(i);
            System.out.println();
        }
    }

    @Override
    public boolean turn(Player comp) {
        Scanner scan = new Scanner(System.in); ////////сделать ввод координат "а1"
        System.out.println("Введи x");
        int x = scan.nextInt();
        System.out.println("Введи y");
        int y = scan.nextInt();
        switch (comp.checkDamage(x, y)) {
            case DESTROYED:
                enemyBoard.setCellState(x, y, CellState.HITDECK);
                enemyBoard.setMISSAroundDestroyed(x, y);
                return true;
            case HIT:
                enemyBoard.setCellState(x, y, CellState.HITDECK);
                return true;
            case MISS:
                enemyBoard.setCellState(x, y, CellState.MISS);
                return false;
        }
        return false;
    }

    @Override
    public TurnState checkDamage(int x, int y) {

        /////сделать ответ в ручную


//        Cell hitCell=myBoard.getCell(x,y);
//        if(hitCell.getState()== CellState.DECK) {
//            myBoard.setCellState(x,y,CellState.HITDECK);
//            myBoard.setShipsDamage(x,y);
//            return true;
//        }
//        else return false;
        return TurnState.MISS;
    }
}

