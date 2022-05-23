/**
 * @author Sam Lee
 * @author Alden Lu 
 */
package view;

import model.Piece;

public class Cell {
	private Piece piece =null;
	private int x;
	private int y;
/**
 * 
 * @param x coordinate
 * @param y coordinate
 * @param piece to set at the cell
 */
	public Cell(int x, int y, Piece piece) {
		this.piece = piece;
		this.setX(x);
		this.setY(y);
	}
	/**
	 * 
	 * @return if cell has a piece or not
	 */
	public boolean isEmpty() {
		
		if(piece==null)return true;
		
		return false;
	}
	/**
	 * 
	 * @return x coordinate of the cell
	 */
	public int getX() {
		return x;
	}
	/**
	 * 
	 * @param x value to set cell to
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * 
	 * @return y coordinate of cell
	 */
	public int getY() {
		return y;
	}
	/**
	 * 
	 * @param y coordinate to set 
	 */
	public void setY(int y) {
		this.y = y;
	}
	/**
	 * 
	 * @return piece object of cell
	 */
	public Piece getPiece()
	{
		return this.piece;
	}
	/**
	 * 
	 * @param piece to set in cell
	 */
	public void  setPiece(Piece piece) {
		this.piece = piece;
	}
	@Override
	/**
	 * @return String 
	 * 
	 * return string containing x,y and piece
	 */
	public String toString() {
		String pieceStr ="";
		if(piece!=null) {
			pieceStr = piece.toString();
		}
		return ""+getX()+","+""+getY()+","+pieceStr;
	}
}
