package breakfast.hackers.superballservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import breakfast.hackers.superballservice.GameStateService.GameState;

@Controller
public class ObstacleController {
    
    @Autowired
    private ObstacleService obstacleService;
    @Autowired
    private GameStateService gameStateService;
    @Autowired
    private SimpMessagingTemplate template;

    @Scheduled(fixedRate=5000)
    public void bla() {
        if(gameStateService.getGameState() == GameState.RUNNING) {
            template.convertAndSend("/topic/obstacles", obstacleService.generateObstacle());
        }
    }
}
