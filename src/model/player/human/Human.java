package model.player.human;

import java.util.Scanner;

import  model.Game;
import  model.player.AbstractPlayer;

public class Human extends AbstractPlayer{
	
	private Game game;
	private Scanner in;
	
	public Human(Game game, String name) {
		super(game);
		setPlayerType(0);
		setName(name);	
		this.game = game;
	}

	/**
	 * Tells game that we are waiting for a click. Once that click comes in, game is calling the myChoice method below directly.
	 * So this object doesn't influence the choice anymore - but given the fact that the local physical player has clicked, there is no need to modify that choice :)
	 */
	@Override
	public void myTurn() {	
		System.out.print(name + ", type your mark-placement in the form row,col:\n");
		in = new Scanner(System.in);
		
		boolean inputValid = false;
		
		while(!inputValid){	
			String input = in.nextLine();
			int row = Integer.parseInt(input.split(",")[0]); //TODO check for correct format
			int col = Integer.parseInt(input.split(",")[1]);
			
			if(row <= game.boardDim - 1 && col <= game.boardDim - 1)		
				if(myChoice(row, col))
					inputValid = true;		
			if(!inputValid)
				System.out.println("That's not a valid input, try again:  ");	
		}
	}
		

	@Override
	public boolean myChoice(int row, int column) {
		return game.placeMark(row, column);
	}
	
}
