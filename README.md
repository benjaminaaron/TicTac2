TicTacToe SmartBots
=================

This is forked from our original TicTac2 game for android (https://github.com/bbuecherl/TicTac2) but stripped-down to only the two smart bots @bbuecherl and I were implementing (insights into their development here: https://github.com/bbuecherl/TicTac2/issues/9) with the purpose of revisiting the logic of the respective decision making process in order to build on it for other projects, conceptually or literally :)

Out of the box `Main.java` pits the two smart bots against each other with two marks per turn on a 5x5 field and winning length 5. The syntax for human players or random bots is in the comments if you want to play around. No visual output, it all happens in the console.

My smart bot version (graph-based, builds a decision tree) has an export function built in that creates a `graphml`-file that can be opened and layouted in yEd for instance. To utilize it the `export()` method on the `decisionGraph` (object of class `TreeBuilder`) must be called. It exports the decision tree from the very last decision making process. Meaning for thorough analysis you might want to call it after every turn of the smart bot. Or you could call it just once at the end of a game, there is already a `gameIsFinished()` method in place to catch that moment.
