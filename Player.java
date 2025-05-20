public class Player {
    private String name;
    private int chips;

    public Player(String name, int chips) {
        this.name = name;
        this.chips = chips;
    }

    public String getname(){
        return name;
    }
    public int getChips(){
        return chips;
    }
}
