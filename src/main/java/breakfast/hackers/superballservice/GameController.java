package breakfast.hackers.superballservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping(path="/api/game")
public class GameController {
    
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
    
    private VoidFunction determineGameState(String action) {
        switch(action.toLowerCase()) {
            case "pause" : return gameStateService::pause;
            case "start" : return gameStateService::start;
            case "stop"  : return gameStateService::stop;
            case "weiter": return gameStateService::start;
            default: throw new IllegalStateException("unknown action: " + action);
        }
    }
}
