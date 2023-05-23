import java.util.Scanner;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Main {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        //String userinput = scanner.nextLine();

        Random random = new Random();

        Timer timer = new Timer(); //so that the game advances automatically
        int intervalInSeconds = 3;

        Tetromino square = new Otetro();
        Tennis field = new Tennis();

        int x = random.nextInt(9);
        int y = 0;
        
        while (y < 19) {
            System.out.println(x);
            System.out.println(y);

            x = random.nextInt(9);
            
            field.drawField();
            field.insert(square, x, y);
            y++;
        
            try {
                Thread.sleep(1000); // Delay for 1 second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run(){
                /*
                if(x==10){
                    timer.cancel();
                }
                
                if(userinput == "QUIT"){
                    timer.cancel();
                }
                */
            }
        }, intervalInSeconds, intervalInSeconds);
    }
}