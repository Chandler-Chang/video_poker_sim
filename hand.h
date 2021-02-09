#ifndef _HAND_H_
#define _HAND_H_

#include"card.h"
#include<vector>
#include<algorithm>
#include<iostream>
using namespace std;

class Hand
{
public:
	void add(Card card) {
		hand.push_back(card);
	}

	void clear() {
		hand.clear();
	}

	void replace(int index, Card replacement) {
		hand.at(index) = replacement;
	}

	void quickSort() {
		quickSortHelper(hand, 0, hand.size() - 1);
	}

	void display() {
		for (int i = 0; i < 5; i++) {
			cout << (i + 1) << ": ";
			printValue(hand.at(i));
			printSuit(hand.at(i));
		}
	}

	void printValue(Card card) {
		//if the card is a face card or Ace...
		if (card.getValue() > 10) {
			if (card.getValue() == 11) {
				cout << "Jack of ";
			}

			else if (card.getValue() == 12) {
				cout << "Queen of ";
			}

			else if (card.getValue() == 13) {
				cout << "King of ";
			}

			else if (card.getValue() == 14) {
				cout << "Ace of ";
			}
		}

		else {
			cout << card.getValue() << " of ";
		}
	}

	void printSuit(Card card) {
		if (card.getSuit() == 1) {
			cout << "Spades" << endl;
		}

		else if (card.getSuit() == 2) {
			cout << "Hearts" << endl;
		}

		else if (card.getSuit() == 3) {
			cout << "Diamonds" << endl;
		}

		else if (card.getSuit() == 4) {
			cout << "Clubs" << endl;
		}
	}

	//returns the payout ratio
	int payout() {
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
			if (hand.at(0).getValue() == 10) { //royal flush if the first card is a 10
				payout_ratio = 1000;
			}
			payout_ratio = 100;
		}

		return payout_ratio;
	}

	//returns the name of the hand achieved
	string getRank() {
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
	}

private:
	vector<Card> hand;

	void quickSortHelper(vector<Card> &hand, int lowIndex, int highIndex) {
		if (lowIndex < highIndex) {
			int pivot_point = partition(hand, lowIndex, highIndex);

			quickSortHelper(hand, lowIndex, pivot_point - 1);
			quickSortHelper(hand, pivot_point + 1, highIndex);
		}
	}

	int partition(vector<Card> &hand, int lowIndex, int highIndex) {
		int pivot_index = highIndex; //pivot set at last element
		int store_index = lowIndex;
		int compare_index = lowIndex;

		for (int i = lowIndex; i < highIndex; i++) {
			if (hand.at(compare_index).getValue() < hand.at(pivot_index).getValue()) {
				swap(hand, store_index, compare_index);
				store_index++;
			}
			compare_index++;
		}

		swap(hand, store_index, pivot_index);

		return store_index;
	}

	void swap(vector<Card> &hand, int index1, int index2) {
		Card temp = hand.at(index1);
		hand.at(index1) = hand.at(index2);
		hand.at(index2) = temp;
	}

	bool jacks_or_better() {
		for (int i = 0; i < 4; i++) {
			if (hand.at(i).getValue() == hand.at(i + 1).getValue()) { //check for a pair
				if (hand.at(i).getValue() >= 11) { //check if it is jacks or better
					return true;
				}
			}
		}
		return false;
	}

	bool two_pair() {
		int first_pair = 0;
		bool has_pair = false;
		for (int i = 0; i < 4; i++) {
			//check for pair, store the value of pair in first_pair
			if (hand.at(i).getValue() == hand.at(i + 1).getValue()) {
				first_pair = hand.at(i).getValue();
				has_pair = true;
				break;
			}
		}

		//if there is a pair, check for second pair
		if (has_pair) {
			for (int i = 0; i < 4; i++) {
				if (hand.at(i).getValue() != first_pair) {
					if (hand.at(i).getValue() == hand.at(i + 1).getValue()) {
						return true;
					}
				}
			}
		}
		return false;
	}

	bool three_of_a_kind() {
		for (int i = 0; i < 3; i++) {
			//check for three consecutive equal values
			if ((hand.at(i).getValue() == hand.at(i + 1).getValue()) &&
				(hand.at(i + 1).getValue() == hand.at(i + 2).getValue())) {
				return true;
			}
		}
		return false;
	}

	bool straight() {
		for (int i = 0; i < 4; i++) {
			//check for ace-to-five straight
			if (i == 3 && hand.at(i).getValue() == 5) {  //if the 4th card is a 5
				if (hand.at(i + 1).getValue() == 14) { //and the last card is an Ace
					return true;
				}
			}

			//check for five-card sequence of values
			if (hand.at(i).getValue() + 1 != hand.at(i + 1).getValue()) {
				return false;
			}
		}
		return true;
	}

	bool flush() {

		//check for the same suit
		for (int i = 0; i < 4; i++) {
			if (hand.at(i).getSuit() != hand.at(i + 1).getSuit()) {
				return false;
			}
		}
		return true;
	}

	bool full_house() {
		int trips = 0;
		bool has_trips = false;

		//check for three of a kind
		for (int i = 0; i < 3; i++) {
			if ((hand.at(i).getValue() == hand.at(i + 1).getValue()) &&
				(hand.at(i + 1).getValue() == hand.at(i + 2).getValue())) {
				trips = hand.at(i).getValue();
				has_trips = true;
			}
		}

		//if there is three of a kind, check for another pair
		if (has_trips) {
			for (int i = 0; i < 4; i++) {
				if ((hand.at(i).getValue() != trips) && //
					hand.at(i).getValue() == hand.at(i + 1).getValue()) {
					return true;
				}
			}
		}
		return false;
	}

	bool four_of_a_kind() {
		for (int i = 0; i < 2; i++) {
			//check for four consecutive equal values
			if (((hand.at(i).getValue() == hand.at(i + 1).getValue()) &&
				(hand.at(i + 1).getValue() == hand.at(i + 2).getValue())) &&
				(hand.at(i + 2).getValue() == hand.at(i + 3).getValue())) {
				return true;
			}
		}
		return false;
	}
};
#endif
