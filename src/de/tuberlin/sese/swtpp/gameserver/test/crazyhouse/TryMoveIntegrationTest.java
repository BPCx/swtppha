package de.tuberlin.sese.swtpp.gameserver.test.crazyhouse;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import de.tuberlin.sese.swtpp.gameserver.control.GameController;
import de.tuberlin.sese.swtpp.gameserver.model.Game;
import de.tuberlin.sese.swtpp.gameserver.model.Player;
import de.tuberlin.sese.swtpp.gameserver.model.User;

public class TryMoveIntegrationTest {

	User user1 = new User("Alice", "alice");
	User user2 = new User("Bob", "bob");
	
	Player whitePlayer = null;
	Player blackPlayer = null;
	Game game = null;
	GameController controller;
	
	@Before
	public void setUp() throws Exception {
		controller = GameController.getInstance();
		controller.clear();
		
		int gameID = controller.startGame(user1, "", "crazyhouse");
		
		game =  controller.getGame(gameID);
		whitePlayer = game.getPlayer(user1);

	}
	
	public void startGame() {
		controller.joinGame(user2, "crazyhouse");		
		blackPlayer = game.getPlayer(user2);
	}
	
	public void startGame(String initialBoard, boolean whiteNext) {
		startGame();
		
		game.setBoard(initialBoard);
		game.setNextPlayer(whiteNext? whitePlayer:blackPlayer);
	}
	
	public void assertMove(String move, boolean white, boolean expectedResult) {
		if (white)
			assertEquals(expectedResult, game.tryMove(move, whitePlayer));
		else 
			assertEquals(expectedResult,game.tryMove(move, blackPlayer));
	}
	
	public void assertGameState(String expectedBoard, boolean whiteNext, boolean finished, boolean whiteWon) {
		String board = game.getBoard().replaceAll("e", "");
		
		assertEquals(expectedBoard,board);
		assertEquals(finished, game.isFinished());

		if (!game.isFinished()) {
			assertEquals(whiteNext, game.getNextPlayer() == whitePlayer);
		} else {
			assertEquals(whiteWon, whitePlayer.isWinner());
			assertEquals(!whiteWon, blackPlayer.isWinner());
		}
	}
	

	/*******************************************
	 * !!!!!!!!! To be implemented !!!!!!!!!!!!
	 *******************************************/
	
	@Test
	public void exampleTest() {
		startGame("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR/",true);
		assertMove("b2-b7",true,false);
		assertGameState("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR/",true,false,false);
	}
	
	@Test
	public void StartBoardTest() {
		startGame();
		assertGameState("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR/",true,false,false);
	}
	
	@Test
	public void StartBoardWithPocketsTest() {
		startGame();
		assertGameState("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR/",true,false,false);
	}
	
	@Test
	public void UnegueltigerZugFormatTest() {
		startGame();
		assertMove("b2-b7", true, false);
		assertGameState("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR/", true, false, false);
	}
	
	@Test
	public void KoenigZugTest() {
		
	}
	
	@Test
	public void DameZugTest() {
		
	}
	
	@Test
	public void BauerZugTest() {
		
	}
	
	@Test
	public void LaeuferZugTest() {
		
	}
	
	@Test
	public void SpringerZugTest() {
		
	}
	
	@Test
	public void TurmZugTest() {
		startGame("8/8/8/8/8/8/8/R2p4/", true);
		assertMove("a1-a8", true, false);
		assertGameState("8/8/8/8/8/8/8/R2p4/", false, false, false);
	}
	
	@Test
	public void LeeresFeldZugTest() {
		
	}

	@Test
	public void GueltigerZugTest() {
		
	}
	
	@Test
	public void SchachmattTest() {
		
	}
	
	@Test
	public void SchachmattReserveTest() {
		
	}
	
	@Test
	public void WeissGewinntTest() {
		
	}
	
	@Test
	public void SchwarzGewinntTest() {
		
	}
	
	@Test
	public void BauerZuDameTest() {
		startGame("",true);
		assertMove("", true, true);
	}
	
	@Test
	public void FigurSchlagenTest() {
		//erwartung der Figur in Pocket in anderer Farbe
	}
	
	@Test
	public void BauerAusPocketTest() {
		startGame("rnbQ2Q1/pppp3p/6k1/8/1P6/8/Pn1pPKPP/RNB2BNR/BPQRppq", true);
		assertMove("P-c3", true, true);
		assertGameState("rnbQ2Q1/pppp3p/6k1/8/1P6/8/Pn1pPKPP/RNB2BNR/BQRppq", false, false, false);
	}
	
	@Test
	public void PocketZustandTest() {
		startGame("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR/RNBQKBNRrnbqkbnr", true);
		assertGameState("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR/BBKNNQRRbbknnqrr", true, false, false);
	}
	
	@Test
	public void UngueltigesZugFormatTest() {
		startGame();
		assertMove("aa-55", true, false);
		assertGameState("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR/", true, false, false);
	}
	
	//TODO: implement test cases of same kind as example here
//	mögliche testfälle;
//	gültiger zug für figur
//	ungültiger zug für figur. verändert board nicht
//	falscher spieler dran
//	richtiger spieler dran
//	spieler gewinnt
//	spieler verliert
//	gegner gewinnt
//	gegner verliert
//	unentschieden vorgeschlagen..wie testen?
}
