//********************************************************
// Card class for PokerTest
// by Pratishta Yerakala (py2211)
// This class creates object card and assigns to it suit
// and value. It also compares them for card games and 
// turns them into readable strings. 
//********************************************************
public class Card implements Comparable<Card> {

	private int suit;
	// make final ints to keep track of number assignments for suits
	final int CLUBS = 1;
	final int DIAMONDS = 2;
	final int HEARTS = 3;
	final int SPADES = 4;

	// value of card is its number (numbers 1 - 13)
	private int value;

	// card constructor that has suit s and value v
	public Card(int s, int v) {
		suit = s;
		value = v;
	}

	// compareTo method makes it easy to sort cards
	public int compareTo(Card c) {
		if (this.value > c.value)
			return 1;
		else if (this.value < c.value)
			return -1;
		else if (this.value == c.value)
			if (this.suit > c.suit)
				return 1;
			else if (this.suit < c.suit)
				return -1;

		return 0;
	}

	// returns value of card
	public int getValue() {
		return this.value;
	}

	// returns suit of card
	public int getSuit() {
		return this.suit;
	}

	// valueName assigned to represent actual names of cards
	String valueName;

	public String toString() {
		if (value == 1)
			valueName = "Ace";
		if (value == 2)
			valueName = "Two";
		if (value == 3)
			valueName = "Three";
		if (value == 4)
			valueName = "Four";
		if (value == 5)
			valueName = "Five";
		if (value == 6)
			valueName = "Six";
		if (value == 7)
			valueName = "Seven";
		if (value == 8)
			valueName = "Eight";
		if (value == 9)
			valueName = "Nine";
		if (value == 10)
			valueName = "Ten";
		if (value == 11)
			valueName = "Jack";
		if (value == 12)
			valueName = "Queen";
		if (value == 13)
			valueName = "King";

		// suits convert into actual names as well
		if (suit == CLUBS) {
			return valueName + " of Clubs";
		}
		if (suit == DIAMONDS) {
			return valueName + " of Diamonds";
		}
		if (suit == HEARTS) {
			return valueName + " of Hearts";
		}
		if (suit == SPADES) {
			return valueName + " of Spades";
		}
		return "";
	}
}
