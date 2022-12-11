import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Board {

	/* array to visualize board 7 columns for 7 stacks of cards, 
	 * and 19 rows for maximal possible amount of cards in single row
	 * there is used an int array, for storing keys for hashmap storing cards
	 */  
	
	private static int[][] playTable = new int[19][7];
	
	// array to set or check if card can be shown 
	private static boolean[][] visibility = new boolean[19][7];
	// array to set/check amount of card that are stored in all 7 stacks
	private static int[] cardAmounts = new int[7];
	
	
	private static int stackSize;
	private static int stackIterator;
	
	// analogically as playTable array, this array stores Integers for 4 final stacks
	private static int[] endStacks = new int[4];

	private static final Scanner scanner = new Scanner(System.in);
	private static int sCol;
	private static int sRow;
	private static int dCol;
	private static int dRow;
	
	private static Card card1;
	private static Card card2;
	
	
	private static int numberOfCards;
	
	// new Deck object to get cards
	private static Deck cardDeck = new Deck();
	
	// List for cards in additional stack outside of board
	private static List<Integer> stackList = new ArrayList<>();
	
	// used for undoing moves and resetting game (going back before 1st move)
	private static int countSteps; 
	
	// count moves for winning screen
	private static int countMoves;
	
	// this hashmap stores objects from class Steps, which stores backups from every move
	// for undoing moves, or going back before 1st move
	 
	private static HashMap<Integer, Steps> stepsMap = new HashMap<>();
	
	
	public static void putValues() {
	// method used to put Integers to board, stacklist, and step 0

		
		// setting visibility of card. at start of the game, there can be only 1 card visible on every stack
		for (int i = 0; i < visibility.length; i++) {
			for (int j = 0; j < visibility[0].length; j++) {
				if(i < j) {
					visibility[i][j] = false;
				} else {
					visibility[i][j] = true;
				}
			}
		}
		
		// putting base value to whole board array
		for (int i = 0; i < playTable.length; i++) {
			for (int j = 0; j < playTable[0].length; j++) {
				playTable[i][j] = 0;
			}
		}

		// setting base amount of cards in 7 stacks
		for (int i = 0; i < cardAmounts.length; i++) {
			cardAmounts[i] = i + 1;
		}
		
		stackSize = 24;
		stackIterator = 0;
		
		// putting base values for final stacks
		for (int i = 0; i < endStacks.length; i++) {
			endStacks[i] = 104 - i;
		}
		
		// create list to be shuffled to get new random set of cards each play
		List<Integer> shufflerList = new ArrayList<>();
		for (int i = 1; i <= 52; i++) {
			shufflerList.add(i);
		}
		Collections.shuffle(shufflerList);

		
		// putting values from shuffled list to board and stacklist
		for (int j = 0; j < 7; j++) {
			for (int i = j; i < 7; i++) {
				playTable[j][i] = shufflerList.get(0);
				shufflerList.remove(0);
			}
		}
		
		stackList.clear();
		for (int i = 0; i < 24; i++) {
			stackList.add(shufflerList.get(0));
			shufflerList.remove(0);
		}
		
		countSteps = 0;
		countMoves = 0;
				
		makeBackup(); // putting values into hashmap of steps for step 0
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
	
	public static void printTextWithinPipes(String text, int repeat1) {
		// method for easy printing text between two pipes with repeat1 amounts of spaces before text
		
		loopPrinting("|", 1);
		loopPrinting(" ", repeat1);
		loopPrinting(text, 1);
		loopPrinting(" ", 104 - repeat1 - text.length());
		loopPrinting("|", 1);
		loopPrinting("\n", 1);
		
	}
	
	public static void printTextWithinPipes(String text) {
		// method for easy printing text between two pipes in the midle of them
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
	
	public static void printLabeledMessage(String text) {
		// print text in "box"
		
		loopPrintingLine(" ", "_", 1, 104, 1);
		loopPrintingLine("|", " ", 1, 104, 1);
		printTextWithinPipes(text, 5);
		loopPrintingLine("|", "_", 1, 104, 1);
	}
	
	public static void printChooseColumnMessage(){
		printTextWithinPipes("Choose column or stack to move card(s) from...", 5);
	}
	public static void printChooseRowMessage(){
		printTextWithinPipes("Choose row to move card(s) from...", 5);
	}
	public static void printChooseDestinationMessage(){
		printTextWithinPipes("Choose column or stack to move card(s) to...", 5);
	}
	public static void printChooseEndStackMessage(){
		printTextWithinPipes("Choose end stack to move card from...(1 to 4)", 5);
	}
	public static void printWrongMoveMessage(){
		printLabeledMessage("Wrong move. Please try again.");
	}
	public static void printNoMovesMessage(){
		printLabeledMessage("You didn't make any moves.");
	}
	public static void printTypedLettersMessage(){
		printLabeledMessage("Please don't put letters in me. It hurts :'(");
	}
	public static void printEmptyStackMessage(){
		printLabeledMessage("Stack is empty. Please try again.");
	}
	
	
	public static void printTable() {
		
		// checks if game is already won
		if (isGameWon()) {
			printWinningScreen();
		}
		
		// print 1st and 2nd lines
		loopPrintingLine(" ", "_", 1, 104, 1); 					// " _____ "		
		loopPrintingLine("|", " ", 1, 19, 23, 19, 19, 20);		// "|     |"
		
		// print 3rd line with color names of final stacks, and additional stack
		loopPrinting("| ", 1);
		System.out.print("[ 8 ] Hearts");
		loopPrinting(" ", 2);
		System.out.print("(1)");
		loopPrinting(" | ", 1);
		System.out.print("[ 8 ] Diamonds");
		loopPrinting(" ", 4);
		System.out.print("(2)");
		loopPrinting(" | ", 1);
		System.out.print("[ 8 ] Clubs");
		loopPrinting(" ", 3);
		System.out.print("(3)");
		loopPrinting(" | ", 1);
		System.out.print("[ 8 ] Spades");
		loopPrinting(" ", 2);
		System.out.print("(4)");
		loopPrinting(" | ", 1);
		loopPrinting(" ", 3);
		System.out.print("[ 9 ] Stack");
		loopPrinting(" ", 5); 
		loopPrinting("|\n", 1); 
		
		// print 4th line with card names of final stacks, and additional stack
		loopPrintingLine("|", " ", 1, 19, 23, 19, 19, 20);		// "|     |"
		
		loopPrinting("|", 1); 
		loopPrinting(" ", 6);
		System.out.print(cardDeck.getCard(endStacks[0]).getPrintName());
		loopPrinting(" ", "|", 6, 1, 8);									// "  |  "
		System.out.print(cardDeck.getCard(endStacks[1]).getPrintName());
		loopPrinting(" ", "|", 8, 1, 6);									// "  |  "
		System.out.print(cardDeck.getCard(endStacks[2]).getPrintName());
		loopPrinting(" ", "|", 6, 1, 6);									// "  |  "
		System.out.print(cardDeck.getCard(endStacks[3]).getPrintName());
		loopPrinting(" ", "|", 6, 1, 6);									// "  |  "
		System.out.print(cardDeck.getCard(stackList.get(stackIterator)).getPrintName());
		loopPrinting(" ", 7);
		loopPrinting("|\n", 1);
		
		// print 5th and 6th lines
		loopPrintingLine("|", "_", 1, 19, 23, 19, 19, 20);		// "|_____|"
		loopPrintingLine("|", " ", 1, 104, 1); 					// "|     |"
		
		// print 7th line with column numbers to choose from,
		// and number for refreshing additional stack
		for (int i = 0; i < 7; i++) {
			if (i == 0) {
				loopPrinting("|", 1); 
				loopPrinting(" ", 8);
			}
			System.out.print("[  " + (i + 1) + "  ]");
			if (i < 6) {
				loopPrinting(" ", 3);
			}
		}
		loopPrinting(" ", 9);
		System.out.print("[ 0 ] Refresh stack");
		loopPrinting(" |\n", 1);

		// print 5th and 6th lines
		loopPrintingLine("|", "_", 1, 104, 1); 					// "|_____|"
		loopPrintingLine("|", " ", 1, 104, 1); 					// "|     |"
		
		// start printing board
		for (int i = 0; i < playTable.length; i++) {
			for (int j = 0; j < playTable[0].length; j++) {
				
				// print row numbers at start of each row
				if (j == 0) {
					if (i < 9) {
						System.out.print("|  " + (i + 1) + " | | ");
 					} else {
 						System.out.print("| " + (i + 1) + " | | ");
					}
				}
				
				// if in place on board, there is 0, print "blank" card
				if (playTable[i][j] == 0) {
					loopPrinting(" ", 7);
				} 
				// if in place on board, value is bigger than 0 and place is visible, print card name
				if (playTable[i][j] > 0 && visibility[i][j] == true) {
					System.out.print(cardDeck.getCard(playTable[i][j]).getPrintName());
				} 
				// if place on board isn't visible, print "*******"
				if (visibility[i][j] == false) { 
					loopPrinting("*", 7);
				}
				
				// print " | " as a separator between each card in row
				if (j < playTable[0].length -1) {
					loopPrinting(" | ", 1);
				}
				
				// print row number at the end of each row
				if (j == playTable[0].length -1) {
					if (i < 9) {
						System.out.print(" | | " + (i + 1) + "  |");
 					} else {
 						System.out.print(" | | " + (i + 1) + " |");
					}

						// print "side panel" with options
						switch (i) {
							case 0 -> {
								loopPrinting(" ", 2);
								System.out.print("[ 10] Undo move");
								loopPrinting(" ", 3);
								System.out.print("|");
							}
							case 1 -> {
								loopPrinting(" ", 2);
								System.out.print("[ 20] Reset board");
								loopPrinting(" ", 1);
								loopPrinting("|", 1);
							}
							case 2 -> {
								loopPrinting(" ", 2);
								System.out.print("[ 30] New game");
								loopPrinting(" ", 4);
								loopPrinting("|", 1);
							}
							case 3 -> {
								loopPrinting(" ", 2);
								System.out.print("[ 40] Main menu");
							 	loopPrinting(" ", 3);
							 	loopPrinting("|", 1);
							}
							// option to automatically finish game prints only if it's possible
							// to finish game
							case 6 -> {
								loopPrinting(" ", 2);
									if (isGameAlmostWon()) {
										System.out.print("[ 99] Finish game");
										loopPrinting(" ", 1);
										loopPrinting("|", 1);
									}
									if (!isGameAlmostWon()) {
										loopPrinting(" ", 18);
										loopPrinting("|", 1);
									}
							}
							default -> {
								loopPrinting(" ", 20);
								loopPrinting("|", 1);
							}
						}
					loopPrinting("\n", 1);
				}
			}
		}
		
		// print last lines
		loopPrintingLine("|", "_", 1, 104, 1); 
		loopPrinting("\n", 1);
		
		// go and play game :]
		playGame();
	}
	
	public static void printWinningScreen() {
		// prints the screen after successfully finishing a game
		loopPrintingLine(" ", "_", 1, 104, 1);
		loopPrintingLine("|", " ", 1, 104, 1);
		printTextWithinPipes("Congratulations. You just won the game. I'm very happy for your victory. ;)", 5);
		loopPrintingLine("|", " ", 1, 104, 1);
		printTextWithinPipes("It took " + countMoves + " moves for you to finish this game.", 5);
		loopPrintingLine("|", " ", 1, 104, 1);
		printTextWithinPipes("Type anything to go back to menu", 5);
		loopPrintingLine("|", "_", 1, 104, 1); 
		loopPrinting("\n", 1);
		
		scanner.nextLine();
		Menu.printMenu();
	}
	
	public static void playGame() {
		
		printChooseColumnMessage();

		// checks if user typed integer or not
		checkInput();
		
		// get first choice/ input
		sCol = scanner.nextInt();
		
		switch (sCol) {
			case 0 -> refreshStack();				// refresh stack
			case 1,2,3,4,5,6,7 -> moveFromBoard();	// user chose column
			case 9 -> moveFromStack();				// user chose additional stack 
			case 8 -> moveFromEndStack();			// user chose final stack
			case 10 -> undoMove();					// undo move
			case 20 -> resetGame();					// reset game - start again the same game
			case 30 -> {							// new game
				putValues();
				printTable();
			}
			case 40 -> Menu.printMenu();			// go back to menu
			case 99 -> {							// instant finish game, only if possible
				if (isGameAlmostWon()) {
					finishGame();
					printWinningScreen();
				}
			}	
			default -> {							// nothing possible was chosen
				printWrongMoveMessage();
				playGame();
			}
		}
	}
		
	public static void addSteps() {
		// this method increments steps, used for storing every new move, so user can go back
		// make backup of every step
		// increments moves so you can see how many moves was needed to finish game
		
		countSteps += 1;
		makeBackup();
		countMoves += 1;
	}
	
	public static void makeBackup() {
		// this method makes backup of every step, and puts it to hashmap which stores all backups
		
		
		stepsMap.put(countSteps, new Steps());
		
		// backing up card positions and positions visibility
		for (int i = 0; i < playTable.length; i++) {
			for (int j = 0; j < playTable[0].length; j++) {
				stepsMap.get(countSteps).setPlayTable(playTable[i][j], i, j);
				stepsMap.get(countSteps).setVisibility(visibility[i][j], i, j);
			}
		}
		
		// backing up card amounts of each column, as these are not counting themselves, but are set
		for (int i = 0; i < cardAmounts.length; i++) {
			stepsMap.get(countSteps).setCardAmounts(cardAmounts[i], i);
		}
		
		// backing up actual size of additional stack, and actual iterator of stack list
		stepsMap.get(countSteps).setStackSize(stackSize);
		stepsMap.get(countSteps).setStackIterator(stackIterator);
		
		// backing up actual state of final stacks
		for (int i = 0; i < endStacks.length; i++) {
			stepsMap.get(countSteps).setEndStacks(endStacks[i], i);
		}
		
		stepsMap.get(countSteps).setStackList(stackList);
	}
	
	public static void undoMove() {
		// this method allows user to go back one move at a time
		
		// if game just started, you have no steps to go back to
		if (countSteps == 0) {
			printNoMovesMessage();
			playGame();
		}
		
		// steps count need to be decreased to be able to get previous step loaded
		countSteps -= 1;
		
		// loading all values stored as backup
		for (int i = 0; i < playTable.length; i++) {
			for (int j = 0; j < playTable[0].length; j++) {
				playTable[i][j] = stepsMap.get(countSteps).getPlayTable(i, j);
				visibility[i][j] = stepsMap.get(countSteps).getVisibility(i, j);
			}
		}
		for (int i = 0; i < cardAmounts.length; i++) {
			cardAmounts[i] = stepsMap.get(countSteps).getCardAmounts(i);
		}
		stackSize = stepsMap.get(countSteps).getStackSize();
		stackIterator = stepsMap.get(countSteps).getStackIterator();
		for (int i = 0; i < endStacks.length; i++) {
			endStacks[i] = stepsMap.get(countSteps).getEndStacks(i);
		}
		stackList.clear();
		for(int i = 0; i < stackSize; i++) {
			stackList.add(stepsMap.get(countSteps).getStackList(i));
		}

		// going back is still counted as move
		countMoves += 1;

		
		printTable();
	}
	
	public static void resetGame() {
		// this method resets game, which means going back to step 0
		
		// loading all values stored as backup for step 0
		for (int i = 0; i < playTable.length; i++) {
			for (int j = 0; j < playTable[0].length; j++) {
				playTable[i][j] = stepsMap.get(0).getPlayTable(i, j);
				visibility[i][j] = stepsMap.get(0).getVisibility(i, j);
			}
		}
		for (int i = 0; i < cardAmounts.length; i++) {
			cardAmounts[i] = stepsMap.get(0).getCardAmounts(i);
		}
		stackSize = stepsMap.get(0).getStackSize();
		stackIterator = stepsMap.get(0).getStackIterator();
		for (int i = 0; i < endStacks.length; i++) {
			endStacks[i] = stepsMap.get(0).getEndStacks(i);
		}
		stackList.clear();
		for(int i = 0; i < stackSize; i++) {
			stackList.add(stepsMap.get(0).getStackList(i));
		}
		countSteps = 0;
		countMoves = 0;
		
		printTable();
	}
	
	public static void refreshStack() {
		// this method allows to get next card from additional stack
		
		// if stack is already empty, print message it's empty
		if (stackSize == 0) {
			printEmptyStackMessage();
			playGame();
		}
		
		// if there is shown last card from stack, show first card
		if (stackIterator == stackSize - 1) {
			stackIterator = 0;
			addSteps();
			printTable();
		}
		
		// if shown card isn't last on list, show next card
		if (stackIterator < stackSize - 1) {
			stackIterator += 1;
			addSteps();
			printTable();
		}
	}
		
	public static boolean isMovePosible() {
		// checks if simple move is possible, which means you try to put card with lower value
		// on card with higher value, and with different color (red/black)
		
		if (card1.getValue() == card2.getValue() -1 && card1.getColorValue() != card2.getColorValue()) {
			return true;
		}
		return false;
	}
	
	public static boolean isKingMovingPosible() {
		// checks if moving king within board is possible
		// within board, kings can be moved only destination column, has no other cards
		
		if (card1.getValue() == 13 && cardAmounts[dCol - 1] == 0) {
			return true;
		}
		return false;
	}
	
	public static boolean isMovingToEndStackPosible() {
		// checks if moving card to final stack is possible. to do so, you need to put card 
		// with higher value, than card on final stack, and with the same color name
		// you cannot put multiple cards, on final stack, at once
		
		if (numberOfCards > 1) {
			return false;
		}
		if(card1.getValue() == card2.getValue() + 1 && card1.getColor().equals(card2.getColor())) {
			return true;
		}
		return false;
	}
	
	public static boolean isThereCardsOnEndStack() {
		// checks if there is any card on end stack you can move
		
		for (int i = 0; i < endStacks.length; i++) {
			if (cardDeck.getCard(endStacks[sRow -1]).getValue() > 0) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isGameWon() {
		// checks if game is won. game is won if in all final stacks there is a king
		
		for(int i = 0; i < endStacks.length; i++) {
			if (cardDeck.getCard(endStacks[i]).getValue() != 13) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean isGameAlmostWon() {
		// checks if game is almost won, and you can choose instant finish option
		// it checks 2 requirements, stack needs to be empty, and whole 1st row need to be visible
		
		if (stackSize != 0) {
			return false;
		}
		for (int i = 0; i < visibility[0].length; i++) {
			if (visibility[0][i] == false) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean isCardAlone() {
		// checks if card in chosen column is the only one visible
		
		int visibleCardsAmount = 0;
		for (int i = 0; i < cardAmounts[sCol - 1]; i++) {
			if (visibility[i][sCol - 1] == true) {
				visibleCardsAmount += 1;
			}
		}
		if (visibleCardsAmount == 1) {
			return true;
		}
		return false;
	}
	
	public static void checkInput() {
		// checks if user typed integer or not
		if (!scanner.hasNextInt()) {
			printTypedLettersMessage();
			scanner.nextLine();
			playGame();
		}
	}
	
	public static void gettingCardTwo() {
		// this method declares card2 depending on move you want to make
		
		
		// this is used when you try to move card to final stack
		if (dCol == 8) {
			switch (card1.getColor()) {
			case "Hearts" -> {
				card2 = cardDeck.getCard(endStacks[0]);
				dRow = 0;
			}
			case "Diamonds" ->{
				card2 = cardDeck.getCard(endStacks[1]);
				dRow = 1;
			}
			case "Clubs" -> {
				card2 = cardDeck.getCard(endStacks[2]);
				dRow = 2;
			}
			case "Spades" -> {
				card2 = cardDeck.getCard(endStacks[3]);
				dRow = 3;
			}
			}
			return;
		}
		
		// this is used when you try to move cards within board that are not kings
		try {
			if (card1.getValue() != 13) {
				card2 = cardDeck.getCard(playTable[cardAmounts[dCol - 1] - 1][dCol - 1]);
				return;
			} 
		} catch (ArrayIndexOutOfBoundsException e) {
			printWrongMoveMessage();
			playGame();
		} catch (NullPointerException e) {
			printWrongMoveMessage();
			playGame();
		}
	}
	
	public static void moveFromBoard() {
		// this method is used to move cards from board
		
		// checks if there is only 1 card visible in column. if yes, it automatically chooses
		// this only card, and you don't have to choose row
		if (isCardAlone()) {
			sRow = cardAmounts[sCol -1];
		}
		
		// if there are more than 1 cards visible in column, then you need to
		//  choose row to move card from
		if (!isCardAlone()) {
			printChooseRowMessage();
			
			// checks if user typed integer or not
			checkInput();
			
			sRow = scanner.nextInt();
			
			// if you typed 0, or row number bigger, than lowest card in column,
			// it automatically chooses lowest card in column
			if (sRow == 0 || sRow > cardAmounts[sCol -1]) {
				sRow = cardAmounts[sCol -1];
			}
			
			// if you typed row number lower, than highest card in column,
			// it automatically chooses highest card in column
			if (sRow > 0 && visibility[sRow - 1][sCol - 1] == false) {
				for (int i = 0; i < cardAmounts[sCol -1]; i++) {
					if (visibility[i][sCol - 1] == true) {
						sRow = i + 1;
						break;
					}
				}
			}
		}
		
		card1 = cardDeck.getCard(playTable[sRow - 1][sCol - 1]);
		
		printChooseDestinationMessage();
		
		// checks if user typed integer or not
		checkInput();
		
		dCol = scanner.nextInt();
		
		gettingCardTwo();
		
		// value used for moving more than 1 card at once
		numberOfCards = cardAmounts[sCol -1] - sRow + 1;
		
		// checks what move you want to make, an launch corresponding methods
		try {
			if (isMovingToEndStackPosible()) {
				moveToEndStack();
				addSteps();
				printTable();
 			}
			if (isKingMovingPosible()) {
				boardMoving();
				addSteps();
				printTable();
			}
			if (isMovePosible()) {
				boardMoving();
				addSteps();
				printTable();
			} 
			if (!isKingMovingPosible() && !isMovePosible() && !isMovingToEndStackPosible()){
				printWrongMoveMessage();
				playGame();
			}
		} catch (NullPointerException e) {
			printWrongMoveMessage();
			playGame();
		} catch (ArrayIndexOutOfBoundsException e) {
			printWrongMoveMessage();
			playGame();
		}
	}
	
	public static void moveFromStack() {
		// this method is used to move cards from additional stack
		
		// checks if stack is empty
		if (stackSize == 0) {
			printEmptyStackMessage();
			playGame();
		}
		
		printChooseDestinationMessage();

		// checks if user typed integer or not
		checkInput();
		
		dCol = scanner.nextInt();
		
		card1 = cardDeck.getCard(stackList.get(stackIterator));
		
		numberOfCards = 1;
		
		gettingCardTwo();

		// checks what move you want to make, an launch corresponding methods
		try {
			if (isMovingToEndStackPosible()) {
				moveToEndStack();
				addSteps();
				printTable();
			}
			if (isKingMovingPosible()) {
				stackMoving();
				addSteps();
				printTable();
			}
			if (isMovePosible()) {
				stackMoving();
				addSteps();
				printTable();
			}
			if (!isKingMovingPosible() && !isMovePosible()){
				printWrongMoveMessage();
				playGame();
			}
		} catch (NullPointerException e) {
			printWrongMoveMessage();
			playGame();
		} catch (ArrayIndexOutOfBoundsException e) {
			printWrongMoveMessage();
			playGame();
		}
	}

	public static void moveFromEndStack() {
		// this method is used to move cards back from final stacks
		
		printChooseEndStackMessage();

		// checks if user typed integer or not
		checkInput();
		
		sRow = scanner.nextInt();
		
		// checks if there is any card you can move from chosen final stack
		if (!isThereCardsOnEndStack()) {
			printWrongMoveMessage();
			playGame();
		}
		
		try {
			card1 = cardDeck.getCard(endStacks[sRow - 1]);
		} catch (ArrayIndexOutOfBoundsException e) {
			printWrongMoveMessage();
			playGame();
		}
		
		printChooseDestinationMessage();

		// checks if user typed integer or not
		checkInput();
		
		dCol = scanner.nextInt();
		
		gettingCardTwo();
		
		numberOfCards =  1;
	
		// checks if you want to take back king, or other card
		try {
			if (!isKingMovingPosible() && !isMovePosible()){
				printWrongMoveMessage();
				playGame();
			}
			endStackMoving();
			addSteps();
			printTable();
		} catch (NullPointerException e) {
			printWrongMoveMessage();
			playGame();
		} catch (ArrayIndexOutOfBoundsException e) {
			printWrongMoveMessage();
			playGame();
		}
	}

	public static void boardMoving() {
		// mechanic behind moving card within board
		
		// take card(s) from source to destination column, and put 0's to source column
		for (int i = 0; i < numberOfCards; i++) {
			playTable[cardAmounts[dCol - 1] + i][dCol - 1] = playTable[sRow - 1 + i][sCol - 1];
			playTable[sRow - 1 + i][sCol - 1] = 0;
		} 
		
		// change amounts of card which are actually stored in columns
		cardAmounts[sCol - 1] -= numberOfCards;
		cardAmounts[dCol - 1] += numberOfCards;
		
		// changes visibility to true for card 1 row higher to card you picked
		if(sRow > 1) {
			visibility[sRow - 2][sCol - 1] = true;
		}
	}

	public static void moveToEndStack() {
		// mechanic behind moving card to final stack
		
		// used for moving card from board to final stack
		if (sCol >= 1 && sCol <= 7) {
			endStacks[dRow] = playTable[sRow - 1 ][sCol - 1];
			playTable[sRow - 1 ][sCol - 1] = 0;
			cardAmounts[sCol - 1] -= numberOfCards;
			if(sRow > 1) {
				visibility[sRow - 2][sCol - 1] = true;
			}
		}
		
		// used for moving card from additional stack to final stack
		if (sCol == 9) {
			
			// checks if stack is empty
			if (stackSize == 0) {
				
				printEmptyStackMessage();
				playGame();
			}
			
			// if this is last card in additional stack, take card and put "blank" card
			if (stackSize == 1) {
				endStacks[dRow] = stackList.get(stackIterator);
				stackList.remove(stackIterator);
				stackSize -= 1;
				stackList.add(100);
				stackIterator = 0;
			}
			
			// if this isn't last card in stack, take card, and show previous card
			if (stackSize > 1) {
				endStacks[dRow] = stackList.get(stackIterator);
				stackList.remove(stackIterator);
				if (stackIterator > 0) {
					stackIterator -= 1;
				}
				stackSize -= 1;
			}
		}
	}

	public static void stackMoving() {
		// mechanic behind moving card from additional stack to board
		
		// checks if stack is empty
		if (stackSize == 0) {
			printEmptyStackMessage();
			playGame();
		}
		
		// if this is last card in additional stack, take card and put "blank" card
		if (stackSize == 1) {
			playTable[cardAmounts[dCol - 1]][dCol - 1] = stackList.get(stackIterator);
			stackList.remove(stackIterator);
			stackSize -= 1;
			cardAmounts[dCol - 1] += numberOfCards;
			stackList.add(100);
			stackIterator = 0;
		}
		
		// if this isn't last card in stack, take card, and show previous card
		if (stackSize > 1) {
			playTable[cardAmounts[dCol - 1]][dCol - 1] = stackList.get(stackIterator);
			stackList.remove(stackIterator);
			if (stackIterator > 0) {
				stackIterator -= 1;
			}
			stackSize -= 1;
			cardAmounts[dCol - 1] += numberOfCards;
		}
	}
	
	public static void endStackMoving() {
		// mechanic behind moving card from final stack to board
		
		// used for moving all cards excluding Aces
		if(isKingMovingPosible() || (isMovePosible() && card1.getValue() > 1)) {
			playTable[cardAmounts[dCol - 1]][dCol - 1] = endStacks[sRow - 1];
			endStacks[sRow - 1] -= 1;
			cardAmounts[dCol - 1] += numberOfCards;
		}
	
		// used for moving Aces
		if (isMovePosible() && card1.getValue() == 1) {
			playTable[cardAmounts[dCol - 1]][dCol - 1] = endStacks[sRow - 1];
			switch (card1.getColor()) {
				case "Hearts" -> endStacks[sRow - 1] = 104;
				case "Diamonds" -> endStacks[sRow - 1] = 103;
				case "Clubs" -> endStacks[sRow - 1] = 102;
				case "Spades" -> endStacks[sRow - 1] = 101;
			}
			cardAmounts[dCol - 1] += numberOfCards;
		}
		
	}
	
	public static void finishGame() {
		// this method adds amount of all remaining cards on board, to moves count
		
		for (int i = 0; i < cardAmounts.length; i++) {
			countMoves += cardAmounts[i];
		}
		
	}
	
}
