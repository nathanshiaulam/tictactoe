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

	}

	private boolean gameOver(String board) {
		return false;
	}

	private void legDay() {

	}
	public static void main(String[] argv) {
	}
}