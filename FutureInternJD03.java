package futureinternjd02;

import java.util.Random;
import java.util.Scanner;

public class FutureInternJD03 {
	static char[][] board;

	public FutureInternJD03() {
		board = new char[3][3];
		initBoard();
	}

	void initBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = ' ';
			}
		}
	}

	static void dispBoard() {
		System.out.println("-------------");
		for (int i = 0; i < board.length; i++) {
			System.out.print("| ");
			for (int j = 0; j < board[i].length; j++) {
				System.out.print(board[i][j] + " | ");
			}
			System.out.println();
			System.out.println("-------------");
		}
	}

	static void placeMark(int row, int col, char mark) {
		if (row >= 0 && row <= 2 && col >= 0 && col <= 2) {
			board[row][col] = mark;
		} else {
			System.out.println("Invalid position");
		}
	}

	static boolean checkRowWin() {
		for (int i = 0; i <= 2; i++) {
			if (board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
				return true;
			}
		}
		return false;
	}

	static boolean checkColWin() {
		for (int j = 0; j <= 2; j++) {
			if (board[0][j] != ' ' && board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
				return true;
			}
		}
		return false;
	}

	static boolean checkDiagWin() {
		return ((board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) ||
				(board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0]));
	}

	static boolean isDraw() {
		for (int i = 0; i <= 2; i++) {
			for (int j = 0; j <= 2; j++) {
				if (board[i][j] == ' ') {
					return false;
				}
			}
		}
		return true;
	}

	static boolean checkWin() {
		return checkRowWin() || checkColWin() || checkDiagWin();
	}

	abstract static class Player {
		String name;
		char mark;

		Player(String name, char mark) {
			this.name = name;
			this.mark = mark;
		}

		abstract void makeMove();

		boolean isValidMove(int row, int col) {
			return row >= 0 && row <= 2 && col >= 0 && col <= 2 && board[row][col] == ' ';
		}
	}

	static class HumanPlayer extends Player {
		HumanPlayer(String name, char mark) {
			super(name, mark);
		}

		@Override
		void makeMove() {
			Scanner scan = new Scanner(System.in);
			int row, col;
			do {
				System.out.println("Enter the row and column");
				row = scan.nextInt();
				col = scan.nextInt();
			} while (!isValidMove(row, col));
			placeMark(row, col, mark);
		}
	}

	static class AIPlayer extends Player {
		AIPlayer(String name, char mark) {
			super(name, mark);
		}

		@Override
		void makeMove() {
			Random r = new Random();
			int row, col;
			do {
				row = r.nextInt(3);
				col = r.nextInt(3);
			} while (!isValidMove(row, col));
			placeMark(row, col, mark);
		}
	}

	public static void main(String[] args) {1
        FutureInternJD03 game = new FutureInternJD03();
        HumanPlayer p1 = new HumanPlayer("Mayuri", 'O');
        AIPlayer p2 = new AIPlayer("TAI", 'X');
        Player currentPlayer = p1;

        while (true) {
            System.out.println(currentPlayer.name + "'s turn");
            currentPlayer.makeMove();
            dispBoard();

            if (checkWin()) {
                System.out.println(currentPlayer.name + " has won");
                break;
            } else if (isDraw()) {
                System.out.println("Game is Draw");
         	break;
 		} 

            currentPlayer = (currentPlayer == p1) ? p2 : p1;
        }
    }
}
