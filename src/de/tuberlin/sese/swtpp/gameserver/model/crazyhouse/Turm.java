package de.tuberlin.sese.swtpp.gameserver.model.crazyhouse;

import java.io.Serializable;

public class Turm extends Figur implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4310216108407580899L;

	public Turm(int x, int y, Farbe farbe) {
		super(x, y, farbe);
		// TODO Auto-generated constructor stub
		this.Symbol = "r";
		this.Schritt = 8;
	}

	@Override
	public boolean CanMove(Feld[][] board, Feld ziel) {
		// TODO Auto-generated method stub
		//checken ob das zielfeld auf einer linie mit der aktuellen position liegt
		boolean result = false;
		if(!(this.posX == ziel.getPosX() || this.posY == ziel.getPosY()))
			return false;
		//zielfeld liegt auf einer linie
		//richtung bestimmen
		//horizontal
		int richtung;
		if(this.posX == ziel.getPosX()) {
			richtung = MoveDirection(this.posX, ziel.getPosX());
			result = CheckPfadHorizontal(board, richtung, this.posY, ziel.getPosY(), this.posX);
		}
		//vertical
		else {
			richtung = MoveDirection(this.posY, ziel.getPosY());
			result = CheckPfadVertikal(board, richtung, this.posX, ziel.getPosX(), this.posY);
		
		}
		return result;
	}
	
	private int MoveDirection(int start,int ziel) {
		if(start < ziel)
			return 1;
		else
			return -1;
	}
	
	private boolean CheckPfadHorizontal(Feld[][] board, int richtung, int start, int ziel, int zeile) {
		boolean result = true;
		for(int i = start; i < ziel; i+= richtung) {
			if(board[zeile][i].hasFigur())
				result = false;
		}
		return result;
	}
	
	private boolean CheckPfadVertikal(Feld[][]board, int richtung, int start, int ziel, int spalte) {
		boolean result = true;
		for(int i = start += richtung; i != ziel; i+= richtung) {
			if(board[i][spalte].hasFigur())
				result = false;
		}
		return result;
	}
}
