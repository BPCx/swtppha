package de.tuberlin.sese.swtpp.gameserver.model.crazyhouse;

import java.io.Serializable;

public class Springer extends Figur implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5637507646735728534L;

	public Springer(int x, int y, Farbe farbe) {
		super(x, y, farbe);
		this.Symbol = "n";
		this.Schritt = 2;//nicht geradlinig....
	}

	@Override
	public boolean CanMove(Feld[][] board, Feld ziel) {
		// TODO Auto-generated method stub
		int x_difference = ziel.getPosX() - this.posX;
		int y_difference = ziel.getPosY() - this.posY;

		if (!validMove(x_difference, y_difference)) {
			return false;
		}
		else if (!board[ziel.getPosX()][ziel.getPosY()].hasFigur()) {
			return true;
		} else if (!board[ziel.getPosX()][ziel.getPosY()].getFigur().getFarbe().name().equals(this.getFarbe().name())) {
			
			return true;
		}
		return false;
	}
	public boolean validMove (int x_difference, int y_difference) {
		if (Math.abs(x_difference) == 2 && Math.abs(y_difference) == 1) {
			return true;
		}
		if (Math.abs(x_difference) == 1 && Math.abs(y_difference) == 2) {
			return true;
		}
		return false;
	}
}
