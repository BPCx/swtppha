package de.tuberlin.sese.swtpp.gameserver.model.crazyhouse;

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
		// TODO Auto-generated method stub
		return false;
	}
}
