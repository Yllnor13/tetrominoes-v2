import java.util.Scanner;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Main {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        //String userinput = scanner.nextLine();
        Random random = new Random();
        Tetromino currentTetro = new Stetro();
        Tetromino currentTetro2 = new Otetro();

        currentTetro2.setActive();

        currentTetro.debugPrint();

        currentTetro.rotate();

        currentTetro.debugPrint();

        currentTetro.rotate();

        currentTetro.debugPrint();

        
        Tennis tennis = new Tennis();
        int currentX;
        int currentY;
        String userInput = "";

        
        int x = 0;
        int y = 15;

        while(tennis.insert(currentTetro, x, y) == true){
            y++;
            tennis.insert(currentTetro, x, y);
            tennis.drawField();
            try{
                Thread.sleep(500);
            }catch(Exception e){
                System.out.println("shit happens");
            }
        }

        y=14;
        Tetromino newTetro = new Otetro();

        while(tennis.insert(newTetro, x, y) == true){
            tennis.insert(newTetro, x, y);
            y++;
            tennis.drawField();
            try{
                Thread.sleep(500);
            }catch(Exception e){
                System.out.println("shit happens");
            }
        }
    }
}