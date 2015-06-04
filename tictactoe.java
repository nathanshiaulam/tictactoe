import java.util.*;

public class TicTacToe {

	HashMap<String, Double> potatoes;

	public TicTacToe() {
		potatoes = new HashMap<String, Double>();
		for (int i = 0; i < 19683; i++) {
			String ternaryNum = "";
			int number = i;
			while (number != 0) {
				int digit = number % 3;
				number /= 3;
				ternaryNum = Integer.toString(digit) + ternaryNum;
			}
			while (ternaryNum.length() < 9) {
				ternaryNum = "0" + ternaryNum;
			}
			System.out.println(ternaryNum);
			potatoes.put(ternaryNum, 1.0);
		}
		legDay();
		System.out.println(potatoes.get("100020100"));
	}

	private String response(String board) {
		return ""; 
	}

	private void prompt(String board) {
		StdOut.println(board.substring(0,3));
		StdOut.println(board.substring(3,6));
		StdOut.println(board.substring(6,9));
		StdOut.println("Your move, bitch.");
	}

	private boolean gameOver(String board) {
		return false;
	}

	private void legDay() {

	}
	public static void main(String[] argv) {
		TicTacToe newGame = new TicTacToe();
		String boardState = "000000000";
		int index;
		while (true) {
			prompt(boardState);
			index = StdIn.readInt();
			String newState = boardState.substring(0,index) + '1' + 
									boardState.substring(index + 1);
			boardState = response(newState);
			if (gameOver(boardState)) {
				StdOut.println("Game Over, let's play again!");
				boardState = "000000000";
			}
		}
	}
}