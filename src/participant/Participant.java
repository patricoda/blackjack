package participant;

import card.hand.Hand;

public class Participant {
	private String name = "Anon";
	private Hand hand;
	
	public Participant() {
		
	}
	
	public String getName() {
		return name;
	}
	
	protected void setName(final String name) {
		this.name = name;
	}
	
	public Hand getHand() {
		return hand;
	}

	public void setHand(final Hand hand) {
		this.hand = hand;
	}
	
	public int getHandValue() {
		return hand.getHandValue();
	}
	
	
}
