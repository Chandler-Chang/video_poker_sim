/* CSE205: 11333/T, Th 4:30-5:45pm
 * Assignment: 6, Final
 * Author: Chandler Chang (1216655869)
 * Description: Table class for Assignment 6
 */

import java.util.Scanner;
import java.util.ArrayList;

public class Table {
	private Deck deck = new Deck();
	private Hand hand = new Hand();
	private int balance = 100;
	private int bid = 1;
	private ArrayList <Integer> exchanges = new ArrayList<Integer>();
	
	//start the game, end when player bids 0 or has no money
	public void start () {
		while (bid != 0 && balance > 0) {
		play();
		}
		end();
	}
	
	private void play() {
		displayBalance();
		getBid();
		
		//stop if player bids 0
		if (bid == 0) { 
			return;
		}
		
		//clear the hand and shuffle deck
		hand.clear(); 
		deck.shuffle(); 
		
		//player draws 5 cards
		for (int i = 0; i < 5; i++) { 
			hand.add(deck.draw());
		}
		
		//display hand
		System.out.println("");
		System.out.println("Your Hand:"); 
		hand.display();
		
		System.out.println("");
		getExchange();
		
		exchange();
		
		//display hand after exchange
		System.out.println("");
		System.out.println("Your New Hand:"); 
		hand.display();
		
		hand.quickSort();
		
		//display hand sorted by value
		System.out.println("");
		System.out.println("Sorted Hand:"); 
		hand.display();
		
		//payout winnings (if any)
		System.out.println("");
		payout(); 
	}
	
	private void getBid() {
		boolean valid = false;
		Scanner scan = new Scanner (System.in);
		
		//input bid and check for a valid bid
		while(!valid) {
			System.out.println("Your bid (1-5), or 0 to cash out: ");
			bid = scan.nextInt();
			if (bid > balance) {
				System.out.println("You don't have that much money.");
			}
			else if (bid >= 0 && bid <= 5) {
				balance -= bid;
				valid = true;
			}
			else {
				System.out.println("Invalid bid, must be an integer (0-5).");
			}	
		}
	}
	
	private void displayBalance() {
		System.out.println("Your balance: $" + balance);
	}
	
	private void getExchange() {
		exchanges.clear();
		
		System.out.println("Exchange?");
		Scanner scan1 = new Scanner(System.in);
		
		//take in a string of numbers
		String exchange_String = scan1.nextLine(); 
		
		//make an array of indexes
		for (int i = 0; i < exchange_String.length(); i++) { 
			exchanges.add(Character.getNumericValue((exchange_String.charAt(i)))-1); 
		}
	}
	
	private void exchange() {
		//replace each card whose index is in the array
		for (int i = 0; i < exchanges.size(); i++) { 
			Card replacement = deck.draw();
			hand.replace(exchanges.get(i), replacement);
		}
	}
	
	private void payout() {
		//if you lost
		if (hand.payout() == 0) {
			System.out.println(hand.getRank() + "! You lose $" + bid);
		}
		
		//if you won
		else {
			int winnings = bid * hand.payout();
			balance += winnings;
			System.out.println(hand.getRank() + "! You win $" + winnings);
		}
	}
	
	//last message, shows your profit or commiserates your bankruptcy
	private void end() {
		if (balance == 0) {
			System.out.println("You lost all of your money! Better luck next time.");
		}
		else {
			System.out.println("You cashed out with $" + balance + " and made a profit of $"
					+ (balance - 100));
		}
	}
}