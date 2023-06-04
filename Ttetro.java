import java.util.ArrayList;
import java.util.List;

public class Ttetro implements Tetromino {
    private List<List<Minomino>> tetro;
    private int width = 3;
    private int height = 2;
    private int orientation = 0;
    
    public Ttetro(){
        List<Minomino> layer1 = new ArrayList<>();
        List<Minomino> layer2 = new ArrayList<>();
        List<List<Minomino>> tblock = new ArrayList<>();

        layer1.add(new Minomino(false));
        layer1.add(new Minomino(true));
        layer2.add(new Minomino(true));
        layer2.add(new Minomino(true));
        layer2.add(new Minomino(true));
        tblock.add(layer1);
        tblock.add(layer2);
        tetro = tblock;
    }

    @Override
    public List<List<Minomino>> returnTetro() {
        return tetro;
    }
    
    @Override
    public void rotate() { //rotates clockwise
        List<List<Minomino>> tblock = new ArrayList<>();
        
        if(orientation == 0){ //turn 90 degrees clockwise
            List<Minomino> layer1 = new ArrayList<>();
            List<Minomino> layer2 = new ArrayList<>();
            List<Minomino> layer3 = new ArrayList<>();
            layer1.add(new Minomino(true));
            layer2.add(new Minomino(true));
            layer2.add(new Minomino(true));
            layer3.add(new Minomino(true));
            tblock.add(layer1);
            tblock.add(layer2);
            tblock.add(layer3);
            tetro = tblock;

            width=2;
            height=3;

            orientation++;
        }
        else if(orientation == 1){ //180 degrees, just the init with opposite adds on the layer
            List<Minomino> layer1 = new ArrayList<>();
            layer1.add(new Minomino(false));
            layer1.add(new Minomino(true));
            List<Minomino> layer2 = new ArrayList<>();
            layer2.add(new Minomino(true));
            layer2.add(new Minomino(true));
            layer2.add(new Minomino(true));
            tblock.add(layer2);
            tblock.add(layer1);
            tetro = tblock;

            width=3;
            height=2;

            orientation++;
        }
        else if(orientation == 2){ //270 degrees
            List<Minomino> layer1 = new ArrayList<>();
            List<Minomino> layer2 = new ArrayList<>();
            List<Minomino> layer3 = new ArrayList<>();
            layer1.add(new Minomino(false));
            layer1.add(new Minomino(true));
            layer2.add(new Minomino(true));
            layer2.add(new Minomino(true));
            layer3.add(new Minomino(false));
            layer3.add(new Minomino(true));
            tblock.add(layer1);
            tblock.add(layer2);
            tblock.add(layer3);
            tetro = tblock;
            
            width=2;
            height=3;

            orientation++;
        }
        else{ //return to normal
            List<Minomino> layer1 = new ArrayList<>();
            layer1.add(new Minomino(false));
            layer1.add(new Minomino(true));
            List<Minomino> layer2 = new ArrayList<>();
            layer2.add(new Minomino(true));
            layer2.add(new Minomino(true));
            layer2.add(new Minomino(true));
            tblock.add(layer1);
            tblock.add(layer2);
            tetro = tblock;

            width=3;
            height=2;
            
            orientation = 0;
        }
    }
    
    @Override
    public void setActive() {
        for(List<Minomino> list : tetro){
            for(Minomino mino : list){
                mino.settle();
            }
        }
    }
    
    @Override
    public void debugPrint() {
        for(List<Minomino> minolist : tetro){
            for(Minomino mino : minolist){
                System.out.print(mino.getSymbol());
            }
            System.out.print("\n");
        }
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
