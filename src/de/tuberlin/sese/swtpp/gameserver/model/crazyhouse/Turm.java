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
		int x_difference = ziel.getPosX() - this.posX;
		int y_difference = ziel.getPosY() - this.posY;
		
		if(!((x_difference == 0 && y_difference != 0) || (x_difference != 0 && y_difference == 0)))
			return false;
		
		int x_ascending = -1;
		int y_ascending = -1;
		
		if (x_difference > 0) {
			x_ascending = 1;
		}
		if (y_difference > 0) {
			y_ascending = 1;
		}
		if (y_difference == 0) {
			for (int i = 1; i <= Math.abs(x_difference); i++) {
				if (board[this.posX + x_ascending*i][this.posY].hasFigur()) {
					if(i == Math.abs(x_difference) && !board[this.posX+x_ascending*i][this.posY].getFigur().getFarbe().name().equals(this.getFarbe().name())) {
						return true; // return true if the target position has an opponent's piece.
					} else {
						return false; // return false if our piece is in the target position or any piece is along the way.
		}}}}
		
		if (x_difference == 0) {
			for (int i = 1; i <= Math.abs(y_difference); i++) {
				if (board[this.posX][this.posY+ y_ascending*i].hasFigur()) {
					if(i == Math.abs(y_difference) && !board[this.posX][this.posY+ y_ascending*i].getFigur().getFarbe().name().equals(this.getFarbe().name())) {
						return true; // return true if the target position has an opponent's piece.
					} else {
						return false; // return false if our piece is in the target position or any piece is along the way.
		}}}}
		return true;
	}
}
