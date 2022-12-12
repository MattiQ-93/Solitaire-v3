import java.util.HashMap;

public class Deck {
	
	HashMap<Integer, Card> deck = new HashMap<Integer, Card>();
	
	public Deck() {
		for (int i = 1; i <= 52; i++) {
			if (i >= 1 && i <= 13) {
				deck.put(i, new Card(i, Suit.SPADES));
			}
			if (i >= 14 && i <= 26) {
				deck.put(i, new Card(i - 13, Suit.CLUBS));
			}
			if (i >= 27 && i <= 39) {
				deck.put(i, new Card(i - 26, Suit.DIAMONDS));
			}
			if (i >= 40 && i <= 52) {
				deck.put(i, new Card(i - 39, Suit.HEARTS));
			}
		}
		deck.put(100, new Card());
		deck.put(101, new Card(Suit.SPADES));
		deck.put(102, new Card(Suit.CLUBS));
		deck.put(103, new Card(Suit.DIAMONDS));
		deck.put(104, new Card(Suit.HEARTS));
	}
	
	public Card getCard(int key) {
		return deck.get(key);
	}
}
