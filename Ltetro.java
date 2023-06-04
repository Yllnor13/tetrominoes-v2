import java.util.ArrayList;
import java.util.List;

public class Ltetro implements Tetromino{
    private List<List<Minomino>> tetro;
    private int width = 2;
    private int height = 3;
    private int orientation = 0;

    public Ltetro(){
        List<Minomino> layer1 = new ArrayList<>();
        List<Minomino> layer2 = new ArrayList<>();
        List<Minomino> layer3 = new ArrayList<>();
        List<List<Minomino>> lblock = new ArrayList<>();

        layer1.add(new Minomino(true));
        layer2.add(new Minomino(true));
        layer3.add(new Minomino(true));
        layer3.add(new Minomino(true));

        lblock.add(layer1);
        lblock.add(layer2);
        lblock.add(layer3);

        tetro = lblock;
    }

    @Override
    public List<List<Minomino>> returnTetro() {
        return tetro;
    }

    @Override
    public void rotate() {
        List<List<Minomino>> lblock = new ArrayList<>();

        if(orientation == 0){
            width = 3;
            height = 2;

            List<Minomino> layer1 = new ArrayList<>();
            List<Minomino> layer2 = new ArrayList<>();

            layer1.add(new Minomino(true));
            layer1.add(new Minomino(true));
            layer1.add(new Minomino(true));
            layer2.add(new Minomino(true));

            lblock.add(layer1);
            lblock.add(layer2);

            orientation++;
        }
        else if(orientation == 1){
            width = 2;
            height = 3;

            List<Minomino> layer1 = new ArrayList<>();
            List<Minomino> layer2 = new ArrayList<>();
            List<Minomino> layer3 = new ArrayList<>();

            layer1.add(new Minomino(true));
            layer1.add(new Minomino(true));
            layer2.add(new Minomino(false));
            layer2.add(new Minomino(true));
            layer3.add(new Minomino(false));
            layer3.add(new Minomino(true));
            
            lblock.add(layer1);
            lblock.add(layer2);
            lblock.add(layer3);

            orientation++;
        }
        else if(orientation == 2){
            width = 3;
            height = 2;

            List<Minomino> layer1 = new ArrayList<>();
            List<Minomino> layer2 = new ArrayList<>();

            layer1.add(new Minomino(false));
            layer1.add(new Minomino(false));
            layer1.add(new Minomino(true));
            layer2.add(new Minomino(true));
            layer2.add(new Minomino(true));
            layer2.add(new Minomino(true));

            lblock.add(layer1);
            lblock.add(layer2);

            orientation++;
        }
        else if(orientation == 3){
            width = 2;
            height = 3;

            List<Minomino> layer1 = new ArrayList<>();
            List<Minomino> layer2 = new ArrayList<>();
            List<Minomino> layer3 = new ArrayList<>();

            layer1.add(new Minomino(true));
            layer2.add(new Minomino(true));
            layer3.add(new Minomino(true));
            layer3.add(new Minomino(true));

            lblock.add(layer1);
            lblock.add(layer2);
            lblock.add(layer3);

            orientation=0;
        }
        tetro = lblock;
    }

    @Override
    public void setActive() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setActive'");
    }

    @Override
    public void debugPrint() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'debugPrint'");
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }
    
}
