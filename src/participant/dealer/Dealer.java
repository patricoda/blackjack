package participant.dealer;

import java.util.ArrayList;

import card.Card;
import card.deck.DeckOfCards;
import card.hand.Hand;

public class Dealer {
	private final DeckOfCards deckOfCards;
	private Hand hand;

	public Dealer(final DeckOfCards deckOfCards) {
		this.deckOfCards = deckOfCards;
	}

	public Hand getHand() {
		return hand;
	}

	public void setHand(final Hand hand) {
		this.hand = hand;
	}

	public ArrayList<Card> deal(final int numberOfCards) {
		return this.deckOfCards.drawMany(0, numberOfCards);
	}
}
