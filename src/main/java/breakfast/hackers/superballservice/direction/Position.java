package breakfast.hackers.superballservice.direction;

public enum Position {
    
    LEFT(-1.0),
    HALF_LEFT(-0.5),
    CENTER(0),
    HALF_RIGHT(0.5),
    RIGHT(1.0);
    
    private double pos;
    
    private Position(double pos) {
        this.pos = pos;
    }
    
    public double getPosition() {
        return pos;
    }
}
