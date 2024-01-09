package Game;

// GameObserver interface defines methods to handle various game events.
public interface GameObserver {
    void onGameOver();
    void onAppleEaten();
    void onSaveSuccess();
    void onSaveError();
    void onLoadSuccess();
    void onLoadError();
}
