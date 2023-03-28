package com.example.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping("/")
    public boolean home() {return true;}

    @PostMapping("/play")
    public Long startGame(@RequestBody(required = false) Game game){
        return gameService.startGame(game);
    }

    @GetMapping("/game")
    public Game getGameInfo(Game game) throws Exception {
        return gameService.getGameInfo(game.getId());
    }

    @PutMapping("/play")
    public boolean updateGameStatus(@RequestBody Game game) throws Exception {
        return gameService.updateGameStatus(game);
    }

    @DeleteMapping("/game")
    public boolean deleteGame(Game game) throws Exception {
        return gameService.deleteGame(game.getId());
    }
    @GetMapping("/search")
    public List<Game> search(@RequestParam(required = false) String name,
                             @RequestParam(required = false) String status){
        return gameService.search(name, status);
    }
}
