
public class Card {

	private int value;	// int representing value of card 1-52 used to determining if move is possible
	private String name;	// name of card 1-10 for numbers and J,D,K,A for face cards
	private String printName;	// string representing what will be printed on board (name + suit + color)
	private Suit suit;	// Hearts, Diamonds, Clubs, Spades
	private Color color;	// Red, Black
	
	public Card(int value,  Suit suit) {
		// constructor for creating playing cards 
		
		this.value = value;

		// defines name of card based on value
		switch (value) {
			case 1 -> this.name =  "A";
			case 2,3,4,5,6,7,8,9,10 -> this.name = "" + value;
			case 11 -> this.name = "J";
			case 12 -> this.name = "Q";
			case 13 -> this.name = "K";
		}
		
		this.suit = suit;

		// defines color based on suit
		switch (suit) {
			case HEARTS -> this.color = Color.RED;
			case DIAMONDS -> this.color = Color.RED;
			case CLUBS -> this.color = Color.BLACK;
			case SPADES -> this.color = Color.BLACK;
		}
		
		// creates printName based on value and using suit and color
		switch (value) {
			case 1,2,3,4,5,6,7,8,9,11,12,13 -> this.printName = (color == Color.RED ? "+" : "-") 
										+ "| " + name + " |"  + suit.toString().substring(0, 1);
			case 10 -> this.printName = (color == Color.RED ? "+" : "-")  + "| " + name 
										+ "|" + suit.toString().substring(0, 1);
		}
	}
	
	public Card(Suit suit) {
		// constructor for creating cards for empty final stacks
		
		this();
		this.suit = suit;
	}
	
	
	public Card() {
		// constructor for creating card for empty additional stack
		
		this.value = 0;
		this.printName = "_______";
	}

	
	public int getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	public String getPrintName() {
		return printName;
	}

	public Suit getSuit() {
		return suit;
	}

	public Color getColor() {
		return color;
	}
}
