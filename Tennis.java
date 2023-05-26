import java.util.ArrayList;
import java.util.List;

public class Tennis {
    public final int WIDTH = 10;
    public final int HEIGHT = 20;
    private Minomino[][] field = new Minomino[HEIGHT][WIDTH];
    private Minomino[][] prevField;
    private ArrayList<Minomino[][]> prevFields;

    private int prevX;
    private int prevY;
    private Tetromino prevTetro;

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
        //TODO: make a copy of the previous field, then check if the new insert makes the tetromino have 2 stay
        prevField = field;
        //prevFields.add(prevField);
        prevX = x;
        prevY = y;
        prevTetro = tetromino;

        //erases the previous tetromino in play before drawing it again
        for(Minomino[] minoList : field){
            for(Minomino mino : minoList){
                if(mino.getActive() == true){
                    mino.erase();
                }
            }
        }
        //TODO: adds the tetromino at the specified coordinates

        int tetrominoX = x;
        int tetrominoY = y;

        for (List<Minomino> minoList : tetromino.returnTetro()) {
            for (Minomino mino : minoList) {
                //if it tried to go out of bounds to the right or left
                if(tetrominoX + tetromino.getWidth() > WIDTH+1){
                    tetrominoX = WIDTH - tetromino.getWidth();
                    insert(tetromino, tetrominoX, tetrominoY);
                }

                else if(tetrominoX < 0){
                    tetrominoX = 0;
                    insert(tetromino, tetrominoX, tetrominoY);
                }

                //if it goes below the field somehow
                else if(tetrominoY + tetromino.getHeight() > HEIGHT+1){
                    tetrominoY= HEIGHT - tetromino.getHeight();
                    insert(tetromino, tetrominoX, tetrominoY);
                }
                
                //make it so that it gets the symbol but doesnt affect whatever is on the tetromino
                else if(field[tetrominoY][tetrominoX].getIsFull() == false){
                    field[tetrominoY][tetrominoX] = new Minomino(true);
                    tetrominoX++;
                }
                
                //if the tetromino tries to insert itself at a place that has a settled tetromino
                else{
                    field = prevField;
                    for(Minomino[] minoList2 : field){
                        for(Minomino mino2 : minoList2){
                            if(mino2.getActive() == true){
                                mino.settle();
                            }
                        }
                    }
                    tetrominoY--;
                }
            }
            tetrominoX = x;
            tetrominoY++;
        }
    }

    public void drawField(){
        // Loop through each row of the field and print each Cube
        
        System.out.println("\n\n\n\n\n\n\n\n\n");
        for (int i = 0; i < HEIGHT; i++) {
            System.out.print("<!");
            for (int j = 0; j < WIDTH; j++) {
                System.out.print(field[i][j].getSymbol()); // print the Cube
            }
            System.out.print("!>");
            System.out.println(); // move to the next row
        }
        System.out.print("<!====================!>");
    }

    public Minomino[][] getPrevField(){
        return prevField;
    }

    public ArrayList<Minomino[][]> getHistory(){
        return prevFields;
    }

    public int getPrevX() {
        return prevX;
    }

    public int getPrevY() {
        return prevY;
    }

    public Tetromino getPrevTetro() {
        return prevTetro;
    }
}
