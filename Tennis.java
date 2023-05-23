import java.util.List;

public class Tennis {
    public final int WIDTH = 10;
    public final int HEIGHT = 20;
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

        int tetrominoX = x;
        int tetrominoY = y;

        for (List<Minomino> minoList : tetromino.returnTetro()) {
            for (Minomino mino : minoList) {
                //make it so that it gets the symbol but doesnt affect whatever is on the tetromino
                field[tetrominoY][tetrominoX] = new Minomino(true);
                tetrominoX++;
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
}
