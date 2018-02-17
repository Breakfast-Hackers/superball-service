package breakfast.hackers.superballservice;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class GameStateService {

    public enum GameState {
        RUNNING,
        PAUSED,
        STOPPED
    }
    
    private long duration;
    private long start = -1;
    
    private GameState gameState = GameState.STOPPED;
    
    public void start() {
        gameState = GameState.RUNNING;
        duration = 0L;
        start = new Date().getTime();
    }
    
    public void pause() {
        gameState = GameState.PAUSED;
        duration += (new Date().getTime() - start);
        start = -1;
    }
    
    public void continueGame() {
        gameState = GameState.RUNNING;
        start = new Date().getTime();
    }
    
    public void stop() {
        gameState = GameState.STOPPED;
        duration += (new Date().getTime() - start);
        start = -1;
    }
    
    public long getDuration() {
        long current = 0;
        if(start > -1) {
            current = new Date().getTime() - start;
        }
        return duration + current;
    }
    
    public GameState getGameState() {
        return gameState;
    }
}
