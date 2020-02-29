package participant;

import card.hand.Hand;

public class Participant {
	private Hand hand;
	
	public Participant() {
		
	}
	
	public Hand getHand() {
		return hand;
	}

	public void setHand(final Hand hand) {
		this.hand = hand;
	}
}
