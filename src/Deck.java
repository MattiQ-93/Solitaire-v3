import java.util.HashMap;

public class Deck {
	
	HashMap<Integer, Card> deck = new HashMap<Integer, Card>();
	
	public Deck() {
		for (int i = 1; i <= 52; i++) {
			if (i >= 1 && i <= 13) {
				deck.put(i, new Card(i, "Spades"));
			}
			if (i >= 14 && i <= 26) {
				deck.put(i, new Card(i - 13, "Clubs"));
			}
			if (i >= 27 && i <= 39) {
				deck.put(i, new Card(i - 26, "Diamonds"));
			}
			if (i >= 40 && i <= 52) {
				deck.put(i, new Card(i - 39, "Hearts"));
			}
		}
		deck.put(100, new Card(""));
		deck.put(101, new Card("Spades"));
		deck.put(102, new Card("Clubs"));
		deck.put(103, new Card("Diamonds"));
		deck.put(104, new Card("Hearts"));
	}
	
	public Card getCard(int key) {
		return deck.get(key);
	}
}
