package breakfast.hackers.superballservice;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.springframework.stereotype.Component;

@Component
public class MovementService {

    private Queue<MovementDTO> movements = new ConcurrentLinkedQueue<>();
            
    public void addMovement(MovementDTO movement) {
        movements.offer(movement);
    }
    
    public Collection<MovementDTO> getMovements() {
        return Collections.unmodifiableCollection(movements);
    }

    public Optional<MovementDTO> getNextMovement() {
        return Optional.ofNullable(movements.poll());
    }
}
