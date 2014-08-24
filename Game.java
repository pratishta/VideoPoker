//********************************************************
// Game class for PokerTest
// by Pratishta Yerakala (py2211)
// Game class can take in String of custom cards created 
// by user or deal random cards from top of the deck; it
// will then evaluate the hand and print it to user. 
//********************************************************
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
	private Player p;
	private Deck cards;
	private Scanner input = new Scanner(System.in);
	private int tokens = 50;

	// constructor for when command-line arguments taken
	public Game(String[] testHand) {
		cards = new Deck();
		p = new Player();

		int suit = 0;
		int value = 0;

		// parses the user input to make suit out of the first character of each
		// element in testHand
		for (int i = 0; i < 5; i++) {
			char suitC = testHand[i].charAt(0);
			if (suitC == 'c')
				suit = 1;
			if (suitC == 'd')
				suit = 2;
			if (suitC == 'h')
				suit = 3;
			if (suitC == 's')
				suit = 4;

			// parses the user input to make value out of substring starting
			// with
			// second character (index 1)
			value = Integer.parseInt(testHand[i].substring(1));

			// adds card with parsed suit and value from user input to player's
			// hand
			p.addCard(new Card(suit, value));
		}
	}

	// constructor for no command-line arguments
	public Game() {
		// had new deck
		cards = new Deck();

		// can take input from user
		input = new Scanner(System.in);

		// creates a new player and player's hand
		p = new Player();
		p.playerHand();
	}

	boolean playAgain = true;
	String remove;
	String removeCard;
	String answer;

	public void play() {
		System.out.println("Welcome to Video Poker!");
		while (playAgain) {
			// shuffles deck
			cards.shuffle();
			System.out.println("Your tokens: " + tokens);

			// sorts the hand from command-line arguments and when Game()
			// creates new hand
			p.sort();

			System.out.println("Your cards: ");
			System.out.println(p.getHand());

			// user gets choice to swap cards
			System.out.println("Do you want to remove your card(s)? y/n");
			remove = input.next();

			// if user wants to swap cards
			if (remove.equals("y")) {
				// ask user if want to swap cards 1-5 individually from their
				// hand
				for (int i = 0; i < 5; i++) {
					System.out.println("Do you want to remove card " + (i + 1)
							+ "? y/n");
					removeCard = input.next();
					// remove chosen card and replace with top card
					if (removeCard.equals("y")) {
						p.getHand().remove(i);
						p.getHand().add(cards.deal());
					}
				}

				// sorts the new hand
				p.sort();
				System.out.println("Your new hand is: ");
				System.out.println(p.getHand());

				// checks new hand to see what winnings are
				checkHand(p.getHand());
			}

			// if player doesn't want to swap cards, automatically check the
			// hand
			if (remove.equals("n")) {
				checkHand(p.getHand());
			}

			// prompt if player wants to play again
			System.out.println("Do you want to play again? y/n");
			answer = input.next();

			// if player doesn't want to play, ends the while loop in play
			// method
			if (answer.equals("n")) {
				playAgain = false;
				System.out.println("Your remaining tokens: " + tokens);
				System.out.println("Thanks for playing!");
			}

			// if player wants to play again, makes a new player hand and
			// decrements tokens as payment for playing again
			else {
				tokens--;
				p = new Player();
				p.playerHand();
			}
		}
	}

	// takes input from scanner for hand and determines what result is
	// accounts for token gains after each winning
	public String checkHand(ArrayList<Card> hand) {

		if (royalFlush()) {
			tokens = tokens + 250;
			System.out.println("Royal Flush!");
		} else if (straightFlush()) {
			tokens = tokens + 50;
			System.out.println("Straight Flush!");
		} else if (fourOfAKind()) {
			tokens = tokens + 25;
			System.out.println("Four of a Kind!");
		} else if (fullHouse()) {
			tokens = tokens + 6;
			System.out.println("Full House!");
		} else if (flush()) {
			tokens = tokens + 5;
			System.out.println("Flush!");
		} else if (straight()) {
			tokens = tokens + 4;
			System.out.println("Straight!");
		} else if (threeOfAKind()) {
			tokens = tokens + 3;
			System.out.println("Three of a Kind!");
		} else if (twoPair()) {
			tokens = tokens + 2;
			System.out.println("Two Pair!");
		} else if (onePair()) {
			tokens = tokens + 1;
			System.out.println("One Pair!");
		} else
			System.out.println("No Pair...");
		return "";
	}

	// methods to individually checks what each hand can result in

	// checks if it's a straight flush and if the last card is king
	boolean royalFlush() {
		boolean isRoyalFlush = false;
		if (straightFlush() && p.getCard(4).getValue() == 13) {
			isRoyalFlush = true;
		}
		return isRoyalFlush;
	}

	// checks if it's a flush and a straight
	boolean straightFlush() {
		boolean isStraightFlush = false;
		if (flush() && straight()) {
			isStraightFlush = true;

		}
		return isStraightFlush;
	}

	// checks if first card is equal to the fourth card and if second card is
	// equal to the fifth card because when sorted, if they're the same the ones
	// in middle also same card
	boolean fourOfAKind() {
		boolean isFourOfAKind = false;
		if (p.getCard(0).getValue() == p.getCard(3).getValue())
			isFourOfAKind = true;
		if (p.getCard(1).getValue() == p.getCard(4).getValue())
			isFourOfAKind = true;

		return isFourOfAKind;
	}

	boolean fullHouse() {
		boolean isFullHouse = false;
		if (p.getCard(0).getValue() == p.getCard(2).getValue()
				&& p.getCard(3).getValue() == p.getCard(4).getValue())
			isFullHouse = true;
		else if (p.getCard(0).getValue() == p.getCard(1).getValue()
				&& p.getCard(2).getValue() == p.getCard(4).getValue())
			isFullHouse = true;

		return isFullHouse;
	}

	// checks if all the suits are the same
	boolean flush() {
		boolean isFlush = false;
		if (p.getCard(0).getSuit() == p.getCard(1).getSuit()) {
			if (p.getCard(1).getSuit() == p.getCard(2).getSuit()) {
				if (p.getCard(2).getSuit() == p.getCard(3).getSuit()) {
					if (p.getCard(3).getSuit() == p.getCard(4).getSuit()) {
						isFlush = true;
					}
				}
			}
		}
		return isFlush;
	}

	// checks if adjacent cards are one more or one less than each other
	boolean straight() {
		boolean isStraight = false;
		if (p.getCard(1).getValue() == p.getCard(0).getValue() + 1) {
			if (p.getCard(2).getValue() == p.getCard(1).getValue() + 1) {
				if (p.getCard(3).getValue() == p.getCard(2).getValue() + 1) {
					if (p.getCard(4).getValue() == p.getCard(3).getValue() + 1) {
						isStraight = true;
					}
				}
			}
		}
		// when sorting, has high ace at the beginning so second card (index 1)
		// must be 10
		if (p.getCard(0).getValue() == 1 && p.getCard(1).getValue() == 10
				&& p.getCard(2).getValue() == 11
				&& p.getCard(3).getValue() == 12
				&& p.getCard(4).getValue() == 13) {
			isStraight = true;
		}
		return isStraight;
	}

	boolean threeOfAKind() {
		boolean isThreeOfAKind = false;
		if (p.getCard(0).getValue() == p.getCard(2).getValue())
			isThreeOfAKind = true;
		else if (p.getCard(1).getValue() == p.getCard(3).getValue())
			isThreeOfAKind = true;
		else if (p.getCard(2).getValue() == p.getCard(4).getValue())
			isThreeOfAKind = true;

		return isThreeOfAKind;
	}

	boolean twoPair() {
		boolean isOnePair = false;
		if (p.getCard(0).getValue() == p.getCard(1).getValue()
				&& p.getCard(2).getValue() == p.getCard(3).getValue())
			isOnePair = true;
		else if (p.getCard(0).getValue() == p.getCard(1).getValue()
				&& p.getCard(3).getValue() == p.getCard(4).getValue())
			isOnePair = true;
		else if (p.getCard(1).getValue() == p.getCard(2).getValue()
				&& p.getCard(3).getValue() == p.getCard(4).getValue())
			isOnePair = true;

		return isOnePair;
	}

	boolean onePair() {
		boolean isOnePair = false;
		if (p.getCard(0).getValue() == p.getCard(1).getValue()
				|| p.getCard(1).getValue() == p.getCard(2).getValue()
				|| p.getCard(2).getValue() == p.getCard(3).getValue()
				|| p.getCard(3).getValue() == p.getCard(4).getValue())
			isOnePair = true;

		return isOnePair;
	}

}
