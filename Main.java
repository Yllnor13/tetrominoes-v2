import java.util.Scanner;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Main {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String userinput = scanner.nextLine();

        Random random = new Random();

        Timer timer = new Timer(); //so that the game advances automatically
        int intervalInSeconds = 3;

        Tetromino square = new Otetro();
        Tennis field = new Tennis();

        int x = random.nextInt(9);
        int y = 0;
        
        while (y < 24) {

            x = random.nextInt(13);
            
            field.drawField();
            field.insert(square, x, y);
            field.drawField();
            field.insert(square, -5, y);
            field.drawField();
            field.insert(square, 13, y);

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

    class UpdateTennisThread extends Thread{
        private Tennis tennis;
        private long time;
        private int currentX;
        private int currentY;
        private Tetromino currentTetro;

        public UpdateTennisThread(Tennis ten, int x, int y, Tetromino tet){
            tennis = ten;
            time = 5000;
            currentX = x;
            currentY = y;
            currentTetro = tet;
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
                    currentY--;

                    tennis.insert(null, currentX, currentY);

                    time = time-10;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}