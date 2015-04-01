package model.player.bot.GraphVersion;

import model.Game;
import model.player.bot.AbstractBot;
import model.player.bot.GraphVersion.tree.TreeBuilder;

public class BotSmartG extends AbstractBot{

	//int turnCount = 0;
	private TreeBuilder decisionGraph;
	private int marksPerTurn;
	private int marksCount = 0;
	
	public BotSmartG(Game game, boolean showThinking) {
		super(game, showThinking);
		setPlayerType(1);
		setName("SmartBot-G");// this.hashCode());
		marksPerTurn = game.getMarksPerTurn();
	}

	@Override
	public void myTurn() {			
		marksCount ++;
		if(marksCount > marksPerTurn)
			marksCount = 1;
				
		if(showThinking)
			System.out.println("it's smart bots turn [player# " + game.getCurrentPlayerIndex() + ", turn " + marksCount + " of " + marksPerTurn + "]");
		
		int dynamicDepth = game.getBoard().getFreeFieldCount();		
		
		int cap = 5;
		
		if(game.getBoard().getBoardDim() > 3)
			cap --;
		if(game.getBoard().getBoardDim() > 4)
			cap --;
		if(game.getBoard().getBoardDim() > 5)
			cap --;			
		if(dynamicDepth > cap)
			dynamicDepth = cap;
			
		long start = System.nanoTime();
		
		decisionGraph = new TreeBuilder(showThinking, dynamicDepth, marksCount, game.getBoard(), game.getCurrentPlayerIndex(), game.getMarksPerTurn());	
		
		long timeNeeded = (System.nanoTime() - start) / 1000000;		
		if(showThinking)
			System.out.println("decision made after " + timeNeeded + " miliseconds");
		
		int chosenIndex = decisionGraph.getChoiceIndex();		
		int row = chosenIndex / game.getBoardDim();
		int column = chosenIndex % game.getBoardDim();
		
		if(showThinking)
			System.out.println("chosenIndex: " + chosenIndex + " > row: " + row + " column: " + column);
		
		if(timeNeeded < 400)
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
		myChoice(row, column);				
	}

	@Override
	public boolean myChoice(int row, int column) {
		return game.placeMark(row, column);
	}	
	
	@Override
	public void gameIsFinished() {
		//decisionGraph.export(); // this is only the very last decision tree (usually rather small). to better see how the decision tree process works, it should be exported after every turn
	}
}
