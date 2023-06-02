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

        currentTetro2.setActive();

        
        Tennis tennis = new Tennis();
        int currentX;
        int currentY;
        String userInput = "";
        currentTetro.rotate();

        
        int x = 0;
        int y = 13;

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
                Thread.sleep(500);
            }catch(Exception e){
                System.out.println("shit happens");
            }
        }

        x=4;
        y=13;

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
                Thread.sleep(500);
            }catch(Exception e){
                System.out.println("shit happens");
            }
        }

        x=8;
        y=13;

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
                Thread.sleep(500);
            }catch(Exception e){
                System.out.println("shit happens");
            }
        }

        tennis.lineClear();
        tennis.drawField();

        /*
        Boolean test = true;

        int test1=14;
        int test2=17;

        while(test){
            test = false;
            int test3=test1+test2;
            System.out.println(test3);
        }

        while(tennis.insert(currentTetro, x, y) == true){
            y++;
            if(tennis.insert(currentTetro, x, y)){
                tennis.insert(currentTetro, x, y);
                tennis.drawField();
                currentTetro.rotate();
            }
            else{
                break;
            }
            try{
                Thread.sleep(500);
            }catch(Exception e){
                System.out.println("shit happens");
            }
        }

        y=14;
        Tetromino newTetro = new Otetro();
        tennis.drawField();
        x++;

        while(tennis.insert(newTetro, x, y) == true){
            if(tennis.insert(newTetro, x, y)){
                tennis.insert(newTetro, x, y);
                tennis.drawField();
            }
            else{
                break;
            }
            y++;
            try{
                Thread.sleep(500);
            }catch(Exception e){
                System.out.println("shit happens");
            }
        }

        y=14;
        tennis.drawField();

        while(tennis.insert(newTetro, 7, y) == true){
            if(tennis.insert(newTetro, 7, y)){
                tennis.insert(newTetro, 7, y);
                tennis.drawField();

            }
            else{
                break;
            }
            y++;
            try{
                Thread.sleep(500);
            }catch(Exception e){
                System.out.println("shit happens");
            }
        }

        y=14;
        tennis.drawField();

        while(tennis.insert(newTetro, 7, y) == true){
            if(tennis.insert(newTetro, 7, y)){
                tennis.insert(newTetro, 7, y);
                tennis.drawField();
            }
            y++;
            try{
                Thread.sleep(500);
            }catch(Exception e){
                System.out.println("shit happens");
            }
        }

        tennis.drawField();

        */
    }
}