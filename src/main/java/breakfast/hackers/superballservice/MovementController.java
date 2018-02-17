package breakfast.hackers.superballservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping(path="/api/movements")
public class MovementController {
    
    @Autowired
    private MovementService movementService;
    
    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    public void move(@RequestBody MovementDTO movement) {
        movementService.addMovement(movement);
    }
    
    @MessageMapping("/api/movementSocket")
    @SendTo("/topic/movements")
    public MovementDTO connect() {
        return null;
    }

    private void sleep(long i) {
        // TODO Auto-generated method stub
        
    }
}
