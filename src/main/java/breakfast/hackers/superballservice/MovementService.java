package breakfast.hackers.superballservice;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Collections;
import java.util.Queue;

import org.springframework.stereotype.Component;

@Component
public class MovementService {

    private Queue<MovementDTO> movements = new ArrayDeque<>();
            
    public void addMovement(MovementDTO movement) {
        movements.offer(movement);
    }
    
    public Collection<MovementDTO> getMovements() {
        return Collections.unmodifiableCollection(movements);
    }
}
