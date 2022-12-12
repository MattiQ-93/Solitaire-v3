
public class Print {

	
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
		loopPrinting("\n", 1);
	}
	public static void printNoMovesMessage(){
		printLabeledMessage("You didn't make any moves.");
		loopPrinting("\n", 1);
	}
	public static void printTypedLettersMessage(){
		printLabeledMessage("Please don't put letters in me. It hurts :'(");
		loopPrinting("\n", 1);
	}
	public static void printEmptyStackMessage(){
		printLabeledMessage("Stack is empty. Please try again.");
		loopPrinting("\n", 1);
	}
	
}
