import java.util.ArrayList;
import java.util.List;

public class Steps {

	private int[][] playTable = new int[19][7];
	private boolean[][] visibility = new boolean[19][7];
	private int[] cardAmounts = new int[7];
	private int stackSize;
	private int stackIterator;
	private int[] endStacks = new int[4];
	private List<Integer> stackList = new ArrayList<>();
	
	public int getPlayTable(int i, int j) {
		return playTable[i][j];
	}
	public boolean getVisibility(int i, int j) {
		return visibility[i][j];
	}
	public int getCardAmounts(int i) {
		return cardAmounts[i];
	}
	public int getStackSize() {
		return stackSize;
	}
	public int getStackIterator() {
		return stackIterator;
	}
	public int getEndStacks(int i) {
		return endStacks[i];
	}
	public int getStackList(Integer i) {
		return stackList.get(i);
	}
	
	public void setPlayTable(int playTable, int i, int j) {
		this.playTable[i][j] = playTable;
	}
	public void setVisibility(boolean visibility, int i, int j) {
		this.visibility[i][j] = visibility;
	}
	public void setCardAmounts(int cardAmounts, int i) {
		this.cardAmounts[i] = cardAmounts;
	}
	public void setStackSize(int stackSize) {
		this.stackSize = stackSize;
	}
	public void setStackIterator(int stackIterator) {
		this.stackIterator = stackIterator;
	}
	public void setEndStacks(int endStacks, int i) {
		this.endStacks[i] = endStacks;
	}
	public void setStackList(List<Integer> stackList) {
		this.stackList.addAll(stackList);
	}
	
	
}
