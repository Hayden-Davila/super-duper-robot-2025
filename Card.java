public class Card {
    private String suit;
    private int value;
    private String name;

    public Card(String suit, int value) {
        this.suit = suit;
        if(value == 1){
            this.name = "Ace";
        } else if(value == 11){
            this.name = "Jack";
        } else if(value == 12){
            this.name = "Queen";
        } else if(value == 13){
            this.name = "King";
        } else {
            this.name = value+"";
        }
        this.value = value;
    }

    public String getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }
    public String getName() {
        return name;
    }
    public String toString() {
        return name + " of " + suit;
    }
}
