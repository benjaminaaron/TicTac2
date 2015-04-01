package model;

import java.util.ArrayList;

import  model.board.Board;
import  model.player.AbstractPlayer;

public class Game {
	
	public int boardDim;
	private int winLength;
	private int marksPerTurn;	
	private int markCount = 0;
	
	private int startPlayerIndex;
	private int currentPlayerIndex; // = 1;	
	private AbstractPlayer currentPlayer;
	
	private AbstractPlayer[] players;
	private AbstractPlayer winner = null;
	
	private Board board;
	
	private boolean gameRunning = false;
	private boolean awaitingClick = false;
	
	public Game(){
		players = new AbstractPlayer[3];
		players[0] = null; // must be because the index is placed on the board... and 0 means not placed yet :)
	}
	
	public AbstractPlayer[] getPlayers() {
		return players;
	}
	
	public Board getBoard(){
		return board;
	}
	
	public void awaitingClick(){
		System.out.println("awaiting click from " + currentPlayer.getName());
		awaitingClick = true;
	}
	
	public boolean placeMark(int row, int col){	
		if(row == -1 && col == -1){
			board.placeRandomly(currentPlayerIndex);
			markComplete(row, col);		
			return true;
		}
		else{		
			boolean temp = board.setField(currentPlayerIndex, row, col);		
			if(temp)
				markComplete(row, col);		
			return temp;
		}
	}

	private void markComplete(int row, int col){
		System.out.println("board after mark from " + currentPlayer.getName() + " on " + row + "," + col);
		System.out.println(board.show(true));
		
		if(board.getWinner() == 0){	
			if(!board.full()){
				markCount ++;
				
				if(markCount == marksPerTurn){
					markCount = 0;
					if(currentPlayerIndex == 1)
						currentPlayerIndex = 2;
					else
						currentPlayerIndex = 1;
					
					currentPlayer = players[currentPlayerIndex];
				}
				awaitingClick = false;
				currentPlayer.myTurn();
			}
			else
				if(gameRunning){
					players[1].gameIsFinished();
					players[2].gameIsFinished();	
					System.out.println("The game ended without a winner.");
				}
				gameRunning = false; //board full without winner	
		}
		else{
			winner = currentPlayer; //should be equal to board.getWinner() ?
			winner.iWon();
			gameRunning = false;		
			players[1].gameIsFinished();
			players[2].gameIsFinished();	
			System.out.println(winner.name + " has won the game!");
		}
		
	}

	public boolean getGameRunning(){
		return gameRunning;
	}
	
	public String showBoard() {
		return board.show(true);
	}
	
	public boolean handleLocalPlayerClick(int row, int column) {
		if(awaitingClick){		
			boolean temp = currentPlayer.myChoice(row, column);	
			if(!temp)
				System.out.println("field already taken, choose another one");
			return temp;
		}		
		return false;
	}
	
	public void initModel(int boardDim, int winLength, int marksPerTurn) {
		this.boardDim = boardDim;
		this.winLength = winLength;
		this.marksPerTurn = marksPerTurn;
		this.startPlayerIndex = 1;
		currentPlayerIndex = startPlayerIndex;
		board = new Board(boardDim, winLength);
	}
	
	public void setPlayers(AbstractPlayer player1, AbstractPlayer player2){
		players[1] = player1;
		player1.setID(1);
		players[2] = player2;
		player2.setID(2);
	}
	
	public void start(){	
		gameRunning = true;
		System.out.println(">> Game started with players: " + players[1].getName() + " and " + players[2].getName());				
		System.out.println(board.show(true));		
		currentPlayer = players[startPlayerIndex];
		currentPlayer.myTurn();
	}
	
	public void reset() {
		currentPlayerIndex = startPlayerIndex;
		board.reset();
	}

	public int getBoardDim() {
		return boardDim;
	}

	public int getWinLength() {
		return winLength;
	}

	public int getMarksPerTurn() {
		return marksPerTurn;
	}

	public int getStartPlayerIndex() {
		return startPlayerIndex;
	}
	
	public int getCurrentPlayerIndex() {
		return currentPlayerIndex;
	}

	public AbstractPlayer getWinner() {
		return winner;
	}

	public ArrayList<int[]> getGameRecording() {
		return board.getHistory();
	}
}
