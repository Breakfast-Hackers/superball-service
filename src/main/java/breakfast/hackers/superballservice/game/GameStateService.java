package breakfast.hackers.superballservice.game;

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
    
    public void startGame() {
        gameState = GameState.RUNNING;
        duration = 0L;
        start = new Date().getTime();
    }
    
    public void pauseGame() {
        gameState = GameState.PAUSED;
        duration += (new Date().getTime() - start);
        start = -1;
    }
    
    public void continueGame() {
        gameState = GameState.RUNNING;
        start = new Date().getTime();
    }
    
    public void stopGame() {
        gameState = GameState.STOPPED;
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
