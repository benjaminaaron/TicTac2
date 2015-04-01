package model.player;

import  model.Game;

public abstract class AbstractPlayer implements PlayerInterface {

	protected Game game;
	public String name;
	protected int gamesWon = 0;
	protected String winningFields = "";
	protected int ID;
	
	 // 0: local human player
	 // 1: local bot
	private int playerType;
	
	public AbstractPlayer(Game game){
		this.game = game;
	}
	
	public void setID(int ID){
		this.ID = ID;
		name = "[" + ID + "]" + name;
	}
	
	public void setPlayerType(int type){
		playerType = type;
	}
	
	public int getPlayerType(){
		return playerType;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void iWon(){
		gamesWon ++;
	}
	
	public void resetGamesWon(){
		gamesWon = 0;
	}

	public void gameIsFinished(){}
}
