package battleship.game;

import battleship.tutorial.FileReader;

import java.util.Scanner;

public class Terminal {
    public void startMenu() {
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("Выберите пункт(введите цифру)");
            System.out.println("1.Начать игру");
            System.out.println("2.Краткие правила");
            System.out.println("3.Выход");

            switch (scan.next()) {
                case "1":
                    difficultyLevelsMenu();
                    break;
                case "2":
                    FileReader.outputText("rules.txt");
                    break;
                case "3":
                    System.exit(0);
                    break;
            }

        }
    }

    public void difficultyLevelsMenu() {
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("Выберите уровень сложности(введите цифру)");
            System.out.println("1.Случайный обстрел");
            System.out.println("2.Добивание");
            System.out.println("3.Информация ою уровнях");
            System.out.println("4.Вернутся в главное меню");
            switch (scan.next()) {
                case "1":
                    Game.simpleComputerPlayer();
                    break;
                case "2":
                    Game.advancedComputerPlayerGame();
                    break;
                case "3":
                    FileReader.outputText("difficultyLevels.txt");
                case "4":
                    return;
            }

        }
    }
}
