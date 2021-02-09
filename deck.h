#include<vector>
#include<algorithm>
#include"card.h"
#include<time.h>

#ifndef _DECK_H_
#define _DECK_H_

class Deck
{
public:
	void generateDeck() {
		deck.clear();
		for (int i = 1; i <= 4; i++) { //each suit
			for (int j = 2; j <= 14; j++) { //each value (2-14)
				Card newCard = Card(i, j);
				deck.push_back(newCard);
			}
		}
	}

	void shuffle() {
		std::vector<Card> shuffled_deck;
		generateDeck();
		srand(time(NULL));

		for (int i = 52; i > 0; i--) {
			//pick a random card
			int index = (int)(rand() % i);
			Card card = deck.at(index);

			//remove it from the deck
			deck.erase(deck.begin() + index);

			//place on a new deck
			shuffled_deck.push_back(card);
		}

		deck = shuffled_deck;
	}

	Card draw() {
		Card top = deck.back();
		deck.pop_back();
		return top;
	}

private:
	std::vector<Card> deck;
	int size = 0;
};
#endif
