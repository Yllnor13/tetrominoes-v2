import java.util.ArrayList;
import java.util.List;

public class Stetro implements Tetromino {
    private List<List<Minomino>> tetro;
    private int width = 3;
    private int height = 2;
    private boolean orientation = true;

    public Stetro(){
        List<List<Minomino>> sblock = new ArrayList<>();

        List<Minomino> layer1 = new ArrayList<>(3);
        List<Minomino> layer2 = new ArrayList<>(3);

        layer1.add(new Minomino(false));
        layer1.add(new Minomino(true));
        layer1.add(new Minomino(true));

        layer2.add(new Minomino(true));
        layer2.add(new Minomino(true));

        sblock.add(layer1);
        sblock.add(layer2);

        tetro = sblock;
    }

    @Override
    public List<List<Minomino>> returnTetro() {
        return tetro;
    }

    @Override
    public void rotate() { //S when flipped 180 is the same as when 0 degrees, same for 90 and 270
        List<List<Minomino>> sblock = new ArrayList<>();

        if(orientation){
            height = 3;
            width = 2;
            List<Minomino> layer1 = new ArrayList<>(width);
            List<Minomino> layer2 = new ArrayList<>(width);
            List<Minomino> layer3 = new ArrayList<>(width);

            layer1.add(new Minomino(true));
            
            layer2.add(new Minomino(true));
            layer2.add(new Minomino(true));
            
            layer3.add(new Minomino(false));
            layer3.add(new Minomino(true));

            sblock.add(layer1);
            sblock.add(layer2);
            sblock.add(layer3);

            tetro = sblock;
        }
        else{
            height = 2;
            width = 3;

            List<Minomino> layer1 = new ArrayList<>(width);
            List<Minomino> layer2 = new ArrayList<>(width);
                        
            layer1.add(new Minomino(false));
            layer1.add(new Minomino(true));
            layer1.add(new Minomino(true));

            layer2.add(new Minomino(true));
            layer2.add(new Minomino(true));

            sblock.add(layer1);
            sblock.add(layer2);

            tetro = sblock;;
        }
        orientation=!orientation;
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
            for(int x=0;x<minolist.size();x++){
                if(!minolist.get(x).getIsFull()){
                    System.out.print(". ");
                }
                else{
                    System.out.print(minolist.get(x).getSymbol());
                }
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
