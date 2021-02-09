import java.util.Scanner;

public class Start {
	public static void main (String args[]) {
		
		Scanner scan = new Scanner (System.in);
		int input = 0;
		Table table1 = new Table();
		
		System.out.println("Welcome to my Video Poker Simulator!");
		while (input != 1) {
			displayMenu();
			input = scan.nextInt();
			
			switch (input) 
			{
				case 1:
					table1.start();
					break;
					
				case 2: 
					displayHow();
					break;
					
				case 3:
					displayPayouts();
					break;
					
				case 4: 
					displayRanks();
					break;
					
				default:
					System.out.println("Invalid choice.");
			}
		}
		
		scan.close();
	}
	
	private static void displayMenu() {
		System.out.println("----------------------------");
		System.out.println("1) Start Playing");
		System.out.println("2) How to Play");
		System.out.println("3) Payout Ratios");
		System.out.println("4) Poker Hand Ranking");
		System.out.println("----------------------------");
	}
	
	private static void displayHow() {
		System.out.println("You start with $100. Before each hand you bid any amount from"
				+ " $1 to $5, then you are dealt 5 cards. \nYou are then given one opportunity"
				+ " to improve your hand by replacing any number of cards.* After \nyou receive"
				+ " new cards, or if you chose not to replace any, you will paid according to"
				+ " the scale \nunder \"Payout Ratios\".");
		System.out.println("");
		System.out.println("*After you receive your initial hand, you will be prompted with "
				+ "\"Exchange?\". This asks you to type in \nthe ordinal number of every card you want "
				+ "to keep in your hand. The cards are organized by the numbers \nin parentheses on "
				+ "the left. For example, if your hand is: "
				+ "\n(1) Ace of Spades"
				+ "\n(2) Nine of Hearts"
				+ "\n(3) King of Diamonds"
				+ "\n(4) Jack of Diamonds"
				+ "\n(5) Ace of Clubs");
		System.out.println("");
		System.out.println("To hold the pair of aces and exchange the rest, you would input \"234\", no commas. To hold "
				+ "your entire \nhand, you would input nothing.");
		System.out.println("");
	}
	
	private static void displayPayouts() {
		System.out.println("Royal Flush: 1000 to 1");
		System.out.println("Straight Flush: 100 to 1");
		System.out.println("Four of a Kind: 25 to 1");
		System.out.println("Full House: 8 to 1");
		System.out.println("Flush: 6 to 1");
		System.out.println("Straight: 4 to 1");
		System.out.println("Three of a Kind: 3 to 1");
		System.out.println("Two Pair: 2 to 1");
		System.out.println("Jacks or Better: 1 to 1");
		System.out.println("");
	}
	
	private static void displayRanks() {
		System.out.println("Royal Flush: A specific type of straight flush: 10, Jack, Queen, King, "
				+ "Ace, all of the same suit. \n	     This is the best hand in poker.");
		System.out.println("Straight Flush: A sequence of five cards ascending in immediate value all of the same suit.");
		System.out.println("Four of a Kind: A hand consisting of four cards of the same value.");
		System.out.println("Full House: A hand consisting of Three of a Kind + A Pair.");
		System.out.println("Flush: Five cards all of the same suit.");
		System.out.println("Straight: A sequence of five cards ascending in immediate value.");
		System.out.println("Three of a Kind: A hand consisting of three cards of the same value.");
		System.out.println("Two Pair: A hand consisting of two Pairs.");
		System.out.println("Jacks or Better: A hand consisting of a Pair of equal or greater value than Jacks.");
		System.out.println("");
	}
}
