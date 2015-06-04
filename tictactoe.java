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
			potatoes.put(ternaryNum, 1.0);
		}
		legDay();
	}

	private String response(String board) {
		double max = 0.0;
		int count = 0;
		for (int i = 0; i < 9; i++) {
			double weight = 0.0;
			if (board.charAt(i) == '0') {
				weight = potatoes.get(board.substring(0,i) 
											 + '2' + board.substring(i + 1));
			}
			if (weight >= max) {
				if (weight > max)
					count = 1;
				else 
					count++;
				max = weight;
			}
		}
		if (max == 0.0)
			return "111111111";

		Random rand = new Random();

		int choice = rand.nextInt(count);

		int number = 0;
		for (int i = 0; i < 9; i++) {
			if (board.charAt(i) == '0') {
				String newBoard = board.substring(0,i) + '2' + 
								  		board.substring(i + 1);
				if (potatoes.get(newBoard) == max) {
					if (number == choice)
						return newBoard;
					number++;
				}
			}
		}
		return "999999999"; 
	}

	private void prompt(String board) {
		StdOut.println(board.substring(0,3));
		StdOut.println(board.substring(3,6));
		StdOut.println(board.substring(6,9));
		StdOut.println("Your move, bitch.");
	}

	private boolean gameOver(String board) {
		if (board.substring(0, 3).equals("111") || board.substring(0, 3).equals("222") ||
			board.substring(3,6).equals("111") || board.substring(3,6).equals("222") ||
			board.substring(6,9).equals("111") || board.substring(6,9).equals("222")) {	
			return true;
		}
		if ((board.charAt(0) == '1' && board.charAt(4) == '1' && board.charAt(8) == '1') ||
			(board.charAt(0) == '1' && board.charAt(3) == '1' && board.charAt(6) == '1') ||
			(board.charAt(1) == '1' && board.charAt(4) == '1' && board.charAt(7) == '1') ||
			(board.charAt(2) == '1' && board.charAt(5) == '1' && board.charAt(8) == '1') ||
			(board.charAt(2) == '1' && board.charAt(4) == '1' && board.charAt(6) == '1')) {
			return true;
		}
		if ((board.charAt(0) == '2' && board.charAt(4) == '2' && board.charAt(8) == '2') ||
			(board.charAt(0) == '2' && board.charAt(3) == '2' && board.charAt(6) == '2') ||
			(board.charAt(1) == '2' && board.charAt(4) == '2' && board.charAt(7) == '2') ||
			(board.charAt(2) == '2' && board.charAt(5) == '2' && board.charAt(8) == '2') ||
			(board.charAt(2) == '2' && board.charAt(4) == '2' && board.charAt(6) == '2')) {
			return true;
		}
		for (int i = 0; i < board.length(); i++) {
			if (board.charAt(i) == '0') {
				return false;
			}
			if (i == board.length() - 1) {
				return true;
			}
		}
		return false;
	}

	private void legDay() {

	}
	public static void main(String[] argv) {
		TicTacToe newGame = new TicTacToe();
		String boardState = "000000000";
		int index;
		while (true) {
			newGame.prompt(boardState);
			index = StdIn.readInt();
			String newState = boardState.substring(0,index) + '1' + 
									boardState.substring(index + 1);
			boardState = newGame.response(newState);
			if (newGame.gameOver(boardState)) {
				StdOut.println("Game Over, let's play again!");
				boardState = "000000000";
			}
		}
	}
}