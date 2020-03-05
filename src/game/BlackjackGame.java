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

	public void start() {
		addPlayers();
		initGameLoop();
	}

	private void addPlayers() {
		boolean addingPlayers = true;

		do {
			System.out.println("Add a player (1), or continue (2)");
			if (scanner.hasNext("1") || scanner.hasNext("2")) {
				int action = scanner.nextInt();
				switch (action) {
				case 1:
					System.out.println("Enter player name:");
					scanner.nextLine();

					if (scanner.hasNextLine()) {
						Player newPlayer = new Player(scanner.nextLine());
						this.players.add(newPlayer);
					}
					break;
				case 2:
					if (this.players.isEmpty()) {
						System.out.println("Please enter at least one player");
						scanner.nextLine();
					} else {
						addingPlayers = false;
					}
					break;
				}
			} else {
				System.out.println("Invalid operation");
				scanner.nextLine();
			}
		} while (addingPlayers);
	}

	private void dealCards() {
		this.dealer.setHand(new Hand(this.dealer.dealCards(2)));

		for (Player player : this.players) {
			player.setHand(new Hand(this.dealer.dealCards(2)));
		}
	}

	private void printParticipantValues() {
		System.out.println("\nDealer has " + dealer.getHandValue());

		for (Player player : this.players) {
			System.out.println(player.getName() + " has "
					+ player.getHandValue());
		}
	}

	private void initGameLoop() {
		boolean gameInProgress = true;

		do {
			System.out.println("new game (1) or exit (2)?");

			if (scanner.hasNext("1") || scanner.hasNext("2")) {
				int action = scanner.nextInt();

				switch (action) {
				case 1:
					initRound();
					break;
				case 2:
					gameInProgress = false;
					break;
				}
			} else {
				System.out.println("Invalid operation");
				scanner.next();
			}

		} while (gameInProgress);

		System.out.println("Bye :)");
		scanner.close();
	}

	private void initRound() {
		dealCards();
		printParticipantValues();

		for (Player player : this.players) {
			playerTurn(player);
		}

		//change to ensure if all player's are bust, dealer doesn't have turn, auto-wins?
		dealerTurn();

		for (Player player : players) {
			determineWinner(player);
		}

		resetCards();
	}

	private void playerTurn(final Player player) {
		// TODO need to create a standard input output class
		System.out.println("\n" + player.getName() + "'s turn...");
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
					System.out.println(player.getName()
							+ "'s current total is " + player.getHandValue());

					// TODO: check if card is ace in which case, make it a 1 if it
					// prevents a bust.
					if (isBust(player)) {
						System.out.println("BUST!");
						playerTurn = false;
					} else if (hasBlackjack(player)) {
						System.out.println("BLACKJACK!");
						playerTurn = false;
					}
					
					break;
				case 2:
					playerTurn = false;
					break;
				}
			} else {
				System.out.println("Invalid operation");
				scanner.next();
			}
		} while (playerTurn);
	}

	private void dealerTurn() {
		System.out.println("\nDealer's turn...");
		boolean dealerTurn = true;

		do {
			if (dealer.getHandValue() < 17) {
				System.out.println("Dealer draws...");
				dealer.getHand().addCard(dealer.dealCard());

				System.out.println("dealer's current total is "
						+ dealer.getHandValue());
			} else {
				if (isBust(dealer)) {
					System.out.println("BUST!");
				} else if (hasBlackjack(dealer)) {
					System.out.println("BLACKJACK!");
				}

				dealerTurn = false;
			}
		} while (dealerTurn);
	}

	private boolean hasBlackjack(final Participant participant) {
		if (participant.getHandValue() == 21) {
			return true;
		}

		return false;
	}

	private boolean isBust(final Participant participant) {
		if (participant.getHandValue() > 21) {
			return true;
		}

		return false;
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

		for (Player player : players) {
			player.getHand().discardAll();
		}

		this.dealer.setDeckOfCards(new StandardDeckOfCards());
		this.dealer.shuffle();
	}

	private void dealCard(Participant participant) {
		Card newCard = this.dealer.dealCard();

		System.out.println("dealer deals " + participant.getName()
				+ " a new card");

		System.out.println("new card is " + newCard.getRank() + " of "
				+ newCard.getSuit() + "s");

		participant.getHand().addCard(newCard);
	}
}
