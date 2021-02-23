package de.tuberlin.sese.swtpp.gameserver.model.crazyhouse;

import static de.tuberlin.sese.swtpp.gameserver.model.crazyhouse.Farbe.Schwarz;

import java.io.Serializable;

public abstract class Figur implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2565010051741811812L;
	
	protected int posX;
	protected int posY;
	
	protected Farbe Farbe;
	protected String Symbol;
	protected int Schritt;
	
	//horiontale bewegung statisch
	//vertikale bewegung
	//diagonale bewegungen
	
	public Figur(int x, int y, Farbe farbe) {
		setPosX(x);
		setPosY(y);
		setFarbe(farbe);
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public Farbe getFarbe() {
		return Farbe;
	}

	public void setFarbe(Farbe farbe) {
		this.Farbe = farbe;
	}
	
	public boolean CanBeat(Figur figur) {
		return this.getFarbe() != figur.getFarbe();
	}
	
	public abstract boolean CanMove(Feld[][] board, Feld ziel);

	public String getString() {
		return this.Farbe == Schwarz ? Symbol.toLowerCase() : Symbol.toUpperCase();
	}
	
	
}

