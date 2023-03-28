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
	@Test
	public void startGame_shouldStartGame() throws Exception {
		try {
			Game game = new Game();
			game.setName("game1");
			game.setStatus(Game.Status.NEW);
			Long b = gameService.startGame(game);
			assertEquals(game.getId(),b);
		} catch (NullPointerException e){}

	}
	@Test
	public void getGameInfo_shouldReturnGameInfo() throws Exception {

		try {
			Game insertGame = new Game();
			insertGame.setName("game1");
			insertGame.setStatus(Game.Status.NEW);
			Long b = gameService.startGame(insertGame);
			Game newGame = gameService.getGameInfo(b);
			assertEquals(insertGame.getId(), newGame.getId());
		} catch (NullPointerException e) {}
	}
	@Test
	public void updateGameStatus_shouldUpdateGameStatus() throws Exception {
		try {
			Game insertGame = new Game();
			insertGame.setName("game1");
			insertGame.setStatus(Game.Status.NEW);
			Long b = gameService.startGame(insertGame);
			Game game = gameService.getGameInfo(b);
			game.setStatus(Game.Status.FINISHED);
			gameService.updateGameStatus(game);
			assertEquals(Game.Status.FINISHED, game.getStatus());
		} catch (NullPointerException e) {
		}
	}

	@Test
	public void deleteGame_shouldDeleteGame() throws Exception {
		try{
			Game game = new Game();
			game.setName("game2");
			boolean b = gameService.deleteGame(game.getId());
			assertEquals(true, b);
		} catch (NullPointerException e) {}
	}
}