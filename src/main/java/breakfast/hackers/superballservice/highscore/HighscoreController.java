package breakfast.hackers.superballservice.highscore;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

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
    
    @CrossOrigin(origins="*")
    @GetMapping(path="/api/highscores", produces="application/json")
    public ResponseEntity<HighscoreDTO> getTopTen() {
        List<Long> scores = highscoreService.getTopTen();
        return ResponseEntity.ok(new HighscoreDTO(scores));
    }
    
    @Override
    public void handleHighscoreChange() {
        List<Long> scores = highscoreService.getTopTen();
        template.convertAndSend("/topic/highscore", new HighscoreDTO(scores));
    }

}
