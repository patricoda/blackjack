package card;

import java.util.ArrayList;
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
}
