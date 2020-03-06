package card.hand;

import java.util.List;

import config.GameValues;

import participant.Participant;

import card.Card;
import card.enums.Rank;

public class Hand {
	private List<Card> cards;
	private int handValue = 0;

	public Hand() {
	}

	public Hand(final List<Card> cards) {
		this.cards = cards;
		handValue = calculateHandValue();
	}

	public void setCards(final List<Card> cards) {
		this.cards = cards;
		handValue = calculateHandValue();
	}

	public List<Card> getCards() {
		return cards;
	}

	public void addCard(final Card card) {
		cards.add(card);
		handValue = calculateHandValue();
	}

	public void discard(final int index) throws IndexOutOfBoundsException {
		cards.remove(index);
	}

	public void discardAll() {
		cards.clear();
	}
	
	public int getHandValue() {
		return handValue;
	}
	
	private int calculateHandValue() {
		int total = 0;

		for (Card card : cards) {
			total = total + card.getRank().getValue();
		}
		
		//if the hand goes over 21, check for aces.
		if(total > GameValues.BLACKJACK_VALUE) {
			for(Card card : cards) {
				if(card.getRank().equals(Rank.ACE)) {
					//if a card in the hand is an ace, treat ace as a 1.
					total = (total - Rank.ACE.getValue()) + 1;
					
					//if the value is then under 21, break out of the loop, otherwise continue.
					if((total - Rank.ACE.getValue()) <= GameValues.BLACKJACK_VALUE) {
						break;
					} else {
						continue;
					}
				}
			}
		}

		return total;
	}
	
	public boolean isBlackjack() {
		if (handValue == GameValues.BLACKJACK_VALUE) {
			return true;
		}

		return false;
	}

	public boolean isBust() {
		if (handValue > GameValues.BLACKJACK_VALUE) {
			return true;
		}

		return false;
	}
}
