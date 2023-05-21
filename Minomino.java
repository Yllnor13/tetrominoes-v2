public class Minomino {
    public String symbol = "[]";
    public Boolean isFull = false;
    public Boolean active = true;


    public Minomino(boolean isFull) {
        this.isFull = isFull;
    }

    public String toString(){
        return symbol;
    }

    public void settle(){
        active = !active;
    }

    public String getSymbol() {
        return isFull ? symbol : ". ";
    }
}
