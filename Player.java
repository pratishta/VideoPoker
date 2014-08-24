//********************************************************
// Player class for PokerTest
// by Pratishta Yerakala (py2211)
// Player class creates a player and has useful methods 
// to get the hand and to sort the hand.
//
//********************************************************
import java.util.ArrayList;
import java.util.Collections;

public class Player {

	private ArrayList<Card> hand;
	private Deck newDeck;

	// creates player
	public Player() {
		hand = new ArrayList<Card>();
		newDeck = new Deck();
	}

	// deals five random cards from top of deck
	public void playerHand() {
		newDeck.shuffle();
		for (int i = 0; i < 5; i++) {
			addCard(newDeck.deal());
		}
	}

	// adds the card c to the player's hand
	public void addCard(Card c) {
		hand.add(c);
	}

	// removes the card c from the player's hand
	public void removeCard(Card c) {
		hand.remove(c);
	}

	// method to return hand to use in other classes
	public ArrayList<Card> getHand() {
		return hand;
	}

	// method to return a card from hand array list
	public Card getCard(int i) {
		return hand.get(i);
	}

	// sorts ArrayList hand before evaluating
	public void sort() {
		Collections.sort(hand);
	}
}
