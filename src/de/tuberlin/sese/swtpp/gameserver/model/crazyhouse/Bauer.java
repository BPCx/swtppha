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
		return false;
	}

	public boolean isStartPosition() {
		return startPosition;
	}

	public void setStartPosition(boolean startPosition) {
		this.startPosition = startPosition;
		this.Schritt = 1;
	}
}
