package card.hand;

import java.util.List;

import card.Card;

public class Hand {
	private List<Card> cards;
	
	public Hand() {}
	
	public Hand(final List<Card> cards) {
		this.cards = cards;
	}
	
	public void setCards(final List<Card> cards) {
		this.cards = cards;
	}
	
	public List<Card> getCards() {
		return this.cards;
	}

	public void addCard(final Card card) {
			cards.add(card);
	}

	public void discard(final int index) throws IndexOutOfBoundsException {
		cards.remove(index);
	}

	public void discardAll() {
		cards.clear();
	}

}
