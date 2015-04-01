package model.player.bot;

import  model.Game;


public class BotRandom extends AbstractBot{

	public BotRandom(Game game, boolean showThinking) {
		super(game, showThinking);
		setPlayerType(2);
		setName("RandomBot");
	}

	@Override
	public void myTurn() {
		myChoice(-1, -1); //"secret code" for game to call the placeRandomly method on board
	}

	@Override
	public boolean myChoice(int row, int column) {
		return game.placeMark(row, column);
	}
}
