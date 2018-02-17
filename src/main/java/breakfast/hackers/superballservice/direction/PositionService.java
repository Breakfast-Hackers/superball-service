package breakfast.hackers.superballservice.direction;

import org.springframework.stereotype.Component;

@Component
public class PositionService {
    
    private Position position = Position.CENTER;
    
    
    public void moveLeft() {
        position = position.getLeftNeighbor();
    }
    
    public void moveRight() {
        position = position.getRightNeighbor();
    }
    
    public Position getPosition() {
        return position;
    }
}
