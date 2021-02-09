#ifndef _CARD_H_
#define _CARD_H_

class Card
{
public:
	Card(int suit, int value) {
		this->suit = suit;
		this->value = value;
	};

	int getSuit() {
		return suit;
	};

	int getValue() {
		return value;
	};

	void setSuit(int suit) {
		this->suit = suit;
	};

	void setValue(int value) {
		this->value = value;
	};

private:
	int suit; // 1 (spade), 2 (heart), 3 (diamond), 4 (club)
	int value; // 2 = 2, ... 10 = 10, 11 = Jack, 12 = Queen, 13 = King, 14 = Ace
};

#endif