package card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import card.enums.Rank;
import card.enums.Suit;

public class DeckOfCards {
	private Suit[] permittedSuits;
	private Rank[] permittedRanks;
	private List<Card> cards;

	public DeckOfCards(Suit[] permittedSuits, Rank[] permittedRanks) {
		this.permittedSuits = permittedSuits;
		this.permittedRanks = permittedRanks;
		this.cards = createCards();
	}

	public final Suit[] getPermittedSuits() {
		return permittedSuits;
	}

	public final Rank[] getPermittedRanks() {
		return permittedRanks;
	}

	public final List<Card> getCards() {
		return cards;
	}

	public final int getNumberOfCards() {
		return cards.size();
	}

	private final List<Card> createCards() {
		final List<Card> cards = new ArrayList<Card>();

		for (Suit suit : permittedSuits) {
			for (Rank rank : permittedRanks) {
				Card card = new Card(suit, rank);
				cards.add(card);
			}
		}

		return cards;
	}

	public final void shuffle() {
		Collections.shuffle(this.cards);
	}

	// return card drawn from deck, remove it from the deck. 0-based index
	public final Card draw(final int cardNumber) throws IndexOutOfBoundsException {
		final Card drawnCard = this.cards.get(cardNumber);
		this.cards.remove(cardNumber);

		return drawnCard;
	}
}
