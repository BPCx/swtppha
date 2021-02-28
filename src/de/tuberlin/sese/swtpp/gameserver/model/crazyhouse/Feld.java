package de.tuberlin.sese.swtpp.gameserver.model.crazyhouse;

import java.io.Serializable;

public class Feld implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4126515171326447491L;
	private int posX;
	private int posY;
	
	private Figur figur = null;
	
	public Feld(int posx, int posy, Figur fig) {
		posX = posx;
		posY = posy;
		figur = fig;
	}
	
	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}
	
	public Figur getFigur() {
		return figur;
	}

	public boolean hasFigur() {
		if (figur != null)
			return true;
		return false;
	}

	public void setFigur(Figur figur) {
		this.figur = figur;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
	
}
