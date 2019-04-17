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
            while(player.turn(computer)&&((ComputerPlayer) computer).isAlive()&&((HumanPlayer) player).isAlive()) {
             //   System.out.println("computer");
              //  ((ComputerPlayer) computer).printBoards();
               // System.out.println("me");
               // ((HumanPlayer) player).printBoards();
            }
            while (computer.turn(player)&&((HumanPlayer) player).isAlive()&&((ComputerPlayer) computer).isAlive()) {
               // System.out.println("computer");
               // ((ComputerPlayer) computer).printBoards();
               // System.out.println("me");
               // ((HumanPlayer) player).printBoards();
            }
        }
    }
}
