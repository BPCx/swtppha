package de.tuberlin.sese.swtpp.gameserver.model.crazyhouse;

import java.io.Serializable;

public class Koenig extends Figur implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4756643950324782264L;
	
	public Koenig(int x, int y, Farbe farbe) {
		super(x, y, farbe);
		// TODO Auto-generated constructor stub
		this.Symbol = "k";
		this.Schritt = 1;
	}

	@Override

	public boolean CanMove(Feld[][] board, Feld ziel) {

		int x_difference = ziel.getPosX() - this.posX;
		int y_difference = ziel.getPosY() - this.posY;
		if (!validMove(x_difference, y_difference)) {
			return false;
		}
		if (!board[ziel.getPosY()][ziel.getPosX()].hasFigur()) {
			return true;
		}
		if (board[ziel.getPosY()][ziel.getPosX()].getFigur().getFarbe() != this.getFarbe()) {
			return true;
		}
		return false;
	}
	public boolean validMove(int x_difference, int y_difference) {
		if (Math.abs(x_difference) == 0 && Math.abs(y_difference) == 0) {
			return false;
		}
		if (Math.abs(x_difference) > 1 || Math.abs(y_difference) > 1) {
			return false;
		}
		return true;
	}
}
