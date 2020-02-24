package card;

import card.enums.Rank;
import card.enums.Suit;

public class StandardDeckOfCards extends DeckOfCards{
	
	public StandardDeckOfCards() {
		super(Suit.values(), Rank.values());
	}
}
