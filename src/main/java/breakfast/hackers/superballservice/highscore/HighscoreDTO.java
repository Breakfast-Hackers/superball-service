package breakfast.hackers.superballservice.highscore;

import java.io.Serializable;
import java.util.List;

public class HighscoreDTO implements Serializable {

    private static final long serialVersionUID = -4359481855808296525L;
    
    private final List<Long> scores;

    public HighscoreDTO(List<Long> scores) {
        this.scores = scores;
    }

    public List<Long> getScores() {
        return scores;
    }
    
}
