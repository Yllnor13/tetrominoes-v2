public class Minomino {
    public String symbol = "[]";
    public Boolean isFull;
    public Boolean active;


    public Minomino(boolean isFull) {
        this.isFull = isFull;
        this.active = isFull;
    }

    public String toString(){
        return symbol;
    }

    public void settle(){
        active = false;
    }

    public void activate(){
        active = true;
    }

    public void erase(){
        isFull = false;
    }

    public void draw(){
        isFull = true;
    }

    public String getSymbol() {
        return isFull ? symbol : ". ";
    }

    public Boolean getActive(){
        return active;
    }

    public Boolean getIsFull(){
        return isFull;
    }
}
