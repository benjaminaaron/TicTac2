package model.player.bot.DecisionGridVersion;

import  model.Game;
import  model.player.bot.AbstractBot;

/**
 * Class representing an intelligent bot
 * 
 * @author agarsia (Bernhard Buecherl)
 * @version 1.0
 * @since 2.0
 */
public class BotSmartDG extends AbstractBot {

	private int[][] decisiongrid;
	private boolean debug;
	private boolean hard; 
	private int mark;
	private boolean first;
	
	public BotSmartDG(Game game, boolean showThinking) {
		super(game, showThinking);	
		this.hard = true; // bot is not intelligent if false
		this.debug = showThinking;
		mark = 0;
		setPlayerType(1);
		setName("SmartBot-DG");// + Integer.toHexString(hashCode()) + "");
		first = true;	
	}

	@Override
	public void myTurn() {
		if(first)
			decisiongrid = DecisionGrid.get(game);

		first = false;
		if (mark++ == 0) {

			if (hard && game.getBoard().getHistory().size() != 0)
				DecisionGrid.refactor(debug, decisiongrid, game.getBoard(), game.getMarksPerTurn(), game.getWinLength());

			if (debug) {
				System.out.println("\ndecision tree:");
				DecisionGrid.print(decisiongrid);
			}
		}

		mark %= game.getMarksPerTurn();

		if (game.getGameRunning()) {
			int[] pos = DecisionGrid.decide(decisiongrid, game.getBoard());
			myChoice(pos[1], pos[2]);
		}
	}

	@Override
	public boolean myChoice(int row, int column) {
		if(debug)
			System.out.println("decided for " + row + "," + column + "\n");
		return game.placeMark(row, column);
	}
}