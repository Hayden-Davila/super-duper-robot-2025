import java.math.BigInteger;
public class Player {
    private String name;
    private BigInteger chips;
    private long wins;
    private long losses;
    private long ties;
    private int restarts;



    public Player(String name, String chips, long wins, long losses, long ties, int restarts) {
        this.name = name;
        this.chips = new BigInteger(chips);
        this.wins = wins;
        this.losses = losses;
        this.ties = ties;
        this.restarts = restarts;
    }
    public Player(String name) {
        this.name = name;
        this.chips = new BigInteger("1000");
        this.wins = 0;
        this.losses = 0;
        this.ties = 0;
        this.restarts = 0;
    }

    public String getname(){
        return name;
    }
    public BigInteger getChips(){
        return chips;
    }
    public long getWins(){
        return wins;
    }
    public long getLosses(){
        return losses;
    }

    public long getTies(){
        return ties;
    }
    public int getRestarts(){
        return restarts;
    }

    public void addChips(long c){
        this.chips = this.chips.add(new BigInteger(c+""));
    }
    public void subtractChips(long c){
        this.chips = this.chips.subtract(new BigInteger(c+""));
        if(this.chips.compareTo(BigInteger.ZERO) < 0){
            restarts();
        }
    }
    public void addChips(BigInteger chips){
        this.chips = this.chips.add(chips);
    }
    public void subtractChips(BigInteger chips){
        this.chips = this.chips.subtract(chips);
        if(this.chips.compareTo(BigInteger.ZERO) < 0){
            restarts();
        }
    }
    public void addWins(){
        this.wins++;
    }
    public void addLosses(){
        this.losses++;
    }
    public void addTies(){
        this.ties++;
    }
    public void restarts(){
        this.restarts++;
        this.chips = new BigInteger("1000");
    }

}
