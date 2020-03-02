package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import card.Card;
import card.deck.StandardDeckOfCards;
import card.hand.Hand;

import participant.Participant;
import participant.dealer.Dealer;
import participant.player.Player;

public class BlackjackGame {
	private final Dealer dealer;
	private final List<Player> players = new ArrayList<Player>();
	private Scanner scanner;

	public BlackjackGame() {
		scanner = new Scanner(System.in);
		this.dealer = new Dealer(new StandardDeckOfCards());
		this.dealer.shuffle();
	}

	public void addPlayer(final Player player) {
		player.setHand(new Hand());
		this.players.add(player);
	}

	public void start() {
		addPlayers();
		initGameLoop();
	}

	private void addPlayers() {
		//TODO: allow addition of players
		addPlayer(new Player("Paddy"));
	}

	private void dealCards() {
		this.dealer.setHand(new Hand(this.dealer.dealCards(2)));

		for (Player player : this.players) {
			player.setHand(new Hand(this.dealer.dealCards(2)));
		}
	}

	private void printParticipantValues() {
		System.out.println("Dealer has " + dealer.getHandValue());

		for (Player player : this.players) {
			System.out.println(player.getName() + " has "
					+ player.getHandValue());
		}
	}

	private void initGameLoop() {
		boolean gameInProgress = true;
		
		do {
			dealCards();
			printParticipantValues();
			
			for (Player player : this.players) {
				playerTurn(player);
			}

			dealerTurn();

			for (Player player : players) {
				determineWinner(player);
			}
			
			resetCards();
		} while (gameInProgress);
		
		scanner.close();
	}

	private void playerTurn(final Player player) {
		// TODO need to create a standard input output class
		System.out.println("\n" + player.getName() + ":");
		boolean playerTurn = true;

		do {
			System.out.println("current total = " + player.getHandValue());

			if (player.getHandValue() == 21) {
				playerTurn = false;
			}

			System.out.println("hit (1) or stand (2)?");

			if (scanner.hasNext("1") || scanner.hasNext("2")) {
				int action = scanner.nextInt();
				switch (action) {
				case 1:
					dealCard(player);
					break;
				case 2:
					playerTurn = false;
					break;
				}

				// TODO: break out hand check into function
				// TODO: check if card is ace in which case, make it a 1 if it
				// prevents a bust.
				if (player.getHandValue() > 21) {
					playerTurn = false;
				}
				
			} else {
				System.out.println("invalid operation");
				scanner.next();
			}
		} while (playerTurn);

		System.out.println(player.getName() + "'s current total is "
				+ player.getHandValue());

		if (player.getHandValue() > 21) {
			System.out.println("BUST!");
		} else if (player.getHandValue() == 21) {
			System.out.println("BLACKJACK!");
		}
	}

	private void dealerTurn() {
		do {
			if (dealer.getHandValue() < 17) {
				System.out.println("Dealer draws...");
				// TODO: dealer should be able to deal 1
				dealer.getHand().addCard(dealer.dealCard());
			}
		} while (dealer.getHandValue() < 17);

		if (dealer.getHandValue() > 21) {
			System.out.println("BUST!");
		} else if (dealer.getHandValue() == 21) {
			System.out.println("BLACKJACK!");
		}

		System.out
				.println("dealer's current total is " + dealer.getHandValue());
	}

	private void determineWinner(final Player player) {
		if (player.getHandValue() > dealer.getHandValue()
				|| dealer.getHandValue() > 21) {
			System.out.println(player.getName() + " wins!");
		} else if (player.getHandValue() == dealer.getHandValue()) {
			System.out.println(player.getName() + " ties!");
		} else if (player.getHandValue() < dealer.getHandValue()
				|| player.getHandValue() > 21) {
			System.out.println(player.getName() + " loses!");
		}
	}
	
	private void resetCards() {
		this.dealer.getHand().discardAll();
		
		for(Player player: players) {
			player.getHand().discardAll();
		}
		
		this.dealer.setDeckOfCards(new StandardDeckOfCards());
		this.dealer.shuffle();
	}

	private void dealCard(Participant participant) {
		Card newCard = this.dealer.dealCard();
		
		System.out.println("dealer deals " + participant.getName()
				+ " a new card");
		
		System.out.println("new card is " + newCard.getRank()
				+ " of " + newCard.getSuit() + "s");
		
		participant.getHand().addCard(newCard);
	}
}
