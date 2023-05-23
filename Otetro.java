import java.util.ArrayList;
import java.util.List;

public class Otetro implements Tetromino{
    private List<List<Minomino>> tetro;
    public int width = 2;

    public Otetro(){
        List<Minomino> layer1 = new ArrayList<>();
        Minomino m1 = new Minomino(true);
        Minomino m2 = new Minomino(true);
        layer1.add(0, m1);
        layer1.add(1, m2);
        List<List<Minomino>> oblock = new ArrayList<>();
        oblock.add(layer1);
        oblock.add(layer1);
        
        tetro = oblock;
    }

    @Override
    public void debugPrint(){
        for(List<Minomino> minolist : tetro){
            for(Minomino mino : minolist){
                System.out.print(mino.getSymbol());
            }
            System.out.print("\n");
        }
    }

    @Override
    public List<List<Minomino>> returnTetro() {
        // TODO Auto-generated method stub

        return tetro;
    }

    @Override
    public void rotate() {
        // TODO make a rotate method for each rotation
    }

    @Override
    public void setActive() {
        for(List<Minomino> list : tetro){
            for(Minomino mino : list){
                mino.settle();
            }
        }
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setActive'");
    }
    
}
