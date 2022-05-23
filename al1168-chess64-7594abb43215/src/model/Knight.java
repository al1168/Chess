/**
 * @author Sam Lee
 * @author Alden Lu 
 */
package model;

import view.*;

public class Knight extends Piece {
	/**
	 * 
	 * @param color Color of init piece
	 */
	
	char type = 'N';
	public Knight(String color) {
		super(color);

	}
	/**
	 * @param start Starting cell
	 * @param end end cell
	 * @param gameBoard Board
	 * @return boolean 
	 * return boolean based on whether the piece have the ability to make the move
	 */
	@Override
	public boolean isValidMove(Cell start, Cell end, Cell[][] gameBoard) {
		// TODO Auto-generated method stub
		if(!end.isEmpty() && start.getPiece().getColor().equals(end.getPiece().getColor())) return false;
		
		int x = Math.abs(start.getX() - end.getX());
		int y = Math.abs(start.getY() - end.getY());
		
		return x*y==2;
		
	}

	@Override
	public String toString() {
		return super.toString() + "N";
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
	@Override
	public boolean move(Cell start, Cell end, Cell[][] gameBoard, int certain,int sim) {
		
		if (isValidMove(start,end,gameBoard)) {
			if(certain==1) {
			gameBoard[end.getX()][end.getY()].setPiece(this);
			gameBoard[start.getX()][start.getY()].setPiece(null);
			if(sim==0) {
			moved = true;
			}
}	
			return true;
		} else return false;
	}

	@Override
	public void forcemove(Cell start, Cell end, Cell[][] gameBoard) {
		// TODO Auto-generated method stub
		
	}

}
