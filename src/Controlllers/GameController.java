package Controlllers;

import models.*;

import java.util.ArrayList;
import java.util.List;



public class GameController {
    private Board board;
    private List<Player> players;
    private Dice dice;

    public GameController(Board board, List<Player> players, Dice dice) {
        this.board = board;
        this.players = new ArrayList<>();
        this.dice = dice;
    }

    public Dice getDice() {
        return dice;
    }

    public void setDice(Dice dice) {
        this.dice = dice;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public boolean movePlayer(int steps, Player player){
        final int MaxPosition = 100;
        int currentPosition = player.getPosition();
        if(currentPosition + steps > MaxPosition){
            return false;
        }

        int finalPosition = currentPosition + steps;

        Cell[] cells = board.getCells();
        Cell currentCell = cells[finalPosition];
        while(currentCell.getJump()!=null){

            currentCell = cells[currentCell.getJump().getEnd()];
            finalPosition = currentCell.getPosition();

        }

        player.setPosition(finalPosition);
        printMovementDetails(player, steps, currentPosition, finalPosition);
        return true;
    }

    public boolean addSnake(Jump jump){

        int start = jump.getStart();
        int end = jump.getEnd();

        if(start <= end){
            //throw new RuntimeException("The Snake head is less than the tail");
            return false;
        }

        Cell cell = board.getCells()[start];
        cell.setJump(new Jump(start, end));
        return true;
    }

    public boolean addLadder(Jump jump){

        int start = jump.getStart();
        int end = jump.getEnd();

        if(start >= end){
            //throw new RuntimeException("The Snake head is less than the tail");
            return false;
        }

        Cell cell = board.getCells()[start];
        cell.setJump(new Jump(start, end));
        return true;
    }

    public boolean checkWinner(Player player){

        return player.getPosition() == board.getSize();

    }

    public void startGame(int numberOfPlayers){
        int currentPlayerIndex = 0;

        while(true) {
            Player playerTurn = players.get(currentPlayerIndex);
            int currentPosition = playerTurn.getPosition();
            int diceCount = getDice().rollDice();
            movePlayer(diceCount, playerTurn);
            if(checkWinner(playerTurn)){
                System.out.println(playerTurn.getName() + " wins the game.");
                break;
            }

            currentPlayerIndex = (currentPlayerIndex+1)%numberOfPlayers;
        }
    }

    private void printMovementDetails(Player player, int diceCount, int currentPosition, int finalPosition){
        System.out.println(player.getName() + " rolled a " + diceCount + " and moved from " + currentPosition + " to " + finalPosition);
    }

}
