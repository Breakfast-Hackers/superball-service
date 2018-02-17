package breakfast.hackers.superballservice.highscore;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

@Component
public class HighscoreService {

    @Autowired
    private HighscoreEntityRepository highscoreEntityRepository;
    
    private List<HighscoreObserver> observers = new ArrayList<>();
    
    public void saveScore(long score) {
        HighscoreEntity entity = new HighscoreEntity();
        entity.setScore(score);
        highscoreEntityRepository.save(entity);
        
        observers.forEach(HighscoreObserver::handleHighscoreChange);
    }
    
    public List<Long> getTopTen() {
        PageRequest pageable = new PageRequest(0, 10, new Sort(Direction.DESC, "score"));
        return highscoreEntityRepository.findAll(pageable).getContent().stream()
                .map(HighscoreEntity::getScore)
                .collect(Collectors.toList());
    }

    public void addObserver(HighscoreObserver observer) {
        observers.add(observer);
    }
}
