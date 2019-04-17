package battleship.players;

import battleship.WorkingWithCoordinates;
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

    public void randomGenerate(){
        enemyBoard.generateEnemyBoard();
        myBoard.randomGenerate();
    }

    public void printBoards() {
        //System.out.println("   \u0430 \u0431 \u0432 \u0433  \u0434  \u0435 \u0436 \u0437 \u0438 \u043A" + "     " + " а б в г д е ж з и к");
        System.out.printf(" %3s%2s%3s%2s%3s%2s%3s%2s%3s%2s",'а','б','в','г','д','е','ж','з','и','к');
        System.out.print("   ");
        System.out.printf(" %3s%2s%3s%2s%3s%2s%3s%2s%3s%2s",'а','б','в','г','д','е','ж','з','и','к');
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

    public boolean isAlive(){
       if(myBoard.getShipsAmount()==0){
           return false;
       }
       else return true;
    }

    @Override
    public boolean turn(Player comp) {
        printBoards();
        System.out.println("Ваш ход");
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите координату (например, а1)");
        String str = scan.nextLine();
        while (WorkingWithCoordinates.coordinateCheck(str)==false){
            System.out.println("Неверный ввод");
            str = scan.nextLine();
        }
        int[] coordinates=WorkingWithCoordinates.getTwoCoordinatesAsInt(str);
        int x=coordinates[1]-1;
        int y = coordinates[0];
        switch (comp.checkDamage(x, y)) {
            case DESTROYED:
                System.out.println("Убил");
                enemyBoard.setCellState(x, y, CellState.HITDECK);
                enemyBoard.setMISSAroundShip(x, y);
                return true;
            case HIT:
                System.out.println("Ранил");
                enemyBoard.setCellState(x, y, CellState.HITDECK);
                return true;
            case MISS:
                System.out.println("Мимо");
                if(enemyBoard.getCellState(x,y)!=CellState.HITDECK) {
                    enemyBoard.setCellState(x, y, CellState.MISS);
                }
                return false;
        }
        return false;
    }

    @Override
    public TurnState checkDamage(int x, int y) {
        printBoards();
        Scanner scan = new Scanner(System.in);
        System.out.println("1.Мимо");
        System.out.println("2.Попал");
        System.out.println("3.Убил");
        switch (scan.next()) {
            case "1":
                return TurnState.MISS;
            case "2":
                return TurnState.HIT;
            case "3":
                return TurnState.DESTROYED;

        }
        return TurnState.MISS;
    }
}

