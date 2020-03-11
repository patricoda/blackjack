package card.hand;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import config.GameValues;

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
		int total = cards.stream().mapToInt(card -> card.getRank().getValue()).sum();

		// if the hand goes over 21, check for aces.
		if (total > GameValues.BLACKJACK_VALUE) {
			final long numberOfAces = cards.stream().filter(card -> card.getRank().equals(Rank.ACE)).count();
			
			for (int i = 0; i < numberOfAces; i++) {
				// if a card in the hand is an ace, treat ace as a 1.
				total = (total - (Rank.ACE.getValue() - 1));
				
				//if total is now less than the max value, break out of loop
				if (total <= GameValues.BLACKJACK_VALUE)
					break;
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
