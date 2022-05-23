/**
 * @author Sam Lee
 * @author Alden Lu 
 */
package model;

import view.*;

public class Bishop extends Piece {
	/**
	 * 
	 * @param color Color of init piece
	 */
	 
	 char type = 'B';
	
	public Bishop(String color) {
		super(color);
		// TODO Auto-generated constructor stub
	}

	//@Override
	/**
	 * @param start Starting cell
	 * @param end end cell
	 * @param gameBoard Board
	 * @return boolean 
	 * return boolean based on whether the piece have the ability to make the move
	 */
	public boolean isValidMove(Cell start, Cell end, Cell[][] gameBoard) {
		// TODO Auto-generated method stub

		if (!end.isEmpty() && start.getPiece().getColor().equals(end.getPiece().getColor()))
			return false;

		int x = Math.abs(start.getX() - end.getX());
		int y = Math.abs(start.getY() - end.getY());		
		return x==y;
	}

	
	@Override
	public String toString() {
		return super.toString() + "B";
	}
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
	public boolean move(Cell start, Cell end, Cell[][] gameBoard, int certain,int sim) {
		int x = start.getX();
		int y = start.getY();
		int xdif = end.getX() - start.getX();
		int ydif = end.getY() - start.getY();

		if (isValidMove(start, end, gameBoard)) {

			while (x != end.getX() && y != end.getY()) { // checks path
				if (!gameBoard[x][y].isEmpty() && !start.getPiece().getColor().equals(gameBoard[x][y].getPiece().getColor())) {
					return false;
				}
				if (xdif < 0) { // goes up
					if (ydif < 0) { // goes left
						x--;
						y--;
					} else if (ydif > 0) { // goes right
						x--;
						y++;
					}
				} else if (xdif > 0) { // goes down
					if (ydif < 0) { // goes left
						x++;
						y--;
					} else if (ydif > 0) { // goes right
						x++;
						y++;
					}
				}

				if (!gameBoard[x][y].isEmpty() && start.getPiece().getColor().equals(gameBoard[x][y].getPiece().getColor())) {
					return false;
				}
			}
			if(certain==1) {
			gameBoard[end.getX()][end.getY()].setPiece(this);
			gameBoard[start.getX()][start.getY()].setPiece(null);
			if(sim==0) {
			moved = true;
			}

			}

			return true;
		} else
			return false;
	}

	@Override
	/**
	 * @param start start cell
	 * @param end end cell
	 * @param gameBoard board
	 */
	public void forcemove(Cell start, Cell end, Cell[][] gameBoard) {
		// TODO Auto-generated method stub
		
	}

}
