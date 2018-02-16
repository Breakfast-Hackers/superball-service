package breakfast.hackers.superballservice;

import java.io.Serializable;

public class MovementDTO implements Serializable {
    
    private static final long serialVersionUID = -1272432189184993601L;

    private String direction;
    
    public String getDirection() {
        return direction;
    }
    
    public void setDirection(String direction) {
        this.direction = direction;
    }

}
