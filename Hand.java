/* CSE205: 11333/T, Th 4:30-5:45pm
 * Assignment: 6, Final
 * Author: Chandler Chang (1216655869)
 * Description: Hand class for Assignment 6
 */

import java.util.ArrayList;

public class Hand {
	private ArrayList<Card> hand = new ArrayList<Card>();
	
	public void add(Card card) {
		hand.add(card);
	}
	
	public void clear() {
		hand.clear();
	}
	
	//displays the hand
	public void display () {
		for (int i = 0; i < 5; i++) {
		System.out.print("(" + (i+1) + ") ");
		printValue(hand.get(i));
		printSuit(hand.get(i));
		}
	}
	
	public void replace (int index, Card replacement) {
		hand.set(index, replacement);
	}
	
	//returns the payout ratio
	public int payout () {
		int payout_ratio = 0; //0 = no hand
		
		if (jacks_or_better()) {
			payout_ratio = 1;
		}
		
		if (two_pair()) {
			payout_ratio = 2;
		}
		
		if (three_of_a_kind()) {
			payout_ratio = 3;
		}
		
		if (straight()) {
			payout_ratio = 4;
		}
		
		if (flush()) {
			payout_ratio = 6;
		}
		
		if (full_house()) {
			payout_ratio = 8;
		}
		
		if (four_of_a_kind()) {
			payout_ratio = 25;
		}
		
		if (straight() && flush()) { //straight flush
			if (hand.get(0).getValue() == 10) { //royal flush if the first card is a 10
				payout_ratio = 1000;
			}
			payout_ratio = 100;
		}
		
		return payout_ratio;
	}
	
	//returns the name of the hand achieved
	public String getRank () {
		if (payout() == 0) {
			return "No Hand";
		}
		if (payout() == 1) {
			return "Jacks or Better";
		}
		if (payout() == 2) {
			return "Two Pair";
		}
		if (payout() == 3) {
			return "Three of a Kind";
		}
		if (payout() == 4) {
			return "Straight";
		}
		if (payout() == 6) {
			return "Flush";
		}
		if (payout() == 8) {
			return "Full House";
		}
		if (payout() == 25) {
			return "Four of a Kind";
		}
		if (payout() == 100) {
			return "Straight Flush";
		}
		if (payout() == 1000) {
			return "Royal Flush";
		}
		return null;
	}
	
	private boolean jacks_or_better () {
		for (int i = 0; i < 4; i++) {
			if (hand.get(i).getValue() == hand.get(i+1).getValue()) { //check for a pair
				if (hand.get(i).getValue() >= 11) { //check if it is jacks or better
				return true;
				}
			}
		}
		return false;
	}
	
	private boolean two_pair () {
		int first_pair = 0;
		boolean has_pair = false;
		for (int i = 0; i < 4; i++) { 
			//check for pair, store the value of pair in first_pair
			if (hand.get(i).getValue() == hand.get(i+1).getValue()) {
				first_pair = hand.get(i).getValue();
				has_pair = true;
				break;
			}
		}
		
		//if there is a pair, check for second pair
		if (has_pair) {
			for (int i = 0; i < 4; i++) { 
				if (hand.get(i).getValue() != first_pair) { 
					if (hand.get(i).getValue() == hand.get(i+1).getValue()) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	private boolean three_of_a_kind () {
		for (int i = 0; i < 3; i++) { 
			//check for three consecutive equal values
			if ((hand.get(i).getValue() == hand.get(i+1).getValue()) &&
				(hand.get(i+1).getValue() == hand.get(i+2).getValue())) {
				return true;
			}
		}
		return false;
	}
	
	private boolean straight () {
		for (int i = 0; i < 4; i++) {
			//check for ace-to-five straight
			if (i == 3 && hand.get(i).getValue() == 5) {  //if the 4th card is a 5
				if (hand.get(i+1).getValue() == 14) { //and the last card is an Ace
					return true;
				}
			}
			
			//check for five-card sequence of values
			if (hand.get(i).getValue()+1 != hand.get(i+1).getValue()) { 
				return false;
			}
		}
		return true;
	}
	
	private boolean flush () {
		
		//check for the same suit
		for (int i = 0; i < 4; i++) {
			if (hand.get(i).getSuit() != hand.get(i+1).getSuit()) {
				return false;
			}
		}
		return true;
	}
	
	private boolean full_house () {
		int trips = 0;
		boolean has_trips = false;
		
		//check for three of a kind
		for (int i = 0; i < 3; i++) {
			if ((hand.get(i).getValue() == hand.get(i+1).getValue()) &&
				(hand.get(i+1).getValue() == hand.get(i+2).getValue())) {
				trips = hand.get(i).getValue();
				has_trips = true;
			}
		}
		
		//if there is three of a kind, check for another pair
		if (has_trips) {
			for (int i = 0; i < 4; i++) { 
				if ((hand.get(i).getValue() != trips) && //
					 hand.get(i).getValue() == hand.get(i+1).getValue()) {
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean four_of_a_kind () {
		for (int i = 0; i < 2; i++) { 
			//check for four consecutive equal values
			if (((hand.get(i).getValue() == hand.get(i+1).getValue()) &&
			 	 (hand.get(i+1).getValue() == hand.get(i+2).getValue())) &&
				 (hand.get(i+2).getValue() == hand.get(i+3).getValue())) {
				 return true;
			}
		}
		return false;
	}
	
	public void quickSort () {
		quickSortHelper (hand, 0, hand.size()-1);
	}
	
	private static void quickSortHelper (ArrayList<Card> hand, int lowIndex, int highIndex) {
		if (lowIndex < highIndex) {
			int pivot_point = partition(hand, lowIndex, highIndex);
			
			quickSortHelper(hand, lowIndex, pivot_point - 1);
			quickSortHelper(hand, pivot_point + 1, highIndex);
			}
	}
	
	private static int partition (ArrayList<Card> hand, int lowIndex, int highIndex) {
		int pivot_index = highIndex; //pivot set at last element
		int store_index = lowIndex;
		int compare_index = lowIndex;
		
		for (int i = lowIndex; i < highIndex; i++) {
			if (hand.get(compare_index).getValue() < hand.get(pivot_index).getValue()) {
				swap(hand, store_index, compare_index);
				store_index++;
			}
			compare_index++;
		}
		
		swap(hand, store_index, pivot_index);
		
		return store_index;
	}
	
	private static void swap (ArrayList<Card> hand, int index1, int index2) {
		Card temp = hand.get(index1);
		hand.set(index1, hand.get(index2));
		hand.set(index2, temp);
	}
	
	private void printValue (Card card) {
		//if the card is a face card or Ace...
		if (card.getValue() > 10) { 
			if (card.getValue() == 11) {
				System.out.print("Jack of ");
			}
			
			else if (card.getValue() == 12) {
				System.out.print("Queen of ");
			}
			
			else if (card.getValue() == 13) {
				System.out.print("King of ");
			}
			
			else if (card.getValue() == 14) {
				System.out.print("Ace of ");
			}
		}
		
		else {
			System.out.print(card.getValue() + " of ");
		}
	}
	
	private void printSuit (Card card) {
		if (card.getSuit() == 1) {
			System.out.println("Spades");
		}
		
		else if (card.getSuit() == 2) {
			System.out.println("Hearts");
		}
		
		else if (card.getSuit() == 3) {
			System.out.println("Diamonds");
		}
		
		else if (card.getSuit() == 4) {
			System.out.println("Clubs");
		}
	}
}