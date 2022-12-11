
public class Card {

	private int value;
	private String name;
	private String printName;
	private String color;
	private int colorValue;
	
	public Card(int value,  String color) {
		this.value = value;

		switch (value) {
			case 1 -> this.name =  "A";
			case 2,3,4,5,6,7,8,9,10 -> this.name = "" + value;
			case 11 -> this.name = "J";
			case 12 -> this.name = "Q";
			case 13 -> this.name = "K";
			
			default -> this.name = "";
		}
		
		this.color = color;
		
		switch (color) {
		case "Spades" -> this.colorValue = 1;
		case "Clubs" -> this.colorValue = 1;
		case "Diamonds" -> this.colorValue = 2;
		case "Hearts" -> this.colorValue = 2;
		
		default -> this.colorValue = 0;
		}
		
		switch (value) {
			case 1,2,3,4,5,6,7,8,9,11,12,13 -> this.printName = (colorValue == 1 ? "+" : "-") 
												+ "| " + name + " |"  + color.substring(0, 1);
			case 10 -> this.printName = (colorValue == 1 ? "+" : "-")  + "| " + name 
												+ "|" + color.substring(0, 1);
		}
		
	}
	
	public Card(String color) {
		this.value = 0;
		this.printName = "_______";
		this.color = color;

		switch (color) {
		case "Spades" -> this.colorValue = 1;
		case "Clubs" -> this.colorValue = 1;
		case "Diamonds" -> this.colorValue = 2;
		case "Hearts" -> this.colorValue = 2;
		
		default -> this.colorValue = 0;
		}
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

	public String getColor() {
		return color;
	}

	public int getColorValue() {
		return colorValue;
	}
}
