import java.util.Scanner;
import java.util.Random;

public class Main {

    public static void main(String[] args){
        //shared objects
        Random random = new Random();

        Tetromino square = new Otetro();
        Tennis tennis = new Tennis();

        //thread 1
        UserInputThread uit = new UserInputThread(tennis);
        uit.start();

        //thread 2
        UpdateTennisThread utt = new UpdateTennisThread(tennis);
        utt.start();

        try{
            uit.join();
            utt.join();
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
                currentY = tennis.getPrevY();
                currentTetro = tennis.getPrevTetro();
                userInput = scanner.nextLine().toLowerCase();

                switch (userInput) {
                    case "a":
                        currentX--;
                        tennis.insert(currentTetro, currentX, currentY);
                        break;
                    case "d":
                        currentX++;
                        tennis.insert(currentTetro, currentX, currentY);
                        break;
                    case "s":
                        currentY++;
                        tennis.insert(currentTetro, currentX, currentY);
                    case "x":
                        currentY = tennis.HEIGHT;
                        tennis.insert(currentTetro, currentX, currentY);
                        break;
                    case "r":
                        currentTetro.rotate();
                        tennis.insert(currentTetro, currentX, currentY);
                    default:
                        // Handle unknown command or invalid input
                        System.out.println("Unknown command: " + userInput);
                        break;
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
                currentY--;

                tennis.insert(null, currentX, currentY);

                time = time-10;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}