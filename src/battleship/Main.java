package battleship;

import battleship.interfaces.Player;
import battleship.music.MusicPlayer;
import battleship.players.ComputerPlayer;
import battleship.players.HumanPlayer;

public class Main {
    public static void main(String[] args) {
        Player computer = new ComputerPlayer();
        Player player = new HumanPlayer();
        Runnable music = new MusicPlayer();
        music.run();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ((HumanPlayer) player).generateBoards();
        ((HumanPlayer) player).printBoards();
    }
}
