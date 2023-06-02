import java.util.ArrayList;
import java.util.List;

public class Itetro implements Tetromino {
    private List<List<Minomino>> tetro;
    private int width = 1;
    private int height = 4;
    private boolean orientation = true;

    public Itetro(){
        List<Minomino> layer1 = new ArrayList<>();
        List<Minomino> layer2 = new ArrayList<>();
        List<Minomino> layer3 = new ArrayList<>();
        List<Minomino> layer4 = new ArrayList<>();
        List<List<Minomino>> iblock = new ArrayList<>();

        layer1.add(new Minomino(true));
        layer2.add(new Minomino(true));
        layer3.add(new Minomino(true));
        layer4.add(new Minomino(true));
        iblock.add(layer1);
        iblock.add(layer2);
        iblock.add(layer3);
        iblock.add(layer4);
        tetro = iblock;
    }

    @Override
    public List<List<Minomino>> returnTetro() {
        return tetro;
    }

    @Override
    public void rotate() {
        if(orientation){
            height = 1;
            width = 4;
            List<List<Minomino>> iblock = new ArrayList<>();
            List<Minomino> layer1 = new ArrayList<>();
            for(int x=0;x<4;x++){
                layer1.add(new Minomino(true));
            }
            iblock.add(layer1);
            tetro = iblock;
            orientation = false;
        }
        else{
            height = 4;
            width = 1;

            List<Minomino> layer1 = new ArrayList<>();
            List<Minomino> layer2 = new ArrayList<>();
            List<Minomino> layer3 = new ArrayList<>();
            List<Minomino> layer4 = new ArrayList<>();
            List<List<Minomino>> iblock = new ArrayList<>();

            layer1.add(new Minomino(true));
            layer2.add(new Minomino(true));
            layer3.add(new Minomino(true));
            layer4.add(new Minomino(true));
            iblock.add(layer1);
            iblock.add(layer2);
            iblock.add(layer3);
            iblock.add(layer4);
            tetro = iblock;

            orientation = true;
        }
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
