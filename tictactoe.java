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
		double max = 0.0;
		int count = 0;
		for (int i = 0; i < 9; i++) {
			double weight = 0.0;
			if (board.charAt(i) == 0) {
				weight = potatoes.get(Integer.parseInt(board.substring(0,i) 
											 + '2' + board.substring(i + 1)));
			}
			if (weight >= max) {
				if (weight > max)
					count = 1;
				else 
					count++;
				max = weight;
			}
		}
		if (max == 0)
			return "111111111";
		Random rand = new Random();

		int choice = rand.nextInt(count + 1);

		int number = 0;
		for (int i = 0; i < 9; i++) {
			if (board.charAt(i) == 0) {
				String newBoard = board.substring(0,i) + '2' + 
								  		board.substring(i + 1);
				if (potatoes.get(Integer.parseInt(newBoard)) == max) {
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