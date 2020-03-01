package participant.dealer;

import java.util.ArrayList;

import participant.Participant;

import card.Card;
import card.deck.DeckOfCards;

public class Dealer  extends Participant {
	private final DeckOfCards deckOfCards;

	public Dealer(final DeckOfCards deckOfCards) {
		this.setName("Dealer");
		this.deckOfCards = deckOfCards;
	}

	public ArrayList<Card> deal(final int numberOfCards) {
		return this.deckOfCards.drawMany(0, numberOfCards);
	}
	
	public void shuffle() {
		this.deckOfCards.shuffle();
	}
}
