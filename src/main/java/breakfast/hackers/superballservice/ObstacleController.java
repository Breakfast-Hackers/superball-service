package breakfast.hackers.superballservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@Controller
public class ObstacleController {
    
    @Autowired
    private ObstacleService obstacleService;
    @Autowired
    private SimpMessagingTemplate template;

    @Scheduled(fixedRate=5000)
    public void bla() {
        template.convertAndSend("/topic/obstacles", obstacleService.generateObstacle());
    }
}
