import java.util.Scanner;

public class Menu {

	public static void main(String[] args) {
		printMenu();
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
				loopPrintingLine("|", " ", 1, 104, 1); 
				printTextWithinPipes("Type anything to go back to menu", 5);
				loopPrintingLine("|", "_", 1, 104, 1); 
				scanner.next();
				scanner.nextLine();
				printMenu();
			}
			case 3 -> {
				printInstructions();
				loopPrintingLine("|", " ", 1, 104, 1); 
				printTextWithinPipes("Type anything to go back to menu", 5);
				loopPrintingLine("|", "_", 1, 104, 1); 
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
	}
	
	public static void printInstructions() {
		loopPrintingLine(" ", "_", 1, 104, 1);
		loopPrintingLine("|", " ", 1, 104, 1); 
		printTextWithinPipes("1. Don't do anything stupid.", 5);
		printTextWithinPipes("2. Play to win a game.", 5);
		printTextWithinPipes("3. When you win a game, you win a game.", 5);
		loopPrintingLine("|", " ", 1, 104, 1); 
		printTextWithinPipes("If you have any problem, do what Dark Souls community suggests: \"GIT GUT\"", 5);
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
