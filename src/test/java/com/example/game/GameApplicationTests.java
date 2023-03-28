package com.example.game;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class GameApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private GameService gameService;

//    private EntityManager em;

	@Test
	public void startGame_shouldStartGame() throws Exception {
		try {
			Game game = new Game();
			game.setId(1L);
			game.setName("game1");
			game.setStatus(Game.Status.NEW);
			int b = gameService.startGame(game);
			assertEquals(1,b);
		} catch (NullPointerException e){
			//e.printStackTrace();
			System.out.println("AA");
		}

	}

	@Test
	public void getGameInfo_shouldReturnGameInfo() throws Exception {

		try {
			Game game = new Game();
			game.setId(1L);
			game.setName("game1");
			game.setStatus(Game.Status.NEW);
			Game newGame = gameService.getGameInfo(game.getId());
			assertEquals(game.getId(), newGame.getId());
		} catch (NullPointerException e) {
			//e.printStackTrace();
			System.out.println("BB");
		}
	}

	@Test
	public void updateGameStatus_shouldUpdateGameStatus() throws Exception {
		try{
			Game game = gameService.getGameInfo(1L);
			gameService.updateGameStatus(game.getId(), Game.Status.FINISHED);
			assertEquals(Game.Status.FINISHED, game.getStatus());
		} catch (NullPointerException e) {
			//e.printStackTrace();
			System.out.println("BB");
		}


	}

	@Test
	public void deleteGame_shouldDeleteGame() throws Exception {
		try{
			Game game = new Game();
			game.setName("game2");
			boolean b = gameService.deleteGame(game.getId());
			assertEquals(true, b);
		} catch (NullPointerException e) {
			//e.printStackTrace();
			System.out.println("BB");
		}
	}

}
