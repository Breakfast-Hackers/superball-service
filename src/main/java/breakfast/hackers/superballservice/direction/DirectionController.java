package breakfast.hackers.superballservice.direction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class DirectionController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(DirectionController.class);
    
    @Autowired
    private SimpMessagingTemplate template;
    @Autowired
    private PositionService positionService;
    
    @CrossOrigin(origins="*")
    @GetMapping(path="/api/position", produces="application/json")
    public ResponseEntity<PositionDTO> getCurrentPosition() {
        return ResponseEntity.ok(new PositionDTO(positionService.getPosition().getPosition()));
    }
    
    @CrossOrigin(origins="*")
    @PostMapping(path="/api/direction", consumes="application/json")
    @ResponseStatus(value = HttpStatus.OK)
    public void move(@RequestBody DirectionDTO movement) {
        if("links".equalsIgnoreCase(movement.getAction())) {
            positionService.moveLeft();
        } else if("rechts".equalsIgnoreCase(movement.getAction())) {
            positionService.moveRight();
        } else {
            LOGGER.warn("unknown action: " + movement.getAction());
        }
        template.convertAndSend("/topic/movements", new PositionDTO(positionService.getPosition().getPosition()));
    }
    
}
