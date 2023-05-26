import java.util.ArrayList;
import java.util.List;

public interface Tetromino {
    public List<List<Minomino>> returnTetro();
    public void rotate();
    public void setActive();
    public void debugPrint();
    public int getWidth();
    public int getHeight();
}
