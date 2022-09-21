package Controlllers;

import models.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GameController {
    private Board board;
    private List<Player> players;
    private Dice dice;

    public GameController(Board board, List<Player> players, Dice dice) {
        this.board = board;
        this.players = players;
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
            int unreachablePosition = currentPosition + steps;
            System.out.println(player.getName() + " is not able to move from " + currentPosition + " to " + unreachablePosition);
            return false;
        }

        int finalPosition = currentPosition + steps;

//        Cell[] cells = board.getCells();
//        Cell currentCell = cells[finalPosition];
//        while(currentCell.getJump()!=null){
//
//            currentCell = cells[currentCell.getJump().getEnd()];
//            finalPosition = currentCell.getPosition();
//
//        }

        // move the player till he's finding the snake or ladder
        while(board.getCells()[finalPosition].getJump() != null){

            finalPosition = board.getCells()[finalPosition].getJump().getEnd();

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

        Map<String, Boolean> hasPlayerRolledDice = new HashMap<>();
        boolean noWinner = true;

        while(noWinner) {
            initialiseMap(hasPlayerRolledDice);
            int playerTurnIndex=0;
            while(playerTurnIndex < numberOfPlayers){

                //System.out.println("----playerTurnIndex----- "+ playerTurnIndex);
                Player playerTurn = players.get(playerTurnIndex);

                // if the player has already taken the turn , then we don't give him the dice
                if(hasPlayerRolledDice.get(playerTurn.getName()) == true){
                    continue;
                }
                playerTurnIndex++;

                int diceCount = getDice().rollDice();
                movePlayer(diceCount, playerTurn);
                hasPlayerRolledDice.put(playerTurn.getName(), true);
                if(checkWinner(playerTurn)){
                    System.out.println(playerTurn.getName() + " wins the game.");
                    noWinner = false;
                    break;
                }
            }
        }
    }

    private void initialiseMap(Map<String, Boolean> hasPlayerRolledDice){

        for (Player player : players) {
            hasPlayerRolledDice.put(player.getName(), false);
        }

    }

    private void printMovementDetails(Player player, int diceCount, int currentPosition, int finalPosition){
        System.out.println(player.getName() + " rolled a " + diceCount + " and moved from " + currentPosition + " to " + finalPosition);
    }

}
