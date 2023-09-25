import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Tennis {
    public final int WIDTH = 10;
    public final int HEIGHT = 20;
    public Minomino[][] field = new Minomino[HEIGHT][WIDTH];
    private Minomino[][] prevField;

    private Tetromino savedTetro;
    private Minomino[][] savedtetrofield = new Minomino[4][4];
    private String[] saved = new String[6];
    private Tetromino[] next3tetro;
    private Minomino[][] next3field = new Minomino[4][12];
    private String[] next3 = new String[6];

    private ArrayList<Minomino[][]> prevFields; //might not need
    private int prevX;
    private int prevY;
    private Tetromino prevTetro;
    private Boolean gameOver = false;
    private int score = 0;
    private int level = 1;


    public Tennis() {
        // Initialize the playing field with empty cubes
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                field[i][j] = new Minomino(false); // false means empty
            }
        }

        //make empty fields for next3 and saved
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                savedtetrofield[i][j] = new Minomino(false);
            }
        }
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 12; j++){
                next3field[i][j] = new Minomino(false);
            }
        }

        //make saved tetromino field in string form for printing
        updateSaved();
        updateNext3();

        prevTetro = getNewTet();
    }

    public synchronized void lineClear(){
        //TODO: make a method that goes through every line and checks if it should line clear
        //then make everything above it go down one line
        //order should be: Line clear->line above goes down
        int i = 0;
        int lineclears = 0;
        for(Minomino[] minoList : field){
            int j = 0;
            for(Minomino mino : minoList){
                if(mino.getIsFull() == true && mino.getActive() == false){
                    j++;
                }
            }
            if (j == minoList.length) { //if there are enough filled lines to fill up a row
                lineclears++;
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
        if (lineclears == 1) { //had to use if else loops cause switch case statement made it add all points at the same time somehow
            score += level * 40;
        } else if (lineclears == 2) {
            score += level * 100;
        } else if (lineclears == 3) {
            score += level * 300;
        } else if (lineclears == 4) {
            score += level * 1200;
        }
    }

    public void wipe(){
        for(Minomino[] minolist : field){
            for(Minomino mino : minolist){
                mino.erase();
                mino.settle();
            }
        }
    }

    public synchronized Boolean insert(Tetromino tetromino, int x, int y){ //kunne ha gjort denne mer abstrakt slik at man kunne bruke den på savedmino og next3 mino
        //TODO: make a copy of the previous field, then check if the new insert makes the tetromino have 2 stay
        prevField = field;
        prevTetro = tetromino;
        prevX = x;
        prevY = y;
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
        if(y<0){
            gameOver = true;
            return true;
        }
        else{
            for (List<Minomino> minoList : tetromino.returnTetro()) {
                for (Minomino mino : minoList) {
                    if(mino.getIsFull()){
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
                                    if(mino2.getActive() == true){
                                        mino2.settle();
                                    }
                                }
                            }
                            return false;
                        }

                        //if the tetromino tries to insert itself at a place that has a settled tetromino
                        
                        else if (mino.getActive() && field[tetrominoY][tetrominoX].getIsFull() && !field[tetrominoY][tetrominoX].getActive()){
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
                                //do nothing
                            }
                            tetrominoX++;
                        }
                    }
                    else{
                        tetrominoX++;
                    }
                }
                tetrominoX = x;
                tetrominoY++;
            }
            lineClear();
            drawField();
            return true;
        }
    }

    private void insert(Minomino[][] m, Tetromino t){ //simpler version of the method above for saved and next3

    }

    public synchronized void drawField(){
        // Loop through each row of the field and print each Cube

        updateSaved();
        updateNext3();

        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        for (int i = 0; i < HEIGHT; i++) {
            System.out.print("<!");
            for (int j = 0; j < WIDTH; j++) {
                System.out.print(field[i][j].getSymbol()); // print the Cube
            }
            if(i >= 0 && i < 5){
                System.out.print("!>        " + saved[i]);
            }
            else if(i > 7 && i < 12){
                System.out.print("!>        " + next3[i-8]);
            }
            else{
                System.out.print("!>");
            }
            System.out.println(); // move to the next row
        }
        System.out.print("<!====================!>          score = " + score + "\n");
    }

    //made most sense to have this here since tennis is synchronized in main for both user and computer thread
    public Tetromino getNewTet(){
        Random random = new Random();
        int newtet = random.nextInt(7);
        Tetromino tetro;

        switch(newtet){
            case 0:
                return tetro = new Otetro();
            case 1:
                return tetro = new Itetro();
            case 2:
                return tetro = new Ttetro();
            case 3:
                return tetro = new Ltetro();
            case 4:
                return tetro = new Jtetro();
            case 5:
                return tetro = new Stetro();
            case 6:
                return tetro = new Ztetro();
            default:
                return tetro = new Otetro(); //so that the function can work
        }
    }

    public synchronized void saveTetro(Tetromino tetro){
        savedTetro = tetro;
    }

    public synchronized Tetromino getSavedTetro(){
        return savedTetro;
    }

    public synchronized Minomino[][] getPrevField(){
        return prevField;
    }

    public ArrayList<Minomino[][]> getHistory(){ //might not be used
        return prevFields;
    }

    public synchronized int getPrevX(){
        return prevX;
    }

    public synchronized int getPrevY(){
        return prevY;
    }

    public synchronized Tetromino getPrevTetro(){
        return prevTetro;
    }

    public void addPrevTetro(Tetromino t) {
        prevTetro = t;
    }

    public Boolean getGameOverState(){
        return gameOver;
    }

    public void changeGameState(Boolean b){
        gameOver = b;
    }

    private void updateSaved(){
        saved[0] = "<!SAVED===!>";
        for (int i = 1; i < 4; i++){
            String row = "<!";
            for (int j = 0; j < 4; j++){
                row += savedtetrofield[i][j].getSymbol();
            }
            row += "!>";
            saved[i] = row;
        }
        saved[5] = "<!========!>";
    }

    private void updateNext3(){
        //make saved tetromino field
        next3[0] = "<!NEXT=TETROMINOES================!>";
        for (int i = 1; i < 4; i++){
            String row = "<!";
            for (int j = 0; j < 12; j++){
                row += next3field[i][j].getSymbol();
            }
            row += "!>";
            next3[i] = row;
        }
        next3[5] = "<<!================================!>";
    }
}
