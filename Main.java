import java.util.Scanner;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static void Main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String userinput = scanner.nextLine();

        Timer timer = new Timer(); //so that the game advances automatically
        int intervalInSeconds = 3;

        timer.scheduleAtFixedRate(new TimerTask() {
            public void run(){
                if(userinput == "QUIT"){
                    timer.cancel();
                }
            }
        }, intervalInSeconds, intervalInSeconds);
    }
}