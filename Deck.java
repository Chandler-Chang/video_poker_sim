/* CSE205: 11333/T, Th 4:30-5:45pm
 * Assignment: 6, Final
 * Author: Chandler Chang (1216655869)
 * Description: Deck class for Assignment 6
 */

import java.util.NoSuchElementException;
import java.util.ArrayList;

public class Deck implements Stack{
	private ArrayList<Card> cards = new ArrayList<Card>();
	private Node head = null;
	private int size = 0;
	
	public Card draw() {
		return pop();
	}
	
	private void generateDeck () {
		cards.clear();
		for (int i = 1; i <= 4; i++) { //each suit
			for (int j = 2; j <= 14; j++) { //each value (2-14)
				Card newCard = new Card(i, j);
				cards.add(newCard);
			}
		}
	}
	
	public void shuffle () {
		//generate an organized deck
		generateDeck();
		
		for (int i = 52; i > 0; i--) {
			//pick a random card
			int index = (int) (Math.random()*i); 
			Card card = cards.get(index);
			
			//remove it from the deck
			cards.remove(index); 
			
			//put in on the stack queue
			push(card); 
		}
	}
	
	private class Node {
		private Object data = null;
		private Node next = null;
		
		public Node(Object data) {
			this.data = data;
		}
	}
	
	public void push (Object card) {
		Node newNode = new Node(card);
		if (size == 0) {
			head = newNode;
		}
		else {
			newNode.next = head;
			head = newNode;
		}
		size++;
	}
	
	public Card pop () {
		if (size == 0) {
			throw new NoSuchElementException("The Deck is empty.");
		}
		
		Card card = (Card) head.data;
		
		if (size == 1) {
			head = null;
		}
		else {
		head = head.next;
		}
		
		size--;
		
		return card;
	}
}