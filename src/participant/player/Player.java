package participant.player;

import card.hand.Hand;

public class Player {
	private final String name;
	private Hand hand;

	public Player(final String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Hand getHand() {
		return hand;
	}

	public void setHand(final Hand hand) {
		this.hand = hand;
	}
}
