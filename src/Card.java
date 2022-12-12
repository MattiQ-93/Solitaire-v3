
public class Card {

	private int value;
	private String name;
	private String printName;
	private Suit suit;
	private Color color;
	
	public Card(int value,  Suit suit) {
		this.value = value;

		switch (value) {
			case 1 -> this.name =  "A";
			case 2,3,4,5,6,7,8,9,10 -> this.name = "" + value;
			case 11 -> this.name = "J";
			case 12 -> this.name = "Q";
			case 13 -> this.name = "K";
			
			default -> this.name = "";
		}
		
		this.suit = suit;
		
		switch (suit) {
			case HEARTS -> this.color = Color.RED;
			case DIAMONDS -> this.color = Color.RED;
			case CLUBS -> this.color = Color.BLACK;
			case SPADES -> this.color = Color.BLACK;
		}
		
		switch (value) {
			case 1,2,3,4,5,6,7,8,9,11,12,13 -> this.printName = (color == Color.RED ? "+" : "-") 
												+ "| " + name + " |"  + suit.toString().substring(0, 1);
			case 10 -> this.printName = (color == Color.RED ? "+" : "-")  + "| " + name 
												+ "|" + suit.toString().substring(0, 1);
		}
		
	}
	
	public Card(Suit suit) {
		this.value = 0;
		this.printName = "_______";
		this.suit = suit;

		switch (suit) {
			case HEARTS -> this.color = Color.RED;
			case DIAMONDS -> this.color = Color.RED;
			case CLUBS -> this.color = Color.BLACK;
			case SPADES -> this.color = Color.BLACK;
		}
	}
	
	public Card() {
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
