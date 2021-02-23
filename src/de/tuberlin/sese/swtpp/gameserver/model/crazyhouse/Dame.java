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
		return false;
	}
}
