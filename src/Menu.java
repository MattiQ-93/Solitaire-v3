import java.util.Scanner;

public class Menu {

	private static final Scanner scanner = new Scanner(System.in);
	private static int menuChoice;

	
	public static void printMenu() {
		
		for (int i = 0; i < 102; i++) {
			System.out.print("_");
		}
		System.out.println("\n\nWelcome to this Solitaire - Java Console Edition");	
		System.out.println();
		System.out.println("[ 1 ]	Start a game");
		System.out.println("[ 2 ]	Check rules");
		System.out.println("[ 3 ]	Check instructions");

		if (!scanner.hasNextInt()) {
			System.out.println("Please don't put letters in me. It hurts :'(");
			scanner.nextLine();
			printMenu();
		}
		
		menuChoice = scanner.nextInt();
		
		if (menuChoice == 1) {
			Board.putValues();
			Board.printTable();
		}
		if (menuChoice== 2) {
			printRules();
			System.out.println("\nType anything to go back to menu");
			scanner.next();
			scanner.nextLine();
			printMenu();
		}
		if (menuChoice== 3) {
			printInstructions();
			System.out.println("\nType anything to go back to menu");
			scanner.next();
			scanner.nextLine();
			printMenu();
		}

		if (menuChoice!= 1 || menuChoice!= 2 || menuChoice!= 3) {
			System.out.println("You chose not available option. Please try again.\n");
			printMenu();
		}
	}
	
	public static void printRules() {
		for (int i = 0; i < 102; i++) {
			System.out.print("_");
		}
		System.out.println("\n\nRules for this game are simple. Play to win.");
		System.out.println("\nIf you have any problem, do what Dark Souls community suggests: \"GIT GUT\"");
	}
	
	public static void printInstructions() {
		for (int i = 0; i < 102; i++) {
			System.out.print("_");
		}
		System.out.println("\n\n1. Don't do anything stupid.");
		System.out.println("2. Play to win a game.");
		System.out.println("3. When you win a game, you win a game.");
		System.out.println("\nIf you have any problem, do what Dark Souls community suggests: \"GIT GUT\"");
	}
}
