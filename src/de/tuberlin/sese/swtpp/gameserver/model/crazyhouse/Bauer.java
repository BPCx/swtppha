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


	public boolean validMove(Feld[][] board, Feld ziel, int y_difference, int x_difference) { 
		if ((y_difference == -1 && this.Farbe.name().equals("Weiss")) || (y_difference == 1 && this.Farbe.name().equals("Schwarz"))) {
			if (Math.abs(x_difference) == 1) { //Attack 1 step to the right or left
				if (!board[ziel.getPosX()][ziel.getPosY()].getFigur().getFarbe().name().equals(this.getFarbe().name())) {
					if(this.startPosition) this.setStartPosition(false);
					return true;
				}
				return false;
			}
			if (x_difference == 0) { //Move 1 step forward
				if (!board[ziel.getPosX()][ziel.getPosY()].hasFigur()) {
					if(this.startPosition) this.setStartPosition(false);
					return true;
				}
				return false;
			}
		}
		if ((y_difference == 2 && x_difference == 0 && isStartPosition() && this.Farbe.name().equals("Schwarz"))) { //Move 2 steps forward
			if (!board[ziel.getPosX()+1][ziel.getPosY()].hasFigur() && !board[ziel.getPosX()+2][ziel.getPosY()].hasFigur()) {
				if(this.startPosition) this.setStartPosition(false);
				return true;
			}
		} else 	if ((y_difference == -2 && x_difference == 0 && isStartPosition() && this.Farbe.name().equals("Weiss"))) {
			this.setStartPosition(false);
			return true;
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
