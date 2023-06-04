import java.util.Scanner;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Main {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        //String userinput = scanner.nextLine();
        Random random = new Random();
        Tetromino currentTetro = new Itetro();
        Tetromino currentTetro2 = new Otetro();


        
        Tennis tennis = new Tennis();
        int currentX;
        int currentY;
        String userInput = "";

        int y = 0;
        for(int x = 0; x < tennis.WIDTH;x++){
            if(x<8){
                while(tennis.insert(currentTetro, x, y) == true){
                    y++;
                    if(tennis.insert(currentTetro, x, y)){
                        tennis.insert(currentTetro, x, y);
                        tennis.drawField();
                    }
                    else{
                        break;
                    }
                    try{
                        Thread.sleep(150);
                    }catch(Exception e){
                        System.out.println("shit happens");
                    }
                }
            }
            else{
                while(tennis.insert(currentTetro2, x, y) == true){
                    y++;
                    if(tennis.insert(currentTetro2, x, y)){
                        tennis.insert(currentTetro2, x, y);
                        tennis.drawField();
                    }
                    else{
                        break;
                    }
                    try{
                        Thread.sleep(50);
                    }catch(Exception e){
                        System.out.println("shit happens");
                    }
                }
            }
            tennis.drawField();
            y=0;
        }

        tennis.drawField();

    }
}