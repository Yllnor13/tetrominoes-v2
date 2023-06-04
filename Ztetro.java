import java.util.ArrayList;
import java.util.List;

public class Ztetro implements Tetromino{
    private List<List<Minomino>> tetro;
    private int width = 3;
    private int height = 2;
    private boolean orientation = true;

    public Ztetro(){
        List<List<Minomino>> zblock = new ArrayList<>();

        List<Minomino> layer1 = new ArrayList<>(3);
        List<Minomino> layer2 = new ArrayList<>(3);

        layer1.add(new Minomino(true));
        layer1.add(new Minomino(true));
        
        layer2.add(new Minomino(false));
        layer2.add(new Minomino(true));
        layer2.add(new Minomino(true));

        zblock.add(layer1);
        zblock.add(layer2);

        tetro = zblock;
    }

    @Override
    public List<List<Minomino>> returnTetro() {
        return tetro;
    }

    @Override
    public void rotate() {
        List<List<Minomino>> zblock = new ArrayList<>();

        if(orientation){
            height = 3;
            width = 2;
            List<Minomino> layer1 = new ArrayList<>(width);
            List<Minomino> layer2 = new ArrayList<>(width);
            List<Minomino> layer3 = new ArrayList<>(width);

            layer1.add(new Minomino(false));
            layer1.add(new Minomino(true));
            
            layer2.add(new Minomino(true));
            layer2.add(new Minomino(true));
            
            layer3.add(new Minomino(true));

            zblock.add(layer1);
            zblock.add(layer2);
            zblock.add(layer3);

            tetro = zblock;
        }
        else{
            height = 2;
            width = 3;

            List<Minomino> layer1 = new ArrayList<>(width);
            List<Minomino> layer2 = new ArrayList<>(width);
                        
            layer1.add(new Minomino(false));
            layer1.add(new Minomino(true));
            layer1.add(new Minomino(true));

            layer2.add(new Minomino(false));
            layer2.add(new Minomino(true));
            layer2.add(new Minomino(true));

            zblock.add(layer1);
            zblock.add(layer2);

            tetro = zblock;;
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getWidth'");
    }

    @Override
    public int getHeight() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getHeight'");
    }
    
}
