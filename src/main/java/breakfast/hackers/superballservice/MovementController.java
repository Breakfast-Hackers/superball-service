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
@RequestMapping
public class MovementController {
    
    @Autowired
    private SimpMessagingTemplate template;
    
    @PostMapping(path="/api/movements", consumes="application/json")
    @ResponseStatus(value = HttpStatus.OK)
    public void move(@RequestBody MovementDTO movement) {
        template.convertAndSend("/topic/movements", movement);
    }
    
}
