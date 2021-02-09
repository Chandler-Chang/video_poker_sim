#ifndef _TABLE_H_
#define _TABLE_H_

#include"card.h"
#include"hand.h"
#include"deck.h"
#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;

class Table {
public:
	Table() {};

	void start() {
		while (bid != 0 && balance > 0) {
			play();
		}
		end();
	}

private:
	Deck deck;
	Hand hand;
	int bid = 1;
	int balance = 100;

	void play() {
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
		cout << endl;
		cout << "Your Hand:" << endl;
		hand.display();

		cout << endl;
		exchange();

		//display hand after exchange
		cout << endl;
		cout << "Your New Hand:" << endl;
		hand.display();

		//sort hand by value, necessary for the payout algorithms
		hand.quickSort();

		//payout winnings (if any)
		cout << endl;
		payout();
	}

	void getBid() {
		bool valid = false;

		//input bid and check for a valid bid
		while (!valid) {
			cout << "Your bid (1-5), or 0 to cash out: " << endl;
			cin >> bid;
			if (bid > balance) {
				cout << "You don't have that much money." << endl;
			}
			else if (bid >= 0 && bid <= 5) {
				balance -= bid;
				valid = true;
			}
			else {
				cout << "Invalid bid, must be an integer (0-5)." << endl;
			}
		}
	}

	void exchange() {
		char input;

		for (int i = 0; i < 5; i++) {

			cout << "Exchange card " << i + 1 << "? (y/n)" << endl;
			cin >> input;

			if (input == 'y') {
				Card replacement = deck.draw();
				hand.replace(i, replacement);
			}
		}
	}

	void payout() {
		//if you lost
		if (hand.payout() == 0) {
			cout << hand.getRank() << "! You lose $" << bid << endl;
		}

		//if you won
		else {
			int winnings = bid * hand.payout();
			balance += winnings;
			cout << hand.getRank() << "! You win $" << winnings << endl;
		}
	}

	void end() {
		if (balance == 0) {
			cout << "You lost all of your money! Better luck next time." << endl;
		}
		else {
			cout << "You cashed out with $" << balance << " and made a profit of $" << (balance - 100) << endl;
		}
	}

	void displayBalance() {
		cout << "Your balance: $" << balance << endl;
	}

};
#endif
