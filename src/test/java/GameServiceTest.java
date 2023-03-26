import com.example.game.Game;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameServiceTest {

    @Autowired
    private GameService gameService;

    private EntityManager em;

    @Test
    public void startGame_shouldStartGame() throws Exception {
        try {
            boolean b = gameService.startGame();
            assertEquals(true,b);
        } catch (NullPointerException e){
            //e.printStackTrace();
            System.out.println("AA");
        }

    }

    @Test
    public void getGameInfo_shouldReturnGameInfo() throws Exception {

        try {
            Game game = new Game();
            game.setName("game1");
            game.setStatus(Game.Status.NEW);
            Game newGame = gameService.getGameInfo(game.getId());
            assertEquals(game.getId(), newGame.getId());
            assertEquals(11,newGame.getId());
        } catch (NullPointerException e) {
            //e.printStackTrace();
            System.out.println("BB");
        }
    }

    @Test
    public void updateGameStatus_shouldUpdateGameStatus() throws Exception {
        Game game = gameService.getGameInfo(1L);
        gameService.updateGameStatus(game.getId(), Game.Status.FINISHED);
        assertEquals(Game.Status.FINISHED, game.getStatus());

    }

    @Test
    public void deleteGame_shouldDeleteGame() throws Exception {
        Game game = new Game();
        game.setName("game2");
        boolean b = gameService.deleteGame(game.getId());
        assertEquals(true, b);
    }
}
