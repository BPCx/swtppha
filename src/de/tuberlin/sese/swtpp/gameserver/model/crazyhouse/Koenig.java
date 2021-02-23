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
		// TODO Auto-generated method stub
		
		return false;
	}
}
