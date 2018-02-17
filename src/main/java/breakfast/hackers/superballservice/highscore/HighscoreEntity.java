package breakfast.hackers.superballservice.highscore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class HighscoreEntity {

    @Id
    @GeneratedValue
    private Long id;
    private long score;
    
    public Long getId() {
        return id;
    }
    
    public long getScore() {
        return score;
    }
    
    public void setScore(long score) {
        this.score = score;
    }
}
