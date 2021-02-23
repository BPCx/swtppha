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
		return false;
	}
}
