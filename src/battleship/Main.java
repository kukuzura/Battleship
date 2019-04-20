package battleship;

import battleship.interfaces.Player;
import battleship.music.MusicPlayer;
import battleship.players.AdvancedComputerPlayer;
import battleship.players.ComputerPlayer;
import battleship.players.HumanPlayer;
import battleship.states.TurnState;

public class Main {
    public static void main(String[] args) {
        AdvancedComputerPlayer computer= new AdvancedComputerPlayer();
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
        computer.randomGenerate();
        computer.printBoards();
        while(((HumanPlayer) player).isAlive()&&((ComputerPlayer) computer).isAlive()){
            while(true) {
                while (!(computer).isFinishingMove()&&computer.turn(player)){
                }
                while ((computer).isFinishingMove()){
                    computer.finishingMove(player);
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
