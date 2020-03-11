package game;

import java.util.ArrayList;
import java.util.List;

import communication.ConsoleCommunication;

import card.Card;
import card.deck.StandardDeckOfCards;
import card.hand.Hand;

import participant.Participant;
import participant.dealer.Dealer;
import participant.player.Player;

public class BlackjackGame {
	private final Dealer dealer;
	private final List<Player> players = new ArrayList<Player>();

	public BlackjackGame() {
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
			int action = ConsoleCommunication.inputInt("Add a player (1), or continue (2)");

			switch (action) {
			case 1:
				final String name = ConsoleCommunication.inputString("Enter player name:");
				Player newPlayer = new Player(name);
				this.players.add(newPlayer);
				break;
			case 2:
				if (this.players.isEmpty()) {
					ConsoleCommunication.output("Please enter at least one player");
				} else {
					addingPlayers = false;
				}
				break;
			default:
				ConsoleCommunication.output("Invalid operation");
				break;
			}
		} while (addingPlayers);
	}

	private void dealCards() {
		this.dealer.setHand(new Hand(this.dealer.dealCards(2)));
		this.players.stream().forEach(player -> player.setHand(new Hand(this.dealer.dealCards(2))));
	}

	private void printParticipantValues() {
		// TODO: players should only be aware of one card the dealer has until
		// dealer turn.
		ConsoleCommunication.output("\nDealer has " + dealer.getHandValue());
		this.players.stream()
				.forEach(player -> ConsoleCommunication.output(player.getName() + " has " + player.getHandValue()));
	}

	private void initGameLoop() {
		boolean gameInProgress = true;

		do {
			int action = ConsoleCommunication.inputInt("new game (1) or exit (2)?");

			switch (action) {
			case 1:
				initRound();
				break;
			case 2:
				gameInProgress = false;
				break;
			default:
				ConsoleCommunication.output("Invalid operation");
				break;
			}
		} while (gameInProgress);

		ConsoleCommunication.output("Bye :)");
	}

	private void initRound() {
		dealCards();
		printParticipantValues();

		this.players.stream().forEach(player -> playerTurn(player));

		if (!allPlayersBust()) {
			dealerTurn();
		}

		this.players.stream().forEach(player -> determineWinner(player));

		resetCards();
	}

	private boolean allPlayersBust() {
		return !this.players.stream().anyMatch(player -> !player.isBust());
	}

	private void playerTurn(final Player player) {
		ConsoleCommunication.output("\n" + player.getName() + "'s turn...");
		boolean playerTurn = true;

		ConsoleCommunication.output("current total = " + player.getHandValue());

		do {
			if (player.hasBlackjack()) {
				handleBlackjack();
				playerTurn = false;
			} else {
				int action = ConsoleCommunication.inputInt("hit (1) or stand (2)?");

				switch (action) {
				case 1:
					dealCard(player);
					ConsoleCommunication.output(player.getName() + "'s current total is " + player.getHandValue());

					if (player.isBust()) {
						handleBust();
						playerTurn = false;
					} else if (player.hasBlackjack()) {
						handleBlackjack();
						playerTurn = false;
					}

					break;
				case 2:
					playerTurn = false;
					break;
				default:
					ConsoleCommunication.output("Invalid operation");
					break;
				}
			}
		} while (playerTurn);
	}

	private void dealerTurn() {
		ConsoleCommunication.output("\nDealer's turn...");
		boolean dealerTurn = true;

		do {
			if (dealer.getHandValue() < 17) {
				ConsoleCommunication.output("Dealer draws...");
				dealer.getHand().addCard(dealer.dealCard());

				ConsoleCommunication.output("dealer's current total is " + dealer.getHandValue());
			} else {
				if (dealer.isBust()) {
					handleBust();
				} else if (dealer.hasBlackjack()) {
					handleBlackjack();
				}

				dealerTurn = false;
			}
		} while (dealerTurn);
	}

	private void determineWinner(final Player player) {
		if (player.isBust()) {
			ConsoleCommunication.output(player.getName() + " loses!");
		} else {
			if (dealer.isBust()) {
				ConsoleCommunication.output(player.getName() + " wins!");
			} else if (player.getHandValue() > dealer.getHandValue()) {
				ConsoleCommunication.output(player.getName() + " wins!");
			} else if (player.getHandValue() == dealer.getHandValue()) {
				ConsoleCommunication.output(player.getName() + " and the dealer tie!");
			} else {
				ConsoleCommunication.output(player.getName() + " loses!");
			}
		}
	}

	private void resetCards() {
		this.dealer.getHand().discardAll();
		
		players.stream().forEach(player -> player.getHand().discardAll());

		this.dealer.setDeckOfCards(new StandardDeckOfCards());
		this.dealer.shuffle();
	}

	private void dealCard(Participant participant) {
		Card newCard = this.dealer.dealCard();

		ConsoleCommunication.output("dealer deals " + participant.getName() + " a new card");

		ConsoleCommunication.output("new card is " + newCard.getRank().name().toLowerCase() + " of "
				+ newCard.getSuit().name().toLowerCase() + "s");

		participant.getHand().addCard(newCard);
	}

	private void handleBust() {
		ConsoleCommunication.output("BUST!");
	}

	private void handleBlackjack() {
		ConsoleCommunication.output("BLACKJACK!");
	}
}
