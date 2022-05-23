/**
 * @author Sam Lee
 * @author Alden Lu 
 */
package model;

import view.*;

public class Pawn extends Piece {
	char type = 'P';

	public Pawn(String color) {
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

		if (start.getPiece().getColor().equals("White")) {
			if (start.getX() == 6 && start.getX() - end.getX() < 3 && start.getX() - end.getX() > 0
					&& start.getY() == end.getY())
				return true; // first move is 2 or 1
			else if (start.getX() - end.getX() == 1 && Math.abs(start.getY() - end.getY()) == 1 && !end.isEmpty())
				return true; // can kill diagonal piece if exists enemy piece
			else if (start.getX() - end.getX() == 1 && end.getY() == start.getY() && end.isEmpty())
				return true; // can move up 1 if empty
		} else if (start.getPiece().getColor().equals("Black")) {
			if (start.getX() == 1 && end.getX() - start.getX() < 3 && end.getX() - start.getX() > 0
					&& start.getY() == end.getY())
				return true; // first move is 2 or 1
			else if (end.getX() - start.getX() == 1 && Math.abs(start.getY() - end.getY()) == 1 && !end.isEmpty())
				return true; // can kill diagonal piece if exists enemy piece
			else if (end.getX() - start.getX() == 1 && end.getY() == start.getY() && end.isEmpty())
				return true; // can move up 1 if empty
		} else
			return false;
		return false;
	}

	public String toString() {
		return super.toString() + type;
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

		int xdif = Math.abs(end.getX() - start.getX());
		int ydif = Math.abs(end.getY() - start.getY());
		// en passant
		if (start.getPiece().getColor().equals("White") && xdif == 1 && ydif == 1
				&& !gameBoard[end.getX() + 1][end.getY()].isEmpty() && gameBoard[end.getX() + 1][end.getY()].getPiece().type=='P') {
			System.out.println("HI");
			if (gameBoard[end.getX() + 1][end.getY()].getPiece().enpassable && !gameBoard[end.getX() + 1][end.getY()].getPiece().getColor().equals(this.getColor())
					&& gameBoard[end.getX() + 1][end.getY()].getPiece().turnnum + 1 == this.turnnum) {
				gameBoard[end.getX() + 1][end.getY()].setPiece(null);
				gameBoard[end.getX()][end.getY()].setPiece(this);
				gameBoard[start.getX()][start.getY()].setPiece(null);
				return true;
			}
		} else if (start.getPiece().getColor().equals("Black") && xdif == 1 && ydif == 1
				&& !gameBoard[end.getX() - 1][end.getY()].isEmpty() && gameBoard[end.getX() - 1][end.getY()].getPiece().type=='P') {
			if (gameBoard[end.getX() - 1][end.getY()].getPiece().enpassable && !gameBoard[end.getX() - 1][end.getY()].getPiece().getColor().equals(this.getColor())
					&& gameBoard[end.getX() - 1][end.getY()].getPiece().turnnum + 1 == this.turnnum) {
				gameBoard[end.getX() - 1][end.getY()].setPiece(null);
				gameBoard[end.getX()][end.getY()].setPiece(this);
				gameBoard[start.getX()][start.getY()].setPiece(null);
				return true;
			}
		}

		// promotion
		if (isValidMove(start, end, gameBoard) && end.getX() == 0 
				&& start.getPiece().getColor().equals("White")) {
			switch (promote) {
			case 'N':
				Knight k = new Knight("White");
				end.setPiece(k);
				break;
			case 'B':
				Bishop b = new Bishop("White");
				end.setPiece(b);
				break;
			case 'R':
				Rook r = new Rook("White");
				end.setPiece(r);
				break;
			case 'Q':
				Queen q = new Queen("White");
				end.setPiece(q);
				break;
				
			}
			gameBoard[start.getX()][start.getY()].setPiece(null);
			return true;
		} else if (isValidMove(start, end, gameBoard) && end.getX() == 7
				&& start.getPiece().getColor().equals("Black")) {
			switch (promote) {
			case 'N':
				Knight k = new Knight("Black");
				end.setPiece(k);
				break;
			case 'B':
				Bishop b = new Bishop("Black");
				end.setPiece(b);
				break;
			case 'R':
				Rook r = new Rook("Black");
				end.setPiece(r);
				break;
			case 'Q':
				Queen q = new Queen("Black");
				end.setPiece(q);
				break;
			}
			gameBoard[start.getX()][start.getY()].setPiece(null);
			return true;
		}

		if (isValidMove(start, end, gameBoard)) {
			if (certain == 1) {
				gameBoard[end.getX()][end.getY()].setPiece(this);
				gameBoard[start.getX()][start.getY()].setPiece(null);
				if(sim==0) {
				moved = true;
				movecount++;
				}
			}

			if (movecount == 1) {
				enpassable = true;
			} else
				enpassable = false;
			return true;
		} else
			return false;
	}

	@Override
	public void forcemove(Cell start, Cell end, Cell[][] gameBoard) {
		// TODO Auto-generated method stub

	}
}
