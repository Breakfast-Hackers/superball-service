package breakfast.hackers.superballservice.direction;

public class PositionService {
    
    private Position position;
    
    
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
