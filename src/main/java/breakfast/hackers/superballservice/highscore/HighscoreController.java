package breakfast.hackers.superballservice.highscore;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class HighscoreController implements HighscoreObserver {
    
    @Autowired
    private SimpMessagingTemplate template;
    
    @Autowired
    private HighscoreService highscoreService;
    
    @PostConstruct
    public void init() {
        highscoreService.addObserver(this);
    }
    
    @Override
    public void handleHighscoreChange() {
        List<Long> scores = highscoreService.getTopTen();
        template.convertAndSend("/topic/highscore", new HighscoreDTO(scores));
    }

}
