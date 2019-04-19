package battleship;

import battleship.interfaces.Player;
import battleship.music.MusicPlayer;
import battleship.players.ComputerPlayer;
import battleship.players.HumanPlayer;
import battleship.states.TurnState;

public class Main {
    public static void main(String[] args) {
        Player computer = new ComputerPlayer();
        Player player = new HumanPlayer();
        Runnable music = new MusicPlayer();
       // music.run();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ((HumanPlayer) player).randomGenerate();
        ((HumanPlayer) player).printBoards();
        ((ComputerPlayer) computer).randomGenerate();
        ((ComputerPlayer) computer).printBoards();
        while(((HumanPlayer) player).isAlive()&&((ComputerPlayer) computer).isAlive()){
            while(true) {
                while (!((ComputerPlayer) computer).isFinishingMove()&&computer.turn(player)){
                }
                while (((ComputerPlayer) computer).isFinishingMove()){
                    ((ComputerPlayer) computer).finishingMove(player);
                }
                break;
            }
        //    while (computer.turn(player)&&((HumanPlayer) player).isAlive()&&((ComputerPlayer) computer).isAlive()) {
               // System.out.println("computer");
               // ((ComputerPlayer) computer).printBoards();
               // System.out.println("me");
               // ((HumanPlayer) player).printBoards();
 //           }
        }
    }
}
