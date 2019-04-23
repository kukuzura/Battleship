package battleship.game;

import battleship.music.MusicPlayer;
import battleship.players.AdvancedComputerPlayerClass;
import battleship.players.ComputerPlayerClass;
import battleship.players.HumanPlayerClass;
import battleship.util.FileReader;

import java.util.Date;
import java.util.Scanner;

public class Game {

    public static void advancedComputerPlayerGame() {
        Scanner scan = new Scanner(System.in);
        String playerName = scan.nextLine();
        AdvancedComputerPlayerClass computer = new AdvancedComputerPlayerClass();
        HumanPlayerClass player = new HumanPlayerClass();
        Runnable music = new MusicPlayer();
        music.run();
        player.randomGenerate();
        computer.randomGenerate();
        boolean finishMoveSuccesful = true;    // если добивание закончилось убийстовм корабля
        boolean simpleTurnResult = true;
        while (player.isAlive() && computer.isAlive()) {
            while (simpleTurnResult && player.isAlive() && finishMoveSuccesful) {      //если промазать при добивании то не зайдет
                if (!(computer.isFinishingMove())) {
                    simpleTurnResult = computer.turn(player);
                }
                while (finishMoveSuccesful && (computer).isFinishingMove() && player.isAlive()) {
                    finishMoveSuccesful = computer.finishingMove(player);
                }
            }
            finishMoveSuccesful = true;
            simpleTurnResult = true;
            while (player.turn(computer) && computer.isAlive()) {

            }
        }

        if (player.isAlive()) {
            FileReader.inputGameResult("result.txt", getGameResult(player, playerName));
        } else
            FileReader.inputGameResult("result.txt", getGameResult(player, playerName));
    }

    public static void simpleComputerPlayer() {
        HumanPlayerClass player = new HumanPlayerClass();
        ComputerPlayerClass computer = new ComputerPlayerClass();
        Runnable music = new MusicPlayer();
        music.run();
        player.randomGenerate();
        computer.randomGenerate();
        while (player.turn(computer) && player.isAlive() && computer.isAlive()) {
            player.printBoards();
        }
        while (computer.turn(player) && player.isAlive() && computer.isAlive()) {
            player.printBoards();
        }

    }

    public static String getGameResult(HumanPlayerClass player, String playerName) {
        Date date = new Date();
        if (player.isAlive()) {
            return date.toString() + " Победитель " + playerName;
        } else
            return date.toString() + " Победитель компьютер";
    }

}
