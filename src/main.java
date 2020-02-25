import card.Card;
import card.DeckOfCards;
import card.StandardDeckOfCards;

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

		System.out.println("drawing card...");
		Card card = deckOfCards.draw(0);

		printCard(card);

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
