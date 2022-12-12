import java.util.HashMap;

public class Deck {
	
	HashMap<Integer, Card> deck = new HashMap<Integer, Card>();
	
	public Deck() {
		
		// putting into hashmap newly created playing cards
		for (int i = 1; i <= 13; i++) {
			deck.put(i, new Card(i, Suit.SPADES));
			deck.put(i + 13, new Card(i, Suit.CLUBS));
			deck.put(i + 26, new Card(i, Suit.DIAMONDS));
			deck.put(i + 39, new Card(i, Suit.HEARTS));
		}
		
		// creating cards to be shown on empty additional and final stacks
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
