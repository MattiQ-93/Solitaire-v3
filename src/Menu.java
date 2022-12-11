import java.util.Scanner;

public class Menu {

	public static void main(String[] args) {
		printInstructions();
	}
	
	private static final Scanner scanner = new Scanner(System.in);
	private static int menuChoice;

	
	public static void printMenu() {
		
		loopPrintingLine(" ", "_", 1, 104, 1); 					// " _____ "		
		loopPrintingLine("|", " ", 1, 104, 1); 					// "       "
		
		printTextWithinPipes("Welcome to this Solitaire - Java Console Edition");
		
		loopPrintingLine("|", " ", 1, 104, 1); 					// "       "
		loopPrintingLine("|", " ", 1, 104, 1); 					// "       "
		
		printTextWithinPipes("[ 1 ]     Start a game", 5);
		printTextWithinPipes("[ 2 ]     Check rules", 5);
		printTextWithinPipes("[ 3 ]     Check instructions", 5);
		
		loopPrintingLine("|", " ", 1, 104, 1); 					// "|     |"		
		loopPrintingLine("|", " ", 1, 104, 1); 					// "|     |"		
		loopPrintingLine("|", "_", 1, 104, 1); 					// "|_____|"

		
		if (!scanner.hasNextInt()) {
			printLabeledMessage("Please don't put letters in me. It hurts :'(");
			scanner.nextLine();
			printMenu();
		}
		
		
		menuChoice = scanner.nextInt();
		
		switch (menuChoice) {
			case 1 -> {
				Board.putValues();
				Board.printTable();
			}
			case 2 -> {
				printRules();
				scanner.next();
				scanner.nextLine();
				printMenu();
			}
			case 3 -> {
				printInstructions();
				scanner.next();
				scanner.nextLine();
				printMenu();
			}
			default ->{
				printLabeledMessage("You chose not available option. Please try again.");
				printMenu();
			}
		}
		
	}
	
	public static void printRules() {
		loopPrintingLine(" ", "_", 1, 104, 1);
		loopPrintingLine("|", " ", 1, 104, 1); 
		printTextWithinPipes("Rules for this game are simple. Play to win.", 5);
		loopPrintingLine("|", " ", 1, 104, 1);
		printTextWithinPipes("If you have any problem, do what Dark Souls community suggests: \"GIT GUT\"", 5);
		loopPrintingLine("|", " ", 1, 104, 1); 
		printTextWithinPipes("Type anything to go back to menu", 5);
		loopPrintingLine("|", "_", 1, 104, 1); 
	}
	
	public static void printInstructions() {
		loopPrintingLine(" ", "_", 1, 104, 1);
		printTextWithinPipes("");
		printTextWithinPipes("INSTRUCTIONS");
		printTextWithinPipes("");
		printTextWithinPipes("1.  For move card from one place to another, you need to type "
							+ "number corresponding to place you", 5);
		printTextWithinPipes("want to take card from.", 9);
		printTextWithinPipes("");
		printTextWithinPipes("2.  For taking card from board, you need to type number from 1 to 7."
							+ " After that you need to type", 5);
		printTextWithinPipes("number of row.", 9);
		printTextWithinPipes(""); 
		printTextWithinPipes("3.  If in column you chose, there is only 1 card visible, you don't"
							+ " need to choose row number.", 5);
		printTextWithinPipes("It is automatically recognized", 9);
		printTextWithinPipes("");
		printTextWithinPipes("4.  If there is more than 1 card visible in column, you need to type"
							+ " exact row number of the row,", 5);
		printTextWithinPipes("or lesser number (but at least 1), to move all visible cards"
							+ " from chosen column.", 9);
		printTextWithinPipes("");
		printTextWithinPipes("5.  If there is more than 1 card visible in column, you can type"
							+ " exact row number of the lowest", 5);
		printTextWithinPipes("card, 0 or number higher than row number, to take lowest card"
							+ " from chosen column.", 9);
		printTextWithinPipes(""); 
		printTextWithinPipes("6.  If there is more than 1 card visible in column, you can type"
							+ " exact row number to take multiple", 5);
		printTextWithinPipes("cards from somwhere within visible cards to the lowest card"
							+ " from chosen column.", 9);
		printTextWithinPipes("");
		printTextWithinPipes("7.  For taking card from additional stack you need to type 9.", 5);
		printTextWithinPipes(""); 
		printTextWithinPipes("8.  You can take cards back from final stacks. To do this, you need"
							+ " to type 8, and then", 5);
		printTextWithinPipes("number of final stack (from 1 to 4)", 9);
		printTextWithinPipes("");
		printTextWithinPipes("9.  After choosing place you want to take card from, you need to"
							+ " type number corresponding to", 5);
		printTextWithinPipes("place you want to move card to (1 to 7 for board, 8 for final"
							+ " stack).", 9);
		printTextWithinPipes("");
		printTextWithinPipes("10. You can type 0 to show next card in additional stack.", 5);
		printTextWithinPipes("");
		printTextWithinPipes("11. You can type 10 to undo your move.", 5);
		printTextWithinPipes("");
		printTextWithinPipes("12. You can type 20 to start actual game from start.", 5);
		printTextWithinPipes("");
		printTextWithinPipes("13. You can type 30 to start new game.", 5);
		printTextWithinPipes("");
		printTextWithinPipes("14. You can type 40 to go back to Main menu", 5);
		printTextWithinPipes("");
		printTextWithinPipes("15. When additional stack is empty, and all cards on board are"
							+ " visible, the game is almos won.", 5);
		printTextWithinPipes("If so, you can type 99 to automatically finish game, without need"
							+ " to put remaining cards", 9);
		printTextWithinPipes("manually on final stacks.", 9);
		printTextWithinPipes("");
		printTextWithinPipes("");
		printTextWithinPipes("Type anything to go back to menu", 5);
		loopPrintingLine("|", "_", 1, 104, 1); 
		
	}
	
	public static void loopPrinting(String text, int repeat) {
		// method for easy repeat printing single string
		
		for (int i = 0; i < repeat; i++) {
			System.out.print(text);
		}
	}
	
	public static void loopPrinting(String text1, String text2, int repeat1, int repeat2, int repeat3) {
		// method for easy repeat printing strings in format e.g. "|___|" 
		
		loopPrinting(text1, repeat1);
		loopPrinting(text2, repeat2);
		loopPrinting(text1, repeat3);
	}
	
	public static void loopPrintingLine(String text1, String text2, int repeat1, int repeat2, int repeat3) {
		// method for easy repeat printing strings in format e.g. "|___|" with new line 
		
		loopPrinting(text1, text2, repeat1, repeat2, repeat3);
		loopPrinting("\n", 1);
	}
	
	public static void printTextWithinPipes(String text, int repeat1) {
		loopPrinting("|", 1);
		loopPrinting(" ", repeat1);
		loopPrinting(text, 1);
		loopPrinting(" ", 104 - repeat1 - text.length());
		loopPrinting("|", 1);
		loopPrinting("\n", 1);
		
	}
	
	public static void printTextWithinPipes(String text) {
		int int0 = text.length();
		int int1 = (104 - int0)/2;
		int int2 = 104 - int1 - int0;
		
		loopPrinting("|", 1);
		loopPrinting(" ", int1);
		loopPrinting(text, 1);
		loopPrinting(" ", int2);
		loopPrinting("|", 1);
		loopPrinting("\n", 1);
	}
	
	public static void loopPrintingLine(String text1, String text2,int repeat0, int repeat1, int repeat2, int repeat3, int repeat4, int repeat5) {
		// method for easy repeat printing strings in format e.g. 
		// "|___|___|___|___|___|" with new line 
		
		loopPrinting(text1, repeat0);
		loopPrinting(text2, repeat1);
		loopPrinting(text1, repeat0);
		loopPrinting(text2, repeat2);
		loopPrinting(text1, repeat0);
		loopPrinting(text2, repeat3);
		loopPrinting(text1, repeat0);
		loopPrinting(text2, repeat4);
		loopPrinting(text1, repeat0);
		loopPrinting(text2, repeat5);
		loopPrinting(text1, repeat0);
		loopPrinting("\n", 1);
	}
	
	public static void printLabeledMessage(String text) {
		// print text in "box"
		
		loopPrintingLine(" ", "_", 1, 104, 1);
		loopPrintingLine("|", " ", 1, 104, 1);
		printTextWithinPipes(text, 5);
		loopPrintingLine("|", "_", 1, 104, 1);
	}
}
