package breakfast.hackers.superballservice;

import java.util.ArrayDeque;
import java.util.Queue;

import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping(path="/api/movementsfickscheisse")
public class MovementController {
    
    private Queue<MovementDTO> movementQueue = new ArrayDeque<>();
    
    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    public void move(MovementDTO movement) {
        movementQueue.offer(movement);
    }
    
    @MessageMapping("/api/movementSocket")
    @SendTo("/topic/movements")
    public MovementDTO connect() {
        MovementDTO movement = movementQueue.poll();
        while(movement == null) {
            sleep(250L);
            movement = movementQueue.poll();
        }
        return movement;
    }

    private void sleep(long i) {
        // TODO Auto-generated method stub
        
    }
}
