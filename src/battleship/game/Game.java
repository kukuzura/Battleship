package battleship.game;

import battleship.interfaces.Player;
import battleship.music.MusicPlayer;
import battleship.players.AdvancedComputerPlayer;
import battleship.players.ComputerPlayer;
import battleship.players.HumanPlayer;

public class Game {

    public static void advancedComputerPlayerGame() {
        AdvancedComputerPlayer computer = new AdvancedComputerPlayer();
        HumanPlayer player = new HumanPlayer();
        Runnable music = new MusicPlayer();
        //   music.run();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        player.randomGenerate();
        player.printBoards();
        computer.randomGenerate();
        computer.printBoards();
        while (player.isAlive() && computer.isAlive()) {
            while (!(computer).isFinishingMove() && computer.turn(player) && player.isAlive()) {
            }
            while ((computer).isFinishingMove() && player.isAlive()) {
                computer.finishingMove(player);
            }
            while (player.turn(computer) && computer.isAlive()) {

            }
        }
    }

    public static void simpleComputerPlayer() {
        HumanPlayer player = new HumanPlayer();
        ComputerPlayer computer = new ComputerPlayer();
        while (player.turn(computer) && player.isAlive() && computer.isAlive()) {
            player.printBoards();
        }
        while (computer.turn(player) && player.isAlive() && computer.isAlive()) {
                   player.printBoards();
        }

    }
}
