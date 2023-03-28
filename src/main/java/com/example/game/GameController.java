package com.example.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController

public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping("/")
    public boolean home() {return true;}

    @PostMapping("/play")
    public int startGame(@RequestParam(required=false) Game game){
        return gameService.startGame(game);
    }

    @GetMapping("/game")
    public Game getGameInfo(Game game) throws Exception {
        return gameService.getGameInfo(game.getId());
    }

    @PutMapping("/play")
    public int updateGameStatus(Game game) throws Exception {
        return gameService.updateGameStatus(game.getId(), game.getStatus());
    }

    @DeleteMapping("/game")
    public boolean deleteGame(Game game) throws Exception {
        return gameService.deleteGame(game.getId());
    }
}
