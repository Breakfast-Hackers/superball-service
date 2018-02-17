package breakfast.hackers.superballservice.direction;

import java.io.Serializable;

public class DirectionDTO implements Serializable {
    
    private static final long serialVersionUID = -1272432189184993601L;

    private String action;
    
    public String getAction() {
        return action;
    }
    
    public void setAction(String action) {
        this.action = action;
    }

}
