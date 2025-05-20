import java.util.Scanner;
import static java.lang.System.*;
import java.util.ArrayList;
import java.util.Random;
public class Game{
    //public ArrayList<Card> deck;
    public static void main(String[] args) {
        out.println("Welcome to Poker");
        Scanner scanner = new Scanner(System.in);
        
        out.println("You will only be able to play against the computer right now!");
        out.println("Have you played before? (y/n)");
        String input = scanner.nextLine();
        if(input.equals("y")){
            out.println("Great! Let's get started!");
        } else{
            out.println("No problem! Let's get started!");
        }

    }
    /**public static void shuffle(){
        Random random = new Random();
        deck = new ArrayList<Card>();
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        for (String suit : suits) {
            for (int i = 1; i <= 13; i++) {
                deck.add(new Card(suit, i));
            }
        }
        for (int i = 0; i < deck.size(); i++) {
            int randomIndexToSwap = random.nextInt(deck.size());
            Card tempCard = deck.get(i);
            deck.set(i, deck.get(randomIndexToSwap));
            deck.set(randomIndexToSwap, tempCard);

        }   
    }**/
}