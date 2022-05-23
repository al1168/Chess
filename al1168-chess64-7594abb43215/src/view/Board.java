/**
 * @author Sam Lee
 * @author Alden Lu 
 */
package view;

import java.util.Arrays;
import java.util.List;

import model.*;


public class Board {

	private static Cell[][] gameBoard = null;
/**
 * creates a board	
 */
	public Board() {
		init_board();
	}
	/**
	 * 
	 * @return the Cell[][] gameBoard
	 */
	public Cell[][] getBoard() {
		return gameBoard;
	}
	/**
	 * 
	 * @param i array index i
	 * @param j array index j
	 * @return Cell object
	 * 
	 */
	public Cell getCell(int i, int j) {
		return gameBoard[i][j];
	}
	/**
	 * 
	 * @param row to init
	 * @param color of pawn
	 */
	private void init_Pawns(int row, String color) {
		for (Cell x : gameBoard[row]) {
			Pawn p = new Pawn(color);
			x.setPiece(p);
		}
	}
	/**
	 *  init the default chess board
	 */
	private void init_board() {
		// TODO Auto-generated method stub
		gameBoard = new Cell[8][8];

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				gameBoard[i][j] = new Cell(i, j, null);
//				System.out.print(i+""+j+" ");
			}
//			System.out.println();
		}
		init_Pawns(1, "Black");
		init_Pawns(6, "White");
		init_sym("Black");
		init_sym("White");

	}
	/**
	 * 
	 * @param color of the piece
	 * inits the entire back row
	 */
	private void init_sym(String color) {
		// TODO Auto-generated method stub
		int rank = 0;
		if (color.equals("White")) {
			rank = 7;
		}
		int i = 0;
		Rook rl = new Rook(color);
		Rook rr = new Rook(color);
		gameBoard[rank][i].setPiece(rl);
		gameBoard[rank][8 - 1 - i].setPiece(rr);
		i++;
		Knight kl = new Knight(color);
		Knight kr = new Knight(color);
		gameBoard[rank][i].setPiece(kl);
		gameBoard[rank][8 - i - 1].setPiece(kr);
		i++;
		Bishop bl = new Bishop(color);
		Bishop br = new Bishop(color);
		gameBoard[rank][i].setPiece(bl);
		gameBoard[rank][8 - i - 1].setPiece(br);
		i++;
		King k = new King(color);
		Queen q = new Queen(color);
		gameBoard[rank][i].setPiece(q);
		gameBoard[rank][8 - i - 1].setPiece(k);
	}
	/**
	 * prints the board
	 */
	public void printBoard() {
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
}
