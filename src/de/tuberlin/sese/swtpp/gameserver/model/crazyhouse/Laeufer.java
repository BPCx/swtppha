package de.tuberlin.sese.swtpp.gameserver.model.crazyhouse;

import javax.xml.validation.Validator;
import java.io.Serializable;


public class Laeufer extends Figur implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2000465999812613545L;

	public Laeufer(int x, int y, Farbe farbe) {
		super(x, y, farbe);
		// TODO Auto-generated constructor stub
		this.Symbol = "b";
		this.Schritt = 8;
	}

	@Override
	public boolean CanMove(Feld[][] board, Feld ziel) {

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
				if (board[this.posY + y_ascending*x][this.posX + x_ascending*x].hasFigur()) {
					if(x == Math.abs(x_difference) && board[this.posY+y_ascending*x][this.posX+x_ascending*x].getFigur().getFarbe() != this.getFarbe()) {
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
