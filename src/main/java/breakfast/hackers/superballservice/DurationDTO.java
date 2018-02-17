package breakfast.hackers.superballservice;

import java.io.Serializable;

public class DurationDTO implements Serializable {
    
    private static final long serialVersionUID = -9064490124802981460L;
    
    private final long duration;

    public DurationDTO(long duration) {
        this.duration = duration;
    }

    public long getDuration() {
        return duration;
    }
}
