import Controlllers.GameController;
import models.Board;
import models.Dice;
import models.Jump;
import models.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {

        GameController gameController = new GameController(new Board(100), new ArrayList<>(), new Dice(1, 1, 6));

        System.out.println("WELCOME TO THE SNAKE AND LADDER GAME");
        Scanner scanner = new Scanner(System.in);
        String commands1 = scanner.nextLine();
        String [] command1 = commands1.split(" ");

        int numberOfSnakes = Integer.parseInt(command1[0]);


        while(numberOfSnakes>0){

            String commands = scanner.nextLine();
            String [] command = commands.split(" ");

            int start = Integer.parseInt(command[0]);
            int end = Integer.parseInt(command[1]);

            if(gameController.addSnake(new Jump(start, end))){
                numberOfSnakes--;
            }
        }

        commands1 = scanner.nextLine();
        command1 = commands1.split(" ");

        int numberOfLadders = Integer.parseInt(command1[0]);

        while(numberOfLadders > 0){

            String commands = scanner.nextLine();
            String [] command = commands.split(" ");

            int start = Integer.parseInt(command[0]);
            int end = Integer.parseInt(command[1]);

            if(gameController.addLadder(new Jump(start, end))){
                numberOfLadders--;
            }
        }

        commands1 = scanner.nextLine();
        command1 = commands1.split(" ");
        int numberOfPlayers = Integer.parseInt(command1[0]);

        String firstPlayer = scanner.nextLine();
        Player player1 = new Player(firstPlayer);

        String secondPlayer = scanner.nextLine();
        Player player2 = new Player(secondPlayer);

        List<Player>players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        gameController.setPlayers(players);
        gameController.startGame(numberOfPlayers);

    }
}
