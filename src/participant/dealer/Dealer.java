package participant.dealer;

import java.util.ArrayList;

import participant.Participant;

import card.Card;
import card.deck.DeckOfCards;

public class Dealer extends Participant {
	private DeckOfCards deckOfCards;

	public Dealer(final DeckOfCards deckOfCards) {
		this.setName("Dealer");
		this.deckOfCards = deckOfCards;
	}
	
	public void setDeckOfCards(final DeckOfCards deckOfCards) {
		this.deckOfCards = deckOfCards;
	}

	public Card dealCard() {
		return this.deckOfCards.draw(0);
	}

	public ArrayList<Card> dealCards(final int numberOfCards) {
		return this.deckOfCards.drawMany(0, numberOfCards);
	}

	public void shuffle() {
		this.deckOfCards.shuffle();
	}
}
