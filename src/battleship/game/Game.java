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
        System.out.println("\n Введите ваше имя \n");
        String playerName = scan.nextLine();
        AdvancedComputerPlayerClass computer = new AdvancedComputerPlayerClass();
        HumanPlayerClass player = new HumanPlayerClass();
        Thread music=new Thread(new MusicPlayer());
        music.run();
        player.randomGenerate();
        computer.randomGenerate();
        boolean finishMoveSuccesful = true;    // если добивание закончилось убийстовм корабля
        boolean simpleTurnResult = true;
        while (player.isAlive() && computer.isAlive()) {
            while (simpleTurnResult && player.isAlive() && finishMoveSuccesful) {      //если промазать при добивании то не зайдет
                if (!(computer.isFinishingMove())&&player.isAlive()) {
                    simpleTurnResult = computer.turn(player);
                }
                while (finishMoveSuccesful && (computer).isFinishingMove() && player.isAlive()) {
                    finishMoveSuccesful = computer.finishingMove(player);
                }
            }
            finishMoveSuccesful = true;
            simpleTurnResult = true;
            while (computer.isAlive() && player.turn(computer)) {

            }
        }

        music.interrupt();
        if (player.isAlive()) {
            System.out.println(" \n \u263A Вы выиграли \u263A \n");
            FileReader.inputGameResult("result.txt", getGameResult(player, playerName));
        } else
            System.out.println("\n \u2639 Вы проиграли \u2639 \n ");
        FileReader.inputGameResult("result.txt", getGameResult(player, playerName));
    }

    public static void simpleComputerPlayer() {
        Scanner scan = new Scanner(System.in);
        System.out.println("\n Введите ваше имя \n");
        String playerName = scan.nextLine();
        HumanPlayerClass player = new HumanPlayerClass();
        ComputerPlayerClass computer = new ComputerPlayerClass();
        Thread music=new Thread(new MusicPlayer());
        music.run();
        player.randomGenerate();
        computer.randomGenerate();
        while (player.isAlive() && computer.isAlive() && player.turn(computer)) {
            player.printBoards();
        }
        while (player.isAlive() && computer.isAlive() && computer.turn(player)) {
            player.printBoards();
        }

        music.interrupt();

        if (player.isAlive()) {
            System.out.println(" \n \u263A Вы выиграли \u263A \n");
            FileReader.inputGameResult("result.txt", getGameResult(player, playerName));
        } else
            System.out.println("\n \u2639 Вы проиграли \u2639 \n ");
        FileReader.inputGameResult("result.txt", getGameResult(player, playerName));
    }


    public static String getGameResult(HumanPlayerClass player, String playerName) {
        Date date = new Date();
        if (player.isAlive()) {
            return date.toString() + " Победитель " + playerName;
        } else
            return date.toString() + " Победитель компьютер";
    }

}
