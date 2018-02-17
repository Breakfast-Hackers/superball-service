package breakfast.hackers.superballservice.direction;

public enum Position {
    
    LEFT(-1.0F, 0, 1),
    HALF_LEFT(-0.5F, 0, 2),
    CENTER(0.0F, 1, 3),
    HALF_RIGHT(0.5F, 2, 4),
    RIGHT(1.0F, 3, 4);
    
    private final float pos;
    private final int leftNeighbor;
    private final int rightNeighbor;
    
    private Position(float pos, int leftNeighbor, int rightNeighbor) {
        this.pos = pos;
        this.leftNeighbor = leftNeighbor;
        this.rightNeighbor = rightNeighbor;
    }
    
    public float getPosition() {
        return pos;
    }

    public Position getLeftNeighbor() {
        return Position.values()[leftNeighbor];
    }

    public Position getRightNeighbor() {
        return Position.values()[rightNeighbor];
    }
}
