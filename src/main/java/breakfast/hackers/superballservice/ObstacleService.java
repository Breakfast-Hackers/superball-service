package breakfast.hackers.superballservice;

import org.springframework.stereotype.Component;

@Component
public class ObstacleService {
    
    private final String[] colors = {"#FF0000", "#00FF00", "#0000FF", "#FF00FF", "#00FFFF", "#FFFF00"};
    
    public ObstacleDTO generateObstacle() {
        return new ObstacleDTO(determineColor(), determinePosition());
    }

    private String determineColor() {
        final int index = (int) (Math.random() * 5d);
        return colors[index];
    }
    
    private double determinePosition() {
        return (Math.random() * 2d) - 1d;
    }
}
