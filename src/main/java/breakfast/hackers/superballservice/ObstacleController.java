package breakfast.hackers.superballservice;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
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
    
    @PostConstruct
    public void init() {
        new Thread(this::bla).start();
    }

    private void bla() {
        while(true) {
            if(gameStateService.getGameState() == GameState.RUNNING) {
                template.convertAndSend("/topic/obstacles", obstacleService.generateObstacle());
            }
            
            try {
                Thread.sleep(determineSleepTime());
            } catch(InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    
    private long determineSleepTime() {
        final long random = (long) ((Math.random() * 1000) - 2000);
        final long millis = Math.max(2500, (7_000 - ((gameStateService.getDuration() / 1000) * 500))) + random;
        return millis;
    }
}
