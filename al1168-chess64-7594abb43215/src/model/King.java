/**
 * @author Sam Lee
 * @author Alden Lu 
 */
package model;

import view.*;

public class King extends Piece {
	
	char type = 'K';
	/**
	 * 
	 * @param color Color of init piece
	 */
	public King(String color) {
		super(color);
		// TODO Auto-generated constructor stub
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
		if (!end.isEmpty() && start.getPiece().getColor().equals(end.getPiece().getColor()))
			return false;
        int x = Math.abs(start.getX() - end.getX()); 
        int y = Math.abs(start.getY() - end.getY());
        if(x<=1 && y<=1) return true; 
        else return false;
		
	}
	@Override
	public String toString() {
		return super.toString()+"K";
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
	public boolean move(Cell start, Cell end, Cell[][] gameBoard,int certain,int sim) {
		//System.out.println(moved);
		//castling
		//System.out.println(moved);
		if (start.getY()==4 && start.getX()==end.getX() && !this.moved ) {
			int x = start.getX();
			int y = start.getY();
			if (end.getY()==6 && gameBoard[x][y+1].isEmpty() 
					&& gameBoard[x][y+2].isEmpty() && !gameBoard[x][y+3].getPiece().moved) {
				gameBoard[x][start.getY()+2].setPiece(this);
				gameBoard[x][start.getY()].setPiece(null);
				gameBoard[x][start.getY()+3].getPiece().forcemove(gameBoard[x][start.getY()+3], gameBoard[x][start.getY()+1], gameBoard);

				return true;
			} else if (end.getY()==1 && gameBoard[x][y-1].isEmpty() && gameBoard[x][y-2].isEmpty() 
					&& gameBoard[x][y-3].isEmpty() && !gameBoard[x][y-4].getPiece().moved) {
				gameBoard[x][start.getY()-3].setPiece(this);
				gameBoard[x][start.getY()].setPiece(null);
				gameBoard[x][start.getY()-4].getPiece().forcemove(gameBoard[x][start.getY()-4], gameBoard[x][start.getY()-2], gameBoard);

				return true;
			}
			moved = true;
		} 

		if (isValidMove(start, end, gameBoard)) {
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
