package breakfast.hackers.superballservice;

import org.springframework.stereotype.Component;

@Component
public class GameStateService {

    public enum GameState {
        RUNNING,
        PAUSED,
        STOPPED
    }
    
    private GameState gameState = GameState.STOPPED;
    
    public void start() {
        gameState = GameState.RUNNING;
    }
    
    public void pause() {
        gameState = GameState.PAUSED;
    }
    
    public void stop() {
        gameState = GameState.STOPPED;
    }
    
    public GameState getGameState() {
        return gameState;
    }
}
