package game;

import java.util.ArrayList;
import java.util.List;

import card.Card;
import card.deck.StandardDeckOfCards;
import card.hand.Hand;

import participant.dealer.Dealer;
import participant.player.Player;

public class BlackjackGame {
	private final Dealer dealer;
	private ArrayList<Player> players;

	public BlackjackGame() {
		this.dealer = new Dealer(new StandardDeckOfCards());
	}

	public BlackjackGame(ArrayList<Player> players) {
		this.dealer = new Dealer(new StandardDeckOfCards());
		this.players = players;
	}

	public void setPlayers(final ArrayList<Player> players) {
		this.players = players;
	}

	public void addPlayer(final Player player) {
		this.players.add(player);
	}

	public void start() {
		this.dealer.setHand(new Hand(this.dealer.deal(2)));

		for (Player player : this.players) {
			player.setHand(new Hand(this.dealer.deal(2)));
		}
		
		System.out.println("dealer has " + calculateHandValue(this.dealer.getHand().getCards()));
	}
	
	private int calculateHandValue(List<Card> list) {
		return 0;
	}
}
