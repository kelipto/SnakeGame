package Game;

public abstract class GameElement implements Drawable {
    protected int x;
    protected int y;

    public GameElement(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
