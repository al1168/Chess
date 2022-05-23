/**
 * @author Sam Lee
 * @author Alden Lu 
 */
package view;

import java.util.Scanner;

import model.King;
import model.Knight;
import model.Piece;

public class Game {
	/**
	 * Starts the game takes care of user inputs
	 */
	public static void start() {
		Board gameBoard = new Board();
		boolean gameOver = false;
		boolean whiteTurn = true;
		boolean draw = false;
		Scanner moveInput = new Scanner(System.in);
		int turn = 0;
		char prom = 'Q';

		boolean black = false;
		boolean valid_inputs = false;
		while (!gameOver) {
			String u = (black) ? "Black" : "White";
			String z = (black) ? "White" : "Black";
			// REMEMBER DIS SHIT
			if (curr_check(u, gameBoard)) {
				System.out.println("Check");
				if (checkmate(gameBoard, z)) {
					gameOver = true;
					System.out.println(z + " wins");
					break;
				}
			}
			gameBoard.printBoard();
			if (black) {
				System.out.print("\nBlack's move: ");
				black = false;
			} else {
				black = true;
				System.out.print("\nWhite's move: ");
			}

			String input = moveInput.nextLine().trim();
			System.out.println();
			if (draw && input.contains("draw")) {
				System.out.println("draw");
				gameOver=true;
				break;
			} else draw = false;
			if (input.contains("draw?")) {
				
				draw = true;
				continue;
			}
			if (input.equals("resign")) {

				String d = "";
				d = (black) ? "Black wins" : "White wins";
				System.out.println(d);
				break;
			}
			valid_inputs = checkinputs(input);
			// System.out.println(valid_inputs);
			if (!valid_inputs) {
				System.out.println("Illegal move, try again");
				black = !black;
				continue;
			}

			if (input.length() == 7) {
				prom = input.charAt(6);
				input = input.substring(0, 5);
			}

			if (input.length() == 5) {
				String curr_color = (black) ? "White" : "Black";
				String input2 = input;
				String[] arr = input2.split(" ");
				String startpoint = FRtoArr(arr[0]);
				String endpoint = FRtoArr(arr[1]);
				int startx = Integer.parseInt("" + startpoint.charAt(0));
				int starty = Integer.parseInt("" + startpoint.charAt(1));
				int endx = Integer.parseInt("" + endpoint.charAt(0));
				int endy = Integer.parseInt("" + endpoint.charAt(1));

				Cell start = gameBoard.getCell(startx, starty);
				if (start.getPiece() == null) {
					System.out.println("Illegal move, try again");
					black = !black;
					continue;
				}
				Cell end = gameBoard.getCell(endx, endy);

//				System.out.println(start.toString());
//				System.out.println(end.toString());

				boolean incheck = checked(curr_color, gameBoard, startx, starty, endx, endy);
				// System.out.println("Cause Check:"+incheck);
				

				if (incheck) {
					black = !black;
					System.out.println("Illegal move, try again");
					continue;
				}

				turn++;
				start.getPiece().turnnum = turn;
				start.getPiece().promote = prom;

				boolean b = false;

				if (black && start.getPiece().getColor().equals("White")) {
					b = start.getPiece().move(start, end, gameBoard.getBoard(), 1, 0);
				} else if (!black && !start.getPiece().getColor().equals("White")) {
					b = start.getPiece().move(start, end, gameBoard.getBoard(), 1, 0);
				}

				if (b == false) {
					System.out.println("Illegal move, try again");
					turn--;
					black = !black;
				}
			}
		}
		//System.out.println("Thats all folks");
		moveInput.close();
	}

	/**
	 * 
	 * @param inputs user inputed String
	 * @return boolean
	 * 
	 *         takes user string and returns if it is a valid input
	 */
	public static boolean checkinputs(String input) throws NumberFormatException {
		String pieces = "RNBQKP";
		if (input.length() > 7 || input.length() < 5)
			return false;
		String[] move = input.split(" ");
		if (move[0].length() != 2 || move[1].length() != 2)
			return false;
		String first = move[0];
		String second = move[1];
		String third = "";
		if (move.length == 3) {
			third = move[2];
		}
		if (!check(first) || !check(second))
			return false;
		if (!third.equals("")) {
			if (!pieces.contains(third))
				return false;
		}
		return true;
	}

	/**
	 * 
	 * @param gameBoard
	 * @param d
	 * @return boolean checks if you are in checkmate
	 */
	public static boolean checkmate(Board gameBoard, String d) {

		Cell king = findmyKing(d, gameBoard.getBoard());
		boolean incheck = false;
		boolean mate = true;

		for (int i = king.getX() - 1; i < king.getX() + 2; i++) {
			for (int j = king.getY() - 1; j < king.getY() + 2; j++) {
				if (i >= 0 && i < 8 && j >= 0 && j < 8) {
					if (gameBoard.getBoard()[i][j].isEmpty()) {
							gameBoard.getBoard()[i][j].setPiece(king.getPiece());
							gameBoard.getBoard()[i][j].setPiece(null);
							
							
							incheck = checked(d, gameBoard, king.getX(), king.getY(), i, j);
						
					}
				}
				if (incheck == false) {
					mate = false;
				}
			}
		}

		return mate;
	}

	/**
	 * 
	 * @param inputs
	 * @return boolean Helper method of checkinputs Checks if chess coordinate is
	 *         valid
	 */
	private static boolean check(String inputs) {
		// TODO Auto-generated method stub
		String letters = "abcdefgh";
		if (inputs.length() != 2)
			return false;
		if (!letters.contains("" + inputs.charAt(0)))
			return false;
		int x = -1;
		try {
			x = Integer.parseInt("" + inputs.charAt(1));

		} catch (NumberFormatException e) {
			return false;
		}
		if (x < 1 || x > 8)
			return false;

		return true;
	}

	/**
	 * 
	 * @param filerank chess coordinate
	 * @return String of array coordinate converts chess coordinates to array
	 *         coordinates
	 */
	private static String FRtoArr(String filerank) {
		if (filerank.length() != 2)
			return null;
		String Letters = "abcdefgh";
		char a = filerank.charAt(0);
		char bb = filerank.charAt(1);
		int y = -1;
		if (Letters.indexOf(a) < 0)
			return null;
		try {
			y = Integer.parseInt("" + bb);
			// y-=1;
		} catch (NumberFormatException e) {
			return null;
		}
		if (y > 8 || y < 0)
			return null;
		y = Math.abs(y - 8);
		return "" + y + "" + Letters.indexOf(a);
	}

	/**
	 * 
	 * @param color
	 * @param gameBoard
	 * @param startx
	 * @param starty
	 * @param endx
	 * @param endy
	 * 
	 *                  startx,starty are coordinates of start position endx, endy
	 *                  are coordinates of end position
	 * 
	 * @return boolean returns true if you are in check and false if not
	 * 
	 *         Description: creates a copy of the original board and does the
	 *         executes the move then finds the King of the current player color
	 *         After, it will iterate entire board and see if there is a piece that
	 *         is able to capture the king
	 * 
	 */
	static boolean checked(String color, Board gameBoard, int startx, int starty, int endx, int endy) {
		Cell[][] simBoard = new Cell[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				simBoard[i][j] = new Cell(i, j, null);
			}
		}
		Cell[][] origin = gameBoard.getBoard();
		for (Cell[] c : origin) {
			for (Cell x : c) {
				if (x.getPiece() != null) {
					simBoard[x.getX()][x.getY()].setPiece(x.getPiece());
				}
			}
		}

		Cell start = simBoard[startx][starty];
		if (start.getPiece() == null) {
			System.out.println("Invalid move , try again");
			return false;
		}
		Cell end = simBoard[endx][endy];
		boolean moved = start.getPiece().move(start, end, simBoard, 1, 1);
		Cell myking = findmyKing(color, simBoard);

//		System.out.println("THIS IS THE SIMBBOARD");
//		printBoard(simBoard);
//		System.out.println("THIS IS THE SIMBBOARD");

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (simBoard[i][j].getPiece() != null) {
					if (!(simBoard[i][j].getPiece().getColor().equals(color))) {

						// add indicator value for checked
						boolean x = simBoard[i][j].getPiece().move(simBoard[i][j], myking, simBoard, -1, 1);
						if (x) {
							//System.out.println("check found with piece" + i + "," + j);
							return true;
						}

					}
				}
			}
		}
		return false;
	}

	/**
	 * 
	 * @param gameBoard Prints the board to the console
	 */

	public static void printBoard(Cell[][] gameBoard) {
		String Letters = "abcdefgh";
		int vert = 8;
		if (gameBoard != null) {
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (gameBoard[i][j].getPiece() != null) {
						System.out.print(gameBoard[i][j].getPiece().toString() + " ");
					} else if ((i + j) % 2 == 0) {
						System.out.print("   ");
					} else {
						System.out.print("## ");
					}
				}
				System.out.print(vert + " ");
				vert--;
				System.out.println();
			}
			int i = 0;
			for (Cell x : gameBoard[0]) {
				System.out.print(" " + Letters.charAt(i) + " ");
				i++;
			}
			System.out.println();
		}
	}

	/**
	 * 
	 * @param d         color
	 * @param gameBoard
	 * @return boolean checks if the current player is in check
	 */
	private static boolean curr_check(String d, Board gameBoard) {
		Cell[][] simBoard = new Cell[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				simBoard[i][j] = new Cell(i, j, null);
//				System.out.print(i+""+j+" ");
			}
//			System.out.println();
		}
		Cell[][] origin = gameBoard.getBoard();
		for (Cell[] c : origin) {
			for (Cell x : c) {
				if (x.getPiece() != null) {
					simBoard[x.getX()][x.getY()].setPiece(x.getPiece());
				}
			}
		}
		Cell myking = findmyKing(d, simBoard);
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (simBoard[i][j].getPiece() != null) {
					if (!(simBoard[i][j].getPiece().getColor().equals(d))) {

						// add indicator value for checked
						boolean x = simBoard[i][j].getPiece().move(simBoard[i][j], myking, simBoard, -1, 1);
						if (x) {
							// System.out.println("check found with piece" + i + "," + j);
							return true;
						}

					}
				}
			}
		}
		return false;
	}

	/**
	 * 
	 * 
	 * @param d         color we are looking for
	 * @param gameBoard board
	 * @return Cell of object of the king
	 */
	private static Cell findmyKing(String d, Cell[][] gameBoard) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (gameBoard[i][j].getPiece() instanceof King) {
					if (gameBoard[i][j].getPiece().getColor().equals(d)) {
						return gameBoard[i][j];
					}
				}
			}
		}
		System.out.println("YOUR " + d + " King dont exist");

		return null;
	}
}
