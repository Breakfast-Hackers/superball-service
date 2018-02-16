package breakfast.hackers.superballservice;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping(path="/api/movements")
public class MovementController {
    
    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    public void move(MovementDTO movement) {
        
    }
}
