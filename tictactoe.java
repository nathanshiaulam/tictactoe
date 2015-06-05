import java.util.*;

public class TicTacToe {

	HashMap<String, Double> potatoes;
	ArrayList<String> movesMade;
	public TicTacToe() {
		potatoes = new HashMap<String, Double>();
		movesMade = new ArrayList<String>();
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
		System.out.println("Max: " + max);

		Random rand = new Random();
		int choice = rand.nextInt(count);

		int number = 0;
		for (int i = 0; i < 9; i++) {
			if (board.charAt(i) == '0') {
				String newBoard = board.substring(0,i) + '2' + 
								  		board.substring(i + 1);
				if (potatoes.get(newBoard) == max) {
					if (number == choice) {
						movesMade.add(newBoard);
						return newBoard;
					}
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

	private void decayBoard(ArrayList<String> moves) {
		for (String s : moves) {
			potatoes.put(s, potatoes.get(s) * .9);
		}
	}

	private void increaseBoard(ArrayList<String> moves) {
		for (String s : moves) {
			//potatoes.put(s, potatoes.get(s) * 1.11);
		}
	}

	private boolean gameOver(String board) {
		// We win.
		if (board.substring(0, 3).equals("111") || 
			board.substring(3,6).equals("111") || 
			board.substring(6,9).equals("111")) {	
			decayBoard(movesMade);
			potatoes.put(movesMade.get(movesMade.size() - 1), 0.0); 
			System.out.print("Player 1 wins! ");
			movesMade = new ArrayList<String>();
			return true;
		}
		if ((board.charAt(0) == '1' && board.charAt(4) == '1' && board.charAt(8) == '1') ||
			(board.charAt(0) == '1' && board.charAt(3) == '1' && board.charAt(6) == '1') ||
			(board.charAt(1) == '1' && board.charAt(4) == '1' && board.charAt(7) == '1') ||
			(board.charAt(2) == '1' && board.charAt(5) == '1' && board.charAt(8) == '1') ||
			(board.charAt(2) == '1' && board.charAt(4) == '1' && board.charAt(6) == '1')) {
			decayBoard(movesMade);
			potatoes.put(movesMade.get(movesMade.size() - 1), 0.0);
			System.out.print("Player 1 wins! ");
			movesMade = new ArrayList<String>();
			return true;
		}
		// Sheldon wins.
		if (board.substring(0, 3).equals("222") ||
			board.substring(3,6).equals("222") ||
			board.substring(6,9).equals("222")) {
			increaseBoard(movesMade);
			potatoes.put(board, 10.0); 
			System.out.print("Sheldon wins! ");
			movesMade = new ArrayList<String>();
			return true;
		}
		if ((board.charAt(0) == '2' && board.charAt(4) == '2' && board.charAt(8) == '2') ||
			(board.charAt(0) == '2' && board.charAt(3) == '2' && board.charAt(6) == '2') ||
			(board.charAt(1) == '2' && board.charAt(4) == '2' && board.charAt(7) == '2') ||
			(board.charAt(2) == '2' && board.charAt(5) == '2' && board.charAt(8) == '2') ||
			(board.charAt(2) == '2' && board.charAt(4) == '2' && board.charAt(6) == '2')) {
			increaseBoard(movesMade);
			potatoes.put(board, 10.0);
			System.out.print("Sheldon wins! ");
			movesMade = new ArrayList<String>();
			return true;
		}
		for (int i = 0; i < board.length(); i++) {
			if (board.charAt(i) == '0') {
				return false;
			}
			if (i == board.length() - 1) {
				increaseBoard(movesMade);
				potatoes.put(board, 10.0); 
				movesMade = new ArrayList<String>();
				return true;
			}
		}
		return false;
	}

	private void legDay() {
		System.out.println("Please wait while Sheldon does leg day.");
		String boardState = "000000000";
		Random rand = new Random();
		for (int i = 0; i < 10000000; i++) {
			int index = rand.nextInt(9);
			while (boardState.charAt(index) != '0') {
				index = rand.nextInt(9);
			}
			String newState = boardState.substring(0,index) + '1' + 
									boardState.substring(index + 1);
			boardState = response(newState);
			if (gameOver(boardState)) {
				boardState = "000000000";
			}
		}
	}

	public static void main(String[] argv) {
		TicTacToe newGame = new TicTacToe();
		String boardState = "000000000";
		int index;
		while (true) {
			newGame.prompt(boardState);
			index = StdIn.readInt();
			System.out.println(index);
			while (boardState.charAt(index) != '0') {
				StdOut.println("Sorry, invalid input.");
				index = StdIn.readInt();
			}
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