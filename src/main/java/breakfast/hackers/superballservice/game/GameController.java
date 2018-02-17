package breakfast.hackers.superballservice.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import breakfast.hackers.superballservice.highscore.HighscoreService;

@Controller
@RequestMapping(path="/api/game")
public class GameController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(GameController.class);
    
    @FunctionalInterface
    private static interface VoidFunction {
        void apply();
    }
    
    @Autowired
    private GameStateService gameStateService;
    @Autowired
    private HighscoreService highscoreService;
    @Autowired
    private SimpMessagingTemplate template;

    @PostMapping(consumes="application/json")
    @ResponseStatus(value = HttpStatus.OK)
    public void handleCommand(@RequestBody CommandDTO command) {
        determineGameState(command.getAction()).apply();
        template.convertAndSend("/topic/commands", command);
    }
    
    @DeleteMapping
    @ResponseStatus(value = HttpStatus.OK)
    public void gameOver() {
        highscoreService.saveScore(gameStateService.getDuration());
        gameStateService.stopGame();
        // TODO: call Alexa
    }
    
    private VoidFunction determineGameState(String action) {
        switch(action.toLowerCase()) {
            case "pause" : return gameStateService::pauseGame;
            case "start" : return gameStateService::startGame;
            case "stop"  :
                highscoreService.saveScore(gameStateService.getDuration());
                return gameStateService::stopGame;
            case "weiter": return gameStateService::continueGame;
            default: LOGGER.warn("unknown action: " + action);
        }
        return () -> {};
    }
    
    @Scheduled(fixedRate=1000)
    public void sendTime() {
        template.convertAndSend("/topic/duration", new DurationDTO(gameStateService.getDuration()));
    }
}
