import model.Game;
import model.player.AbstractPlayer;
import model.player.bot.BotRandom;
import model.player.bot.DecisionGridVersion.BotSmartDG;
import model.player.bot.GraphVersion.BotSmartG;
import model.player.human.Human;

public class Main {
	public static void main(String[] args) {	
		Game game = new Game();	
		//AbstractPlayer player1 = new BotRandom(game, false);
		//AbstractPlayer player1 = new Human(game, "Max");
		AbstractPlayer player1 = new BotSmartDG(game, false); // 2nd param is showThinking
		AbstractPlayer player2 = new BotSmartG(game, false);
		game.setPlayers(player1, player2);	
		game.initModel(5, 5, 2); // boardDim, winLength, marksPerTurn
		game.start();
		
		//TODO crashes sometimes with two random bots, probably because the placed marks are not getting removed from the available-for-random stack or something? 
	}
}
