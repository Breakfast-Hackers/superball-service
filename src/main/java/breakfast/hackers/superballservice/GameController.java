package breakfast.hackers.superballservice;

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
        gameStateService.stop();
        // TODO: call Alexa
    }
    
    private VoidFunction determineGameState(String action) {
        switch(action.toLowerCase()) {
            case "pause" : return gameStateService::pause;
            case "start" : return gameStateService::start;
            case "stop"  : return gameStateService::stop;
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
