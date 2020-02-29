package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import card.Card;
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
		initGameLoop();
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

	private void initGameLoop() {
		//TODO need to create a standard input output class
		Scanner scanner = new Scanner(System.in);
this.players.add(new Player("Karl"));
this.players.get(1).setHand(new Hand(this.dealer.deal(2)));
		for (Player player : this.players) {
			System.out.println("\n" + player.getName() + ":");
			boolean playerAction = true;
			
			do {
				System.out.println("current total = " + player.getHand().getHandValue());
				
				if(player.getHand().getHandValue() == 21) {
					playerAction = false;
				}
				
				System.out.println("hit (1) or stand (2)?");
				
				if (scanner.hasNext("1") || scanner.hasNext("2")) {
					int action = scanner.nextInt();
					switch (action) {
					case 1:
						Card newCard = this.dealer.deal(1).get(0);
						System.out.println("dealer deals " + player.getName() + " a new card");
						System.out.println("new card is " + newCard.getRank() + " of " + newCard.getSuit() + "s");
						player.getHand().addCard(newCard);
						break;
					case 2:
						System.out.println("stand");
						playerAction = false;
						break;
					}
					
					if(player.getHand().getHandValue() > 21) {
						playerAction = false;
					}
				} else {
					System.out.println("invalid operation");
					scanner.next();
				}
			} while (playerAction);
			
			System.out.println(player.getName() + "'s current total is " + player.getHand().getHandValue());
			
			if(player.getHand().getHandValue() > 21) {
				System.out.println("BUST!");
			} else if(player.getHand().getHandValue() == 21) {
				System.out.println("BLACKJACK!");
			}
		}

		scanner.close();
		// dealer will attempt to get best hand
		// round finish
	}
}
