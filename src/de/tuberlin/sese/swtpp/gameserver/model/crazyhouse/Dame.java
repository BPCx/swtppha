package de.tuberlin.sese.swtpp.gameserver.model.crazyhouse;

import java.io.Serializable;

public class Dame extends Figur implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2519664888341693283L;

	public Dame(int x, int y, Farbe farbe) {
		super(x, y, farbe);
		// TODO Auto-generated constructor stub
		this.Symbol = "q";
		this.Schritt = 8;
	}

	@Override
	public boolean CanMove(Feld[][] board, Feld ziel) {
		// TODO Auto-generated method stub
		if(checkKing(board, ziel)) {
			return true;
		}
		if(checkRook(board, ziel)) {
			return true;
		}
		if(checkBishop(board, ziel)) {
			return true;
		}
		return false;
	}
	public boolean checkKing(Feld[][] board, Feld ziel) {

		int x_difference = ziel.getPosX() - this.posX;
		int y_difference = ziel.getPosY() - this.posY;
		if (!validMove2(x_difference, y_difference)) {
			return false;
		}
		if (!board[ziel.getPosX()][ziel.getPosY()].hasFigur()) {
			return true;
		} else if (!board[ziel.getPosX()][ziel.getPosY()].getFigur().getFarbe().name().equals(this.getFarbe().name())) {
			return true;
		}
		return false;
	}
	public boolean validMove2(int x_difference, int y_difference) {
		if (Math.abs(x_difference) == 0 && Math.abs(y_difference) == 0) {
			return false;
		}
		if (Math.abs(x_difference) > 1 || Math.abs(y_difference) > 1) {
			return false;
		}
		return true;
	}
	public boolean checkRook(Feld[][] board, Feld ziel) {
		
		int x_difference = ziel.getPosX() - this.posX;
		int y_difference = ziel.getPosY() - this.posY;
		if(!((x_difference == 0 && y_difference != 0) || (x_difference != 0 && y_difference == 0)))
			return false;
		
		int x_ascending = -1;
		int y_ascending = -1;
		
		if (x_difference > 0) {
			x_ascending = 1;
		}
		if (y_difference > 0) {
			y_ascending = 1;
		}
		if (y_difference == 0) {
			for (int i = 1; i <= Math.abs(x_difference); i++) {
				if (board[this.posX + x_ascending*i][this.posY].hasFigur()) {
					if(i == Math.abs(x_difference) && !board[this.posX+x_ascending*i][this.posY].getFigur().getFarbe().name().equals(this.getFarbe().name())) {
						return true; // return true if the target position has an opponent's piece.
					} else {
						return false; // return false if our piece is in the target position or any piece is along the way.
		}}}}
		
		if (x_difference == 0) {
			for (int i = 1; i <= Math.abs(y_difference); i++) {
				if (board[this.posX][this.posY+ y_ascending*i].hasFigur()) {
					if(i == Math.abs(y_difference) && !board[this.posX][this.posY+ y_ascending*i].getFigur().getFarbe().name().equals(this.getFarbe().name())) {
						return true; // return true if the target position has an opponent's piece.
					} else {
						return false; // return false if our piece is in the target position or any piece is along the way.
		}}}}
		return true;
	}
	public boolean checkBishop(Feld[][] board, Feld ziel) {

		int x_difference = ziel.getPosX() - this.posX;
		int y_difference = ziel.getPosY() - this.posY;
		int x_ascending = -1;
		int y_ascending = -1;

		if (validMove(x_difference, y_difference)) {
			if (x_difference > 0) {
				x_ascending = 1;
			}
			if (y_difference > 0) {
				y_ascending = 1;
			}
			for (int x = 1; x <= Math.abs(x_difference); x++) { //Moves through the diagonal towards the target position and checks if anything is in the way
				if (board[this.posX + x_ascending*x][this.posY + y_ascending*x].hasFigur()) {
					if(x == Math.abs(x_difference) && !board[this.posX+x_ascending*x][this.posY+y_ascending*x].getFigur().getFarbe().name().equals(this.getFarbe().name())) {
						return true; // return true if the target position has an opponent's piece.
					} else {
						return false; // return false if our piece is in the target position or any piece is along the way.
					}
				}
			}
			return true; // return true if no pieces are in the way.
		}
		return false; // Non-diagonal move returns a false
	}
	public boolean validMove (int x_difference, int y_difference) {
		if (x_difference == 0 || y_difference == 0) {
			return false;
		}
		 if (Math.abs(x_difference) == Math.abs(y_difference)) {
		 	return true;
		}
		 return false;
	}
}
