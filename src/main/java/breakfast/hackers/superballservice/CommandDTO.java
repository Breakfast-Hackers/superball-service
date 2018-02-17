package breakfast.hackers.superballservice;

import java.io.Serializable;

public class CommandDTO implements Serializable {

    private static final long serialVersionUID = -5683680581650341970L;
    
    private String action;
    
    public String getAction() {
        return action;
    }
    
    public void setAction(String action) {
        this.action = action;
    }

}
