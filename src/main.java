import java.util.ArrayList;

import card.Card;
import card.deck.DeckOfCards;
import card.deck.StandardDeckOfCards;

public class main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DeckOfCards deckOfCards = new StandardDeckOfCards();

		deckOfCards.shuffle();

		for (Card card : deckOfCards.getCards()) {
			printCard(card);
		}

		printDeckSize(deckOfCards);

		System.out.println("drawing cards...");
		ArrayList<Card> drawnCards = deckOfCards.drawMany(0, 5);

		for (Card card : drawnCards) {
			printCard(card);
		}

		printDeckSize(deckOfCards);
	}

	private static void printDeckSize(final DeckOfCards deckOfCards) {
		System.out.println("number of cards = "
				+ deckOfCards.getNumberOfCards());
	}

	private static void printCard(final Card card) {
		System.out.println("Suit = " + card.getSuit() + " Rank = "
				+ card.getRank());
	}

}
