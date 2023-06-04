import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tennis {
    public final int WIDTH = 10;
    public final int HEIGHT = 20;
    public Minomino[][] field = new Minomino[HEIGHT][WIDTH];
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
/*
    public void lineClear() {
        for (int i = field.length - 1; i >= 0; i--) {
            boolean isFullLine = true;
            for (Minomino mino : field[i]) {
                if (!mino.getIsFull() || mino.getActive()) {
                    isFullLine = false;
                    break;
                }
            }
    
            if (isFullLine) {
                for (Minomino mino : field[i]) {
                    mino.erase();
                }
    
                for (int j = i; j > 0; j--) {
                    field[j] = field[j - 1];
                }
                for(int j = 0; j<field[0].length; j++){
                    field[0][j] = new Minomino(false);
                }
            }
        }
    }*/
    

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
            }
            System.out.println("this is j:" + j);
            if (j == minoList.length) {
                System.out.println("this is i" + i);
                System.out.println("THE REQUIERMENTS ARE MET");
                for (Minomino mino : minoList) {
                    mino.erase();
                }
                for(int x = i; x>0;x--){
                    if(x==0){
                        for(Minomino mino : field[x]){
                            mino.erase();
                            mino.settle();
                        }
                    }else{
                        field[x] = Arrays.copyOf(field[x-1], field[x-1].length);
                    }
                }
            }
            i++;
        }
    }

    public Boolean insert(Tetromino tetromino, int x, int y){
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

        //this part may need a rewrite cause I am not really doing anything with the minomino in the actual tetromino
        //I think?
        //rewrite above
        for (List<Minomino> minoList : tetromino.returnTetro()) {
            for (Minomino mino : minoList) {


                //most important check, so that the method doesnt crash

                //if it tried to go out of bounds to the right or left
                if(prevX + tetromino.getWidth() > WIDTH){
                    tetrominoX = WIDTH - tetromino.getWidth();
                    insert(tetromino, tetrominoX, tetrominoY);
                    return true;
                }

                if(prevX < 0){
                    tetrominoX = 0;
                    insert(tetromino, tetrominoX, tetrominoY);
                    return true;
                }

                //if it goes below the field somehow
                if(prevY + tetromino.getHeight() > HEIGHT){
                    tetrominoY = HEIGHT - tetromino.getHeight();
                    insert(tetromino, tetrominoX, tetrominoY);
                    for(Minomino[] minoList2 : field){
                        for(Minomino mino2 : minoList2){
                            if(mino2 != null){
                                if(mino2.getActive() == true){
                                    mino2.settle();
                                }
                            }
                        }
                    }
                    return false;
                }

                //if the tetromino tries to insert itself at a place that has a settled tetromino
                
                else if (field[tetrominoY][tetrominoX].getIsFull() == true && field[tetrominoY][tetrominoX].getActive() == false){
                    field = prevField;
                    prevY--;
                    insert(tetromino, prevX, prevY);
                    for(Minomino[] minoList2 : field){
                        for(Minomino mino2 : minoList2){
                            if(mino2.getActive() == true){
                                mino2.settle();
                            }
                        }
                    }
                    return false;
                }
                
                //make it so that it gets the symbol but doesnt affect whatever is on the tetromino
                else if(field[tetrominoY][tetrominoX].getIsFull() == false){
                    if(mino.getIsFull()){
                        field[tetrominoY][tetrominoX] = new Minomino(true);
                    }
                    else{
                        field[tetrominoY][tetrominoX] = new Minomino(false);
                    }
                    tetrominoX++;
                }
            }
            tetrominoX = x;
            tetrominoY++;
        }
        lineClear();
        return true;
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
