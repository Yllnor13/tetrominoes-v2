import java.util.Scanner;
import java.util.Random;

public class Main {

    public static void main(String[] args){
        //shared objects

        Tennis tennis = new Tennis();

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

    public synchronized void inserter(){
        if(tennis.insert(currentTetro, currentX, currentY) && !tennis.getGameOverState()){
            tennis.insert(currentTetro, currentX, currentY);
        }
        else if(!tennis.getGameOverState()){
            currentTetro = tennis.getNewTet();
            currentX = 5 - (currentTetro.getWidth()/2);
            tennis.insert(currentTetro, currentX, 0);
        }
    }

    @Override
    public void run(){
        try{
            String userInput = "";
            while (!userInput.equalsIgnoreCase("quit")) {
                userInput = scanner.nextLine().toLowerCase();
                
                synchronized(tennis){
                    currentX = tennis.getPrevX();
                    currentTetro = tennis.getPrevTetro();
                    switch (userInput.toLowerCase()) {
                        case "a":
                            currentX--;
                            currentY = tennis.getPrevY();
                            inserter();
                            break;
                        case "d":
                            currentX++;
                            currentY = tennis.getPrevY();
                            inserter();
                            break;
                        case "s":
                            currentY = tennis.getPrevY();
                            currentY++;
                            inserter();
                            break;
                        case "x":
                            for(int x = tennis.getPrevY(); x<tennis.HEIGHT;x++){
                                currentY = x;
                                if(tennis.insert(currentTetro, currentX, currentY)){
                                    tennis.insert(currentTetro, currentX, currentY);
                                }
                                else{
                                    currentTetro = tennis.getNewTet();
                                    tennis.insert(currentTetro, 5, 0);
                                    break;
                                }
                                
                            }
                        case "r":
                            currentTetro.rotate();
                            currentY = tennis.getPrevY();
                            currentX = tennis.getPrevX();
                            inserter();
                            break;
                        case "q":
                            tennis.saveTetro(currentTetro);
                            inserter();
                            break;
                        case "restart":
                            tennis.wipe();
                            tennis.changeGameState(true);
                            inserter();
                            break;
                        case "quit":
                            System.exit(0);
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
    private Boolean isprinted = false;

    public UpdateTennisThread(Tennis ten){
        tennis = ten;
        time = 1000;
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

    public synchronized void inserter(){
        if(tennis.insert(currentTetro, currentX, currentY) && !tennis.getGameOverState()){//starten av spillet
            tennis.insert(currentTetro, currentX, currentY);
            isprinted = false;
        }
        else if (!tennis.getGameOverState()){
            updateTetro(tennis.getNewTet());
            updateX(5-(currentTetro.getWidth()/2));
            tennis.insert(currentTetro, currentX, 0);
        }
        else{//slutten av spillet
            if(!isprinted){
                isprinted = true;
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n                    GAME OVER.\n\n\nIF YOU WISH TO START A NEW GAME, TYPE 'RESTART'");
            }
        }
    }

    public void passTime(){
        try{
            Thread.sleep(time);
            synchronized(tennis){
                updateX(tennis.getPrevX());
                updateY(tennis.getPrevY());
                currentY++;
                updateTetro(tennis.getPrevTetro());
                inserter();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run(){
        while (time > 200){
            passTime();
            time = time-10;
        }
        while(true){
            passTime();
        }
    }
}