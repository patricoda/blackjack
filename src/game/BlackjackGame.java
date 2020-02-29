package game;

import java.util.ArrayList;
import java.util.List;

import card.deck.StandardDeckOfCards;
import card.hand.Hand;

import participant.dealer.Dealer;
import participant.player.Player;

public class BlackjackGame {
	private final Dealer dealer;
	private final List<Player> players = new ArrayList<Player>();

	public BlackjackGame() {
		this.dealer = new Dealer(new StandardDeckOfCards());
		this.dealer.shuffle();
	}

	public void addPlayer(final Player player) {
		this.players.add(player);
	}

	public void start() {
		addPlayers();
		dealCards();

		printParticipantValues();
	}

	private void addPlayers() {
		addPlayer(new Player("Paddy"));
	}

	private void dealCards() {
		this.dealer.setHand(new Hand(this.dealer.deal(2)));

		for (Player player : this.players) {
			player.setHand(new Hand(this.dealer.deal(2)));
		}
	}

	private void printParticipantValues() {
		System.out.println("Dealer has " + dealer.getHand().getHandValue());

		for (Player player : this.players) {
			System.out.println(player.getName() + " has "
					+ player.getHand().getHandValue());
		}
	}
}
