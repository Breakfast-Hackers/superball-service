package breakfast.hackers.superballservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping(path="/api/game")
public class GameController {
    
    @Autowired
    private SimpMessagingTemplate template;

    @PostMapping(consumes="application/json")
    @ResponseStatus(value = HttpStatus.OK)
    public void handleCommand(CommandDTO command) {
        template.convertAndSend("/topic/commands", command);
    }
}
