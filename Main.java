import java.util.Scanner;
import java.util.Random;

public class Main {

    public static void main(String[] args){
        //shared objects
        Random random = new Random();

        Tetromino square = new Otetro();
        Tennis tennis = new Tennis();
        tennis.addPrevTetro(square);

        //thread 1
        UserInputThread uit = new UserInputThread(tennis);
        uit.start();

        //thread 2
        UpdateTennisThread utt = new UpdateTennisThread(tennis);
        utt.start();

        try{
            utt.join();
            uit.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class UserInputThread extends Thread{
    private Tennis tennis;
    private Scanner scanner = new Scanner(System.in);
    private int currentX;
    private int currentY;
    private Tetromino currentTetro;

    public UserInputThread(Tennis t){
        tennis = t;
    }

    @Override
    public void run(){
        try{
            String userInput = "";
            while (!userInput.equalsIgnoreCase("quit")) {
                currentX = tennis.getPrevX();
                currentTetro = tennis.getPrevTetro();
                userInput = scanner.nextLine().toLowerCase();
                
                synchronized(tennis){
                    switch (userInput.toLowerCase()) {
                        case "a":
                            currentX--;
                            currentY = tennis.getPrevY();
                            tennis.insert(currentTetro, currentX, currentY);
                            tennis.drawField();
                            break;
                        case "d":
                            currentX++;
                            currentY = tennis.getPrevY();
                            tennis.insert(currentTetro, currentX, currentY);
                            tennis.drawField();
                            break;
                        case "s":
                            currentY = tennis.getPrevY();
                            currentY++;
                            if(tennis.insert(currentTetro, currentX, currentY)){
                                tennis.insert(currentTetro, currentX, currentY);
                            }
                            tennis.drawField();
                            break;
                        case "x":
                            currentY = tennis.HEIGHT;
                            tennis.insert(currentTetro, currentX, currentY);
                            tennis.drawField();
                            break;
                        case "r":
                            currentTetro.rotate();
                            currentY = tennis.getPrevY();
                            currentX = tennis.getPrevX();
                            tennis.insert(currentTetro, currentX, currentY);
                            tennis.drawField();
                            break;
                        case "quit":

                        default:
                            // Handle unknown command or invalid input
                            System.out.println("Unknown command: " + userInput);
                            break;
                    }
                    if (userInput.equalsIgnoreCase("quit")) {
                        break;
                    }
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}

class UpdateTennisThread extends Thread{
    private Tennis tennis;
    private long time;
    private int currentX;
    private int currentY;
    private Tetromino currentTetro;

    public UpdateTennisThread(Tennis ten){
        tennis = ten;
        time = 5000;
    }

    public void updateTetro(Tetromino t){
        currentTetro = t;
    }

    public void updateX(int x){
        currentX = x;
    }

    public void updateY(int y){
        currentY = y;
    }

    @Override
    public void run(){
        try{
            while (time >= 500){
                Thread.sleep(time);

                updateX(tennis.getPrevX());
                updateY(tennis.getPrevY());
                updateTetro(tennis.getPrevTetro());
                synchronized(tennis){
                    if (!tennis.insert(currentTetro, currentX, currentY)) {
                        // handle if the tetromino cannot be inserted anymore (it reached the bottom or other tetrominos)
                        // like getting a new random tetromino and resetting currentX and currentY to initial values
                    }
                    else{
                        tennis.insert(currentTetro, currentX, (currentY+1));
                        tennis.drawField();
                    }
                }
                time = time-10;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}