import java.util.*

public class TicTacToe {


	public TicTacToe() {

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