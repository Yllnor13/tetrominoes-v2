import java.util.List;

public class Tennis {
    private final int WIDTH = 10;
    private final int HEIGHT = 20;
    private Minomino[][] field = new Minomino[HEIGHT][WIDTH];

    public Tennis() {
        // Initialize the playing field with empty cubes
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                field[i][j] = new Minomino(false); // false means empty
            }
        }
    }

    public void lineClear(){
        //TODO: make a method that goes through every line and checks if it should line clear
        //then make everything above it go down one line
        //order should be: Line clear->line above goes down
        int i = 0;
        for(Minomino[] minoList : field){
            int j = 0;
            for(Minomino mino : minoList){
                if(mino.getIsFull() == true && mino.getActive() == false){
                    j++;
                }
                if(j==10){
                    for(Minomino mino2 : minoList){
                        mino2.erase();
                    }
                    

                }
            }
            i++;
        }
    }

    public void insert(Tetromino tetromino, int x, int y){
        //erases the previous tetromino in play before drawing it again
        for(Minomino[] minoList : field){
            for(Minomino mino : minoList){
                if(mino.getActive() == true){
                    mino.erase();
                }
            }
        }
        //TODO: adds the tetromino at the specified coordinates
        //should also check if its an active minomino or not in htere

        int i1 = 0;
        for(List<Minomino> minoList : tetromino.returnTetro()){
            for(Minomino mino : minoList){
                field[x][y] = mino;
                x++;
            }
            y++;
        }
    }

    public void drawField(){

    }
}
