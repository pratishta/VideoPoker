//********************************************************
// PokerTest
// used by Pratishta Yerakala (py2211)
// This is a tester class to test methods from Deck, Card,
// Game and Player to play a game of Video Poker. 
//
//********************************************************
public class PokerTest {
	//this class must remain unchanged
	public static void main(String[] args){
		if (args.length<1){
			Game g = new Game();
			g.play();
		}
		else{
			Game g = new Game(args);
			g.play();
		}
	}
}
