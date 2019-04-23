package battleship.players;

import battleship.util.WorkingWithCoordinates;
import battleship.field.GameBoard;
import battleship.states.CellState;
import battleship.states.TurnState;

import java.util.Scanner;

public class HumanPlayerClass implements Player {

    GameBoard myBoard;
    GameBoard enemyBoard;

    public HumanPlayerClass(){
        myBoard = new GameBoard();
        enemyBoard = new GameBoard();
    }

    @Override
    public boolean turn(Player comp) {
        printBoards();
        System.out.println(" \n \u2694 "+"Ваш ход"+" \u2694 \n");
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите координату (например, а1)");
        String str = scan.nextLine();
        while (WorkingWithCoordinates.coordinateCheck(str) == false) {
            System.out.println("Неверный ввод");
            str = scan.nextLine();
        }
        int[] coordinates = WorkingWithCoordinates.getTwoCoordinatesAsInt(str);
        int x = coordinates[1] - 1;
        int y = coordinates[0];
        switch (comp.checkDamage(x, y)) {
            case DESTROYED:
                System.out.println("\n \u2620  "+"Убил" +"  \u2620 \n");
                enemyBoard.setCellState(x, y, CellState.HITDECK);
                enemyBoard.setMISSAroundShip(x, y);
                return true;
            case HIT:
                System.out.println(" \n Ранил \n");
                enemyBoard.setCellState(x, y, CellState.HITDECK);
                return true;
            case MISS:
                System.out.println(" \n Мимо \n ");
                if (enemyBoard.getCellState(x, y) != CellState.HITDECK) {
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
        while (true) {
            System.out.println("1.Мимо");
            System.out.println("2.Попал");
            System.out.println("3.Убил \u2620");
            System.out.printf("Введите цифру \n");
            switch (scan.next()) {
                case "1":
                    return TurnState.MISS;
                case "2":
                    myBoard.setCellState(x,y,CellState.HITDECK);
                    return TurnState.HIT;
                case "3":
                    myBoard.setCellState(x,y,CellState.HITDECK);
                    return TurnState.DESTROYED;
                default:
                    System.out.println("Неверный ввод");
            }
        }
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

    public void randomGenerate() {
        enemyBoard.generateBoard(CellState.UNDEFINED);
        myBoard.randomGenerate();
    }

    public boolean isAlive() {
        if (myBoard.getShipsAmount() == 0) {
            return false;
        } else return true;
    }

}

