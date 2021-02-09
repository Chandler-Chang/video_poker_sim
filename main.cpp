#include"deck.h"
#include"card.h"
#include"hand.h"
#include"table.h"
#include<iostream>
using namespace std;

void displayMenu() {
	cout << "----------------------------" << endl;
	cout << "1) Start Playing" << endl;
	cout << "2) How to Play" << endl;
	cout << "3) Payout Ratios" << endl;
	cout << "4) Poker Hand Ranking" << endl;
	cout << "----------------------------" << endl;
}

void displayHow() {
	cout << "You start with $100. Before each hand you bid any amount from"
		<< " $1 to $5, then you are dealt 5 cards. \nYou are then given one opportunity"
	    << " to improve your hand by replacing any number of cards. After \nyou receive"
		<< " new cards, or if you chose not to replace any, you will paid according to"
		<< " the scale \nunder \"Payout Ratios\"." << endl;
}

void displayPayouts() {
	cout << "Royal Flush     : 1000 to 1" << endl;
	cout << "Straight Flush  : 100 to 1" << endl;
	cout << "Four of a Kind  : 25 to 1" << endl;
	cout << "Full House      : 8 to 1" << endl;
	cout << "Flush           : 6 to 1" << endl;
	cout << "Straight        : 4 to 1" << endl;
	cout << "Three of a Kind : 3 to 1" << endl;
	cout << "Two Pair        : 2 to 1" << endl;
	cout << "Jacks or Better : 1 to 1" << endl;
	cout << endl;
}

void displayRanks() {
	cout << "Royal Flush: A specific type of straight flush: 10, Jack, Queen, King, "
		 << "Ace, all of the same suit. \n	     This is the best hand in poker." << endl;;
	cout << "Straight Flush: A sequence of five cards ascending in immediate value all of the same suit." << endl;
	cout << "Four of a Kind: A hand consisting of four cards of the same value." << endl;
	cout << "Full House: A hand consisting of Three of a Kind + A Pair." << endl;
	cout << "Flush: Five cards all of the same suit." << endl;
	cout << "Straight: A sequence of five cards ascending in immediate value." << endl;
	cout << "Three of a Kind: A hand consisting of three cards of the same value." << endl;
	cout << "Two Pair: A hand consisting of two Pairs." << endl;
	cout << "Jacks or Better: A hand consisting of a Pair of equal or greater value than Jacks." << endl;
	cout << endl;
}

void main() {
	int input = 0;
	Table table1;

	cout << "Welcome to my Video Poker Simulator!" << endl;
	while (input != 1) {
		displayMenu();
		cin >> input;

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
			cout << "Invalid choice.";
		}
	}
}

