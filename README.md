TicTacToe SmartBots
=================

This is forked from [our original TicTac2 game for android](https://github.com/bbuecherl/TicTac2) but stripped-down to only the two smart bots @bbuecherl and I were implementing (insights into their development [here](https://github.com/bbuecherl/TicTac2/issues/9)) with the purpose of revisiting the logic of the respective decision making process in order to build on it for other projects, conceptually or literally :smiley:

Out of the box `Main.java` pits the two smart bots against each other with two marks per turn on a 5x5 field and winning length 5. The syntax for human players or random bots is in the comments if you want to play around. No visual output, it all happens in the console.

My smart bot version (graph-based, builds a decision tree) has an export function built in that creates a `graphml`-file that can be opened and layouted in yEd for instance. To utilize it the `export()` method on the `decisionGraph` (object of class `TreeBuilder`) must be called. It exports the decision tree from the very last decision making process. Meaning for thorough analysis you might want to call it after every turn of the smart bot. Or you could call it just once at the end of a game, there is already a `gameIsFinished()` method in place to catch that moment.

These images I created using the exporter and yEd for the layout: 

<br>

<img src="https://camo.githubusercontent.com/8d42f164d95559ca2249d9fabe348ac728418150/68747470733a2f2f662e636c6f75642e6769746875622e636f6d2f6173736574732f353134313739322f313633353832352f30663732336131632d353762392d313165332d383934332d6465626238326163343133372e706e67" width="400px" height="400px"/><img src="https://camo.githubusercontent.com/89690fa7ae84ac0b73e007b06567302401deed8d/68747470733a2f2f662e636c6f75642e6769746875622e636f6d2f6173736574732f353134313739322f313633353832362f31336664396635342d353762392d313165332d393164372d3561303335333533373331372e706e67" width="400px" height="400px"/>

<br>

![](https://camo.githubusercontent.com/c6e6b8ade10aeaa4a3bf29b9bfec129a8353c2f2/68747470733a2f2f662e636c6f75642e6769746875622e636f6d2f6173736574732f353134313739322f313636383834392f30366137313732382d356336642d313165332d393839312d3764623662356166633965612e676966)
