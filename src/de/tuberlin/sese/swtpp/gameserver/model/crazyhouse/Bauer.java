package de.tuberlin.sese.swtpp.gameserver.model.crazyhouse;

import java.io.Serializable;

public class Bauer extends Figur implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6422444067714926244L;

	private boolean startPosition = true;
	
	public Bauer(int x, int y, Farbe farbe) {
		super(x, y, farbe);
		this.Symbol = "p";
		this.Schritt = 2;
		this.startPosition = true;
	}

	@Override
	public boolean CanMove(Feld[][] board, Feld ziel) {
		// TODO Auto-generated method stub

		int x_difference = ziel.getPosX() - this.posX;
		int y_difference = ziel.getPosY() - this.posY;
		if (validMove(board, ziel, x_difference, y_difference)) {
			return true;
		}
		return false;
	}

	/* TODO reimplement function for the king?
	public boolean CanMove(Feld[][] board, Feld ziel) {

		int x_difference = ziel.getPosX() - this.posX;
		int y_difference = ziel.getPosY() - this.posY;
		if (!validMove(board, ziel, x_difference, y_difference)) {
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
	}*/

	public boolean validMove(Feld[][] board, Feld ziel, int x_difference, int y_difference) {
		if (y_difference == 1) {
			if (Math.abs(x_difference) == 1) { //Attack 1 step to the right or left
				if (board[ziel.getPosY()][ziel.getPosX()].getFigur().getFarbe() != this.getFarbe()) {
					return true;
				}
				return false;
			}
			if (x_difference == 0) { //Move 1 step forward
				if (!board[ziel.getPosY()][ziel.getPosX()].hasFigur()) {
					return true;
				}
				return false;
			}
		}
		if (y_difference == 2 && x_difference == 0 && isStartPosition()) { //Move 2 steps forward
			if (!board[ziel.getPosY()+1][ziel.getPosX()].hasFigur() && !board[ziel.getPosY()+2][ziel.getPosX()].hasFigur()) {
				return true;
			}
		}
		return false;
	}

	public boolean isStartPosition() {
		return startPosition;
	}

	public void setStartPosition(boolean startPosition) { //TODO use function
		this.startPosition = startPosition;
		this.Schritt = 1;
	}
}
