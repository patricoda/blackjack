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
		
		for (Player player : this.players) {
			System.out.println("\n" + player.getName() + ":");
			boolean playerTurn = true;
			
			do {
				System.out.println("current total = " + player.getHand().getHandValue());
				
				if(player.getHand().getHandValue() == 21) {
					playerTurn = false;
				}
				
				System.out.println("hit (1) or stand (2)?");
				
				if (scanner.hasNext("1") || scanner.hasNext("2")) {
					int action = scanner.nextInt();
					switch (action) {
					case 1:
						//TODO: break out into hit function
						Card newCard = this.dealer.deal(1).get(0);
						System.out.println("dealer deals " + player.getName() + " a new card");
						System.out.println("new card is " + newCard.getRank() + " of " + newCard.getSuit() + "s");
						player.getHand().addCard(newCard);
						break;
					case 2:
						//TODO: break out into stand function
						System.out.println("stand");
						playerTurn = false;
						break;
					}
					
					//TODO: break out hand check into function 
					//TODO: check if card is ace in which case, make it a 1 if it prevents a bust.
					if(player.getHand().getHandValue() > 21) {
						playerTurn = false;
					}
				} else {
					System.out.println("invalid operation");
					scanner.next();
				}
			} while (playerTurn);
			
			System.out.println(player.getName() + "'s current total is " + player.getHand().getHandValue());
			
			if(player.getHand().getHandValue() > 21) {
				System.out.println("BUST!");
			} else if(player.getHand().getHandValue() == 21) {
				System.out.println("BLACKJACK!");
			}
		}

		scanner.close();
		
		//TODO: break out dealer AI into function
		do {
		if(dealer.getHand().getHandValue() < 17) {
			System.out.println("Dealer draws...");
			//TODO: dealer should be able to deal 1 
			dealer.getHand().addCard(dealer.deal(1).get(0));
		}
		} while(dealer.getHand().getHandValue() < 17);
		
		if(dealer.getHand().getHandValue() > 21) {
			System.out.println("BUST!");
		} else if(dealer.getHand().getHandValue() == 21) {
			System.out.println("BLACKJACK!");
		}
		
		System.out.println("dealer's current total is " + dealer.getHand().getHandValue());
		
		for(Player player : players) {
			if(player.getHand().getHandValue() > dealer.getHand().getHandValue() || dealer.getHand().getHandValue() > 21) {
				System.out.println(player.getName() + " wins!");
			} else if(player.getHand().getHandValue() == dealer.getHand().getHandValue()) {
				System.out.println(player.getName() + " ties!");
			} else if(player.getHand().getHandValue() < dealer.getHand().getHandValue() || player.getHand().getHandValue() > 21){
				System.out.println(player.getName() + " loses!");
			}
		}
		// round finish
		//TODO: loop
	}
}
