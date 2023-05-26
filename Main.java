import java.util.Scanner;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Main {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        //String userinput = scanner.nextLine();
        Random random = new Random();
        Tetromino currentTetro;
        

        Tennis tennis = new Tennis();
        int currentX;
        int currentY;
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
    }
}