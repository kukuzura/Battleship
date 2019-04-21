package battleship;

import battleship.game.Terminal;
import battleship.interfaces.Player;
import battleship.music.MusicPlayer;
import battleship.players.AdvancedComputerPlayer;
import battleship.players.ComputerPlayer;
import battleship.players.HumanPlayer;
import battleship.states.TurnState;

public class Main {
    public static void main(String[] args) {
        Terminal terminal= new Terminal();
        terminal.startMenu();
    }
}
