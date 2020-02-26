package card.hand;

import java.util.List;

import card.Card;

public abstract class Hand {
	private int maxCards;
	private List<Card> cards;

	protected void setMaxCards(final int maxCards) {
		this.maxCards = maxCards;
	}

	public void setCards(final List<Card> cards) {
		this.cards = cards;
	}

	public void addCard(final Card card) {
		if (cards.size() < maxCards) {
			cards.add(card);
		}
	}

	public void discard(final int index) throws IndexOutOfBoundsException {
		cards.remove(index);
	}

	public void discardAll() {
		cards.clear();
	}

}
