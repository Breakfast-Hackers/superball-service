package breakfast.hackers.superballservice.obstacle;

import java.io.Serializable;

public class ObstacleDTO implements Serializable {

    private static final long serialVersionUID = 9143713509109003581L;
    
    private final String color;
    private final double position;
    
    public ObstacleDTO(String color, double position) {
        super();
        this.color = color;
        this.position = position;
    }
    
    public String getColor() {
        return color;
    }
    
    public double getPosition() {
        return position;
    }
    
}
