import card.Card;
import card.DeckOfCards;
import card.StandardDeckOfCards;

public class main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DeckOfCards deckOfCards = new StandardDeckOfCards();

		for (Card card : deckOfCards.getCards()) {
			System.out.println("Suit = " + card.getSuit() + " Rank = "
					+ card.getRank());
		}
	}

}
