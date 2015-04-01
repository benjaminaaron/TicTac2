package model.player.bot;

import  model.Game;
import  model.board.Board;
import  model.player.AbstractPlayer;

public abstract class AbstractBot extends AbstractPlayer {

	protected int boardDim;
	protected Board board;
	protected boolean showThinking;
	
	public AbstractBot(Game game, boolean showThinking){
		super(game);
		this.boardDim = game.getBoardDim();
		this.board = game.getBoard();
		this.showThinking = showThinking;
	}
}
