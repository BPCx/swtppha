package de.tuberlin.sese.swtpp.gameserver.model.crazyhouse;

import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;



class Board implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2417539552840948740L;
	private static final String initBoard = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR/";
	//Feld enthält Informationen zu seiner Position und ob sich eine Figur darauf befindet
	private Feld[][] board;
	//pocket spieler weiss
	//pocket spieler schwarz
	private List<Figur> pocketSchwarz;
	private List<Figur> pocketWeiss;
	
	public Board() {
		pocketSchwarz = new LinkedList<Figur>();
		pocketWeiss = new LinkedList<Figur>();
	}
	
	public void Init() {
		//benötigt wegen der Tests
		//erstellt ein frisches Board mit figuren
		this.setBoard(initBoard);
		
	}
	
	public void addRow(String Row, int row) {
		char[] Fields = Row.toCharArray();
		int feldChar = 0;
		for (int i = 0; i < 8; i++) {
			Feld neuesFeld = null;
			if(Character.isDigit(Fields[feldChar])) {
				int anzahlFelder = Integer.parseInt(String.valueOf(Fields[feldChar]));
				int j;
				for(j = 0; j < anzahlFelder; j++) {
					neuesFeld = new Feld(row, i, null);
					board[row][i+j] = neuesFeld;
				}
				feldChar++;
				i+=j-1;
				continue;
			}
			Figur neueFigur = erstelleFigur(Fields[feldChar], row, i);
			if(Character.isLowerCase(Fields[feldChar]))
				neueFigur.setFarbe(Farbe.Schwarz);
			neuesFeld = new Feld(row, i, neueFigur);
			feldChar++;
			board[row][i] = neuesFeld;
		}
	}
	
	private void erstelleSpielerPockets(String pocketString) {
		char[] figuren = pocketString.toCharArray();
		for (int i = 0; i < figuren.length; i++) {
			Figur neueFigur = erstelleFigur(figuren[i], -1, -1);
			if(Character.isLowerCase(figuren[i])) {
				neueFigur.setFarbe(Farbe.Schwarz);
				pocketSchwarz.add(neueFigur);
			}
			else
				pocketWeiss.add(neueFigur);
		}
	}
		
	private Figur erstelleFigur(char figur, int x, int y) {
		Figur neueFigur = null;
		switch (Character.toUpperCase(figur)) {
		case 'R':
			neueFigur = new Turm(x, y, Farbe.Weiss);
			break;
		case 'K':
			neueFigur = new Koenig(x, y, Farbe.Weiss);
			break;
		case 'Q':
			neueFigur = new Dame(x, y, Farbe.Weiss);
			break;
		case 'B':
			neueFigur = new Laeufer(x, y, Farbe.Weiss);
			break;
		case 'P':
			neueFigur = new Bauer(x, y, Farbe.Weiss);
			break;
		case 'N':
			neueFigur = new Springer(x, y, Farbe.Weiss);
			break;
		}
		return neueFigur;
	}
	
	private void loeschBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = null;
			}
		}
		board = null;
	}
	
	public void setBoard(String boardString) {
		//überschreibt ggf. das Board
		if(board != null)
			loeschBoard();
		board = new Feld[8][8];
		String[] rows = boardString.split("/");
		for (int i = 0; i < 8; i++) {
			addRow(rows[i], i);
		}
		if(rows.length == 9) {
			//pockets erstellen
			erstelleSpielerPockets(rows[8]);
		}
	}

	public String getBoardString() {
		//string aus Feld und figuren generieren
		//leere felder beachten
		//figur soll eigene char zurückgeben mit farbe
		//pockets müssen sortiert aufgelistet werden
		String boardString = erstelleBoardString();
		boardString = boardString.concat(erstellePocketString());
		return boardString;
	}
	
	private String erstelleBoardString() {
		String boardString = "";
		int anzahlFelderLeer = 0;
		//TODO
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if(!board[i][j].hasFigur()) {
					anzahlFelderLeer++;
					if(anzahlFelderLeer > 0) {
						boardString = boardString.concat(Integer.toString(anzahlFelderLeer));
						anzahlFelderLeer = 0;
					}
				}
				else
					boardString = boardString.concat(board[i][j].getFigur().getString());
			}
			if(anzahlFelderLeer > 0)
				boardString = boardString.concat(Integer.toString(anzahlFelderLeer));
			boardString = boardString.concat("/");
			anzahlFelderLeer = 0;
		}
		return boardString;
	}
	
	private String erstellePocketString() {
		String pocketString = "";
		
		for (Figur figur : pocketWeiss) {
			pocketString = pocketString.concat(figur.getString());
		}
		for (Figur figur : pocketSchwarz) {
			pocketString = pocketString.concat(figur.getString());
		}
		
		char test[] = pocketString.toCharArray();
		Arrays.sort(test);
		return new String(test);
	}
	
	private Figur durchsuchePocket(List<Figur> pocket, String figurString) {
		for (Figur element : pocket) {
			Figur figur = (Figur) element;
			if(figur.getString() == figurString)
				return figur;
		}
		return null;
	}
	
	private Figur checkFigurInPocket(String figurString) {
		Figur figur;
		if(Character.isLowerCase(figurString.charAt(0)))
			figur = durchsuchePocket(pocketSchwarz, figurString);
		else
			figur = durchsuchePocket(pocketWeiss, figurString);
		return figur;
	}
	
	public boolean tryMove(String move) {
		String[] moves = move.split("-");
		
		//checken ob der erste teil Feld oder Pocket ist
		//if(!moveString.matches("(([kqbnrpKQBNRP])|([a-h]))-[a-h][1-8]"))
		Figur SpielerFigur;
		if(move.matches("[kqbnrpKQBNRP]-[a-h][1-8]")) {
			String figString = move.substring(0, 1);
			SpielerFigur = checkFigurInPocket(figString);
			if(SpielerFigur == null)
				return false;
		}			
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				board[i][j].setPosX(i);
				board[i][j].setPosY(j);
			}
		}
		Position startPos = String2Pos(moves[0]);
		Position zielPos = String2Pos(moves[1]);
		
		Feld startFeld = board[startPos.getX()][startPos.getY()];
		Feld zielFeld = board[zielPos.getX()][zielPos.getY()];
		if(!startFeld.hasFigur())
			return false;
		SpielerFigur = startFeld.getFigur();
		if(zielFeld.hasFigur())
			if(!zielFeld.getFigur().CanBeat(SpielerFigur))
				return false;
		
		if(!SpielerFigur.CanMove(board, zielFeld))
			return false;
		//wenn zielfeld spielfigur hat wird sie geschlagen
		//danach wird die spielerfigur auf das feld gesetzt und die werte aktualisiert
		//danach muss der
		//
		SpielerFigur.setPosX(startPos.getX());
		SpielerFigur.setPosY(zielPos.getY());
		board[zielPos.getX()][zielPos.getY()].setFigur(SpielerFigur);
		board[startPos.getX()][startPos.getY()].setFigur(null);
		return true;
	}
	
	private Position String2Pos(String move) {
		Position pos = new Position();
		char[] position = move.toCharArray();
		
		int x = position[0] - 97;
		int y = Integer.parseInt(String.valueOf(position[1]));
		
		pos.setX(8 - y);
		pos.setY(x);
		
		
		
		return pos;
	}
}
