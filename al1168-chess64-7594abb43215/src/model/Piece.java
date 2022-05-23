/**
 * @author Sam Lee
 * @author Alden Lu 
 */
package model;

import view.*;

public abstract class Piece {
	private String color;

	public boolean moved = false;
	public int movecount = 0;
	public int turnnum = 0;
	public boolean enpassable = false;
	public char promote = 'Q';
	public char type = 'P';


	public Piece(String color) {
		this.color = color;

	}
	/**
	 * @param start Starting cell
	 * @param end end cell
	 * @param gameBoard Board
	 * @return boolean 
	 * return boolean based on whether the piece have the ability to make the move
	 */
	public abstract boolean isValidMove(Cell start, Cell end, Cell[][] gameBoard);



	//public abstract boolean move(Cell start, Cell end, Cell[][] gameBoard);

	
	//public abstract boolean move(Cell start, Cell end, Cell[][] gameBoard, int trial);
	/**
	 * @param start start cell
	 * @param end end cell
	 * @param gameBoard board
	 * @param certain indicator
	 * @param sim indicator
	 * 
	 * Test if from start cell to end cell is valid
	 * certain indicates to proceed with the movement
	 * sim indicates whether it is the simulator calling the method or the actual board
	 * 
	 */
	public abstract boolean move(Cell start, Cell end, Cell[][] gameBoard, int certain, int sim);

	
	public abstract void forcemove(Cell start, Cell end, Cell[][] gameBoard);


	public String getColor() {
		return color;
	}

	public String toString() {
		return color.substring(0, 1).toLowerCase();
	}

}
