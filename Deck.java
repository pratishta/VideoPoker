//********************************************************
// Deck class for PokerTest
// by Pratishta Yerakala (py2211)
// This class generates a deck of 52 cards. I can also 
// shuffle and deal cards.
//
//********************************************************
import java.util.Random;

public class Deck {

	private Card[] theDeck;
	// the index of the top of the deck
	private int top;
	private Random randomNumber = new Random();
	private int deckSize = 52;

	public Deck() {
		theDeck = new Card[deckSize];

		// adds 52 cards of 4 suits and 13 values
		int card = 0;
		for (int value = 1; value <= 13; value++) {
			for (int suit = 1; suit <= 4; suit++) {
				theDeck[card] = new Card(suit, value);
				card++;
			}
		}
	}

	// shuffles the deck by switching two cards 1000 times
	public void shuffle() {
		for (int s = 0; s < 1000; s++) {
			// random numbers between 1 and 52 assigned to randomIndex1 and
			// randomIndex2
			int randomIndex1 = randomNumber.nextInt(deckSize);
			int randomIndex2 = randomNumber.nextInt(deckSize);
			// temporary cards assigned cards that correspond to random number
			// in deck
			Card temp1 = theDeck[randomIndex1];
			Card temp2 = theDeck[randomIndex2];
			// cards switch; temp1 is temp2 and vice versa
			theDeck[randomIndex1] = temp2;
			theDeck[randomIndex2] = temp1;
		}
		// top assigned to be the "first" card in the shuffled deck
		top = 0;
	}

	// method deals the top cards and increments top so that same indexed card
	// isn't being used
	public Card deal() {
		Card topCard = theDeck[top];
		top++;
		return topCard;
	}
}
