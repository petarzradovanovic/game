import com.example.game.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController



public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping("/")
    public boolean home() {
        return gameService.home();
    }

    @PostMapping("/play")
    public boolean startGame(){
        return gameService.startGame();
    }

    @GetMapping("/game")
    public Game getGameInfo(Game game) throws Exception {
        return gameService.getGameInfo(game.getId());
    }

    @PutMapping("/play")
    public int updateGameStatus(Game game){
        return gameService.updateGameStatus(game.getId(), game.getStatus());
    }

    @DeleteMapping("/game")
    public boolean deleteGame(Game game){
        return gameService.deleteGame(game.getId());
    }
}
