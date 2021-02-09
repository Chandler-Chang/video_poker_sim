/* CSE205: 11333/T, Th 4:30-5:45pm
 * Assignment: 6, Final
 * Author: Chandler Chang (1216655869)
 * Description: Card class for Assignment 6
 */

public class Card {
	private int suit; // 1 (spade), 2 (heart), 3 (diamond), 4 (club)
	private int value; // 2 = 2, ... 10 = 10, 11 = Jack, 12 = Queen, 13 = King, 14 = Ace
	
	public Card (int suit, int value) {
		this.suit = suit;
		this.value = value;
	}
	
	public int getSuit () {
		return suit;
	}
	
	public int getValue () {
		return value;
	}
	
	public void setSuit (int suit) {
		this.suit = suit;
	}
	
	public void setValue (int value) {
		this.value = value;
	}
}