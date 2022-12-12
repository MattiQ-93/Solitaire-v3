import java.util.Scanner;

public class Menu {

	
	private static final Scanner scanner = new Scanner(System.in);
	private static int menuChoice;

	
	public static void printMenu() {
		
		Print.loopPrintingLine(" ", "_", 1, 104, 1); 					// " _____ "		
		Print.loopPrintingLine("|", " ", 1, 104, 1); 					// "       "
		
		Print.printTextWithinPipes("Welcome to this Solitaire - Java Console Edition");
		
		Print.loopPrintingLine("|", " ", 1, 104, 1); 					// "       "
		Print.loopPrintingLine("|", " ", 1, 104, 1); 					// "       "
		
		Print.printTextWithinPipes("[ 1 ]     Start a game", 5);
		Print.printTextWithinPipes("[ 2 ]     Check rules", 5);
		Print.printTextWithinPipes("[ 3 ]     Check instructions", 5);
		
		Print.loopPrintingLine("|", " ", 1, 104, 1); 					// "|     |"		
		Print.loopPrintingLine("|", " ", 1, 104, 1); 					// "|     |"		
		Print.loopPrintingLine("|", "_", 1, 104, 1); 					// "|_____|"

		
		if (!scanner.hasNextInt()) {
			Print.printLabeledMessage("Please don't put letters in me. It hurts :'(");
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
				Print.printLabeledMessage("You chose not available option. Please try again.");
				printMenu();
			}
		}
		
	}
	
	public static void printRules() {
		Print.loopPrintingLine(" ", "_", 1, 104, 1);
		Print.loopPrintingLine("|", " ", 1, 104, 1); 
		Print.printTextWithinPipes("Rules for this game are simple. Play to win.", 5);
		Print.loopPrintingLine("|", " ", 1, 104, 1);
		Print.printTextWithinPipes("If you have any problem, do what Dark Souls community suggests: \"GIT GUT\"", 5);
		Print.loopPrintingLine("|", " ", 1, 104, 1); 
		Print.printTextWithinPipes("Type anything to go back to menu", 5);
		Print.loopPrintingLine("|", "_", 1, 104, 1); 
	}
	
	public static void printInstructions() {
		Print.loopPrintingLine(" ", "_", 1, 104, 1);
		Print.printTextWithinPipes("");
		Print.printTextWithinPipes("INSTRUCTIONS");
		Print.printTextWithinPipes("");
		Print.printTextWithinPipes("1.  For move card from one place to another, you need to type "
							+ "number corresponding to place you", 5);
		Print.printTextWithinPipes("want to take card from.", 9);
		Print.printTextWithinPipes("");
		Print.printTextWithinPipes("2.  For taking card from board, you need to type number from 1 to 7."
							+ " After that you need to type", 5);
		Print.printTextWithinPipes("number of row.", 9);
		Print.printTextWithinPipes(""); 
		Print.printTextWithinPipes("3.  If in column you chose, there is only 1 card visible, you don't"
							+ " need to choose row number.", 5);
		Print.printTextWithinPipes("It is automatically recognized", 9);
		Print.printTextWithinPipes("");
		Print.printTextWithinPipes("4.  If there is more than 1 card visible in column, you need to type"
							+ " exact row number of the row,", 5);
		Print.printTextWithinPipes("or lesser number (but at least 1), to move all visible cards"
							+ " from chosen column.", 9);
		Print.printTextWithinPipes("");
		Print.printTextWithinPipes("5.  If there is more than 1 card visible in column, you can type"
							+ " exact row number of the lowest", 5);
		Print.printTextWithinPipes("card, 0 or number higher than row number, to take lowest card"
							+ " from chosen column.", 9);
		Print.printTextWithinPipes(""); 
		Print.printTextWithinPipes("6.  If there is more than 1 card visible in column, you can type"
							+ " exact row number to take multiple", 5);
		Print.printTextWithinPipes("cards from somwhere within visible cards to the lowest card"
							+ " from chosen column.", 9);
		Print.printTextWithinPipes("");
		Print.printTextWithinPipes("7.  For taking card from additional stack you need to type 9.", 5);
		Print.printTextWithinPipes(""); 
		Print.printTextWithinPipes("8.  You can take cards back from final stacks. To do this, you need"
							+ " to type 8, and then", 5);
		Print.printTextWithinPipes("number of final stack (from 1 to 4)", 9);
		Print.printTextWithinPipes("");
		Print.printTextWithinPipes("9.  After choosing place you want to take card from, you need to"
							+ " type number corresponding to", 5);
		Print.printTextWithinPipes("place you want to move card to (1 to 7 for board, 8 for final"
							+ " stack).", 9);
		Print.printTextWithinPipes("");
		Print.printTextWithinPipes("10. You can type 0 to show next card in additional stack.", 5);
		Print.printTextWithinPipes("");
		Print.printTextWithinPipes("11. You can type 10 to undo your move.", 5);
		Print.printTextWithinPipes("");
		Print.printTextWithinPipes("12. You can type 20 to start actual game from start.", 5);
		Print.printTextWithinPipes("");
		Print.printTextWithinPipes("13. You can type 30 to start new game.", 5);
		Print.printTextWithinPipes("");
		Print.printTextWithinPipes("14. You can type 40 to go back to Main menu", 5);
		Print.printTextWithinPipes("");
		Print.printTextWithinPipes("15. When additional stack is empty, and all cards on board are"
							+ " visible, the game is almos won.", 5);
		Print.printTextWithinPipes("If so, you can type 99 to automatically finish game, without need"
							+ " to put remaining cards", 9);
		Print.printTextWithinPipes("manually on final stacks.", 9);
		Print.printTextWithinPipes("");
		Print.printTextWithinPipes("");
		Print.printTextWithinPipes("Type anything to go back to menu", 5);
		Print.loopPrintingLine("|", "_", 1, 104, 1); 
		
	}
	
	
}
