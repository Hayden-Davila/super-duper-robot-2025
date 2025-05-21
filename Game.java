import java.util.Scanner;
import static java.lang.System.*;
import java.util.ArrayList;
import java.util.Random;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
public class Game{
    
    public static void main(String[] args) {
        out.println("Welcome to Poker");
        Scanner scanner = new Scanner(System.in);
        Player player;
        out.println("You will only be able to play against the computer right now!");
        out.println("Have you played before? (y/n)");
        String input = scanner.nextLine();
        if(input.equals("y")){
            out.println("Great! Do you have a save file! (y/n)");
            input = scanner.nextLine();
            if(input.equals("y")){
                out.println("give me your username");
                String username = scanner.nextLine();
                player = loadGame(username);

            }
            else{
                out.println("No problem! Let's get started!");
                out.println("What is your name?");
                String name = scanner.nextLine();
                player = new Player(name);
            }
        } else{
            out.println("No problem! Let's get started!");
            out.println("What is your name?");
            String name = scanner.nextLine();
            player = new Player(name);
        }
        
        out.println("Do you know the rules of poker? (y/n)");
        input = scanner.nextLine();
        if(input.equals("y")){
            out.println("great!");
        }
        else{
            out.println("Don't worry! I will explain the rules to you!");
            rules();
        }
        out.println("After every game you may review the rules, stats, or save the game, or quit the game.");
        out.println("We will not save for you, so please save every now and then.");
        while(true){
            
            out.println("type save to save the game, type rules to review the rules, type stats to see your stats, type quit to quit the game, or type play to play.");
            input = scanner.nextLine();
            if(input.equals("save")){
                saveGame(player);
                out.println("Game saved!");
            }
            else if(input.equals("rules")){
                rules();
            }
            else if(input.equals("stats")){
                out.println("Your name is: "+player.getname());
                out.println("You have: "+player.getChips()+" chips");
                out.println("You have won: "+player.getWins()+" games");
                out.println("You have lost: "+player.getLosses()+" games");
                out.println("You have tied: "+player.getTies()+" games");
                out.println("You have restarted: "+player.getRestarts()+" times");
            }
            else if(input.equals("quit")){
                out.println("Thanks for playing!");
                break;
            }
            else if(input.equals("play")){
                play(player);
            }
            else{
                out.println("Invalid input! Please try again.");
            }

            delay(2000);
            out.println("\n\n\n");
            delay(2000);

        }
        

    }
    //saving the Game
    public static void saveGame(Player player){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(player.getname()+".txt"));
            writer.write(player.getname());
            writer.newLine();
            writer.write(player.getChips().toString());
            writer.newLine();
            writer.write(player.getWins()+"");
            writer.newLine();
            writer.write(player.getLosses()+"");
            writer.newLine();
            writer.write(player.getTies()+"");
            writer.newLine();
            writer.write(player.getRestarts()+"");
            writer.close();
        } catch (IOException e) {
            out.println("Error saving game: " + e.getMessage());
        }
    }

    //loading the game
    public static Player loadGame(String username){
        try {
            File file = new File(username+".txt");
            Scanner scanner = new Scanner(file);
            String name = scanner.nextLine();
            String chips = scanner.nextLine();
            long wins = Long.parseLong(scanner.nextLine());
            long losses = Long.parseLong(scanner.nextLine());
            long ties = Long.parseLong(scanner.nextLine());
            int restarts = Integer.parseInt(scanner.nextLine());
            out.println("Welcome back " + name + "!");
            
            return new Player(name, chips, wins, losses, ties, restarts);

        } catch (FileNotFoundException e) {
            out.println("Error loading game: " + e.getMessage());
            out.println("please restart the game and create a new account.");
            out.println("If you have an account, please make sure the file is in the same directory as the game.");
            throw new Error("File not found: " + username + ".txt");
        }
        
    }
    //rules
    public static void rules(){

            out.println("We will be playing five card poker.");
            out.println("You will be dealt five cards and you will have to make the best hand possible.");
            out.println("You will be playing against the computer.");
            out.println("The computer will also be dealt five cards.");
            delay(4000);
            out.println("You will be able to see two of the computer's cards for decsion making purposes.");
            out.println("Before being dealt the cards, you will put in 50 chips to the pot.");
            out.println("we will have a two intervals of betting.");
            delay(4000);
            out.println("During which you will be able to raise, call, or fold.");
            out.println("The computer will also be able to raise, call, or fold.");

            delay(4000);
            out.println("The winner will be the one with the best hand.");
            out.println("Winning gives you the pot and losing removes the chips form your account.");
            out.println("You will forced to restart if you run out of chips.");
            delay(4000);
            out.println("You can bet more than you have in your account, but you will be forced to restart if you lose.");
            out.println("If you fold and bet more chips than you have, you will be forced to restart.");
            delay(4000);

            out.println("The hands have numerical rankings used for comparisons.");
            out.println("Due to my inability to implement every possible hand for two pairs, there will be occasions where wins and ties may occur to a player with a lower hand.");
            out.println("This is a result of the math I used to calculate the hands.");
            delay(4000);

    }


    //shuffling the deck
    public static ArrayList<Card> shuffle(){
        ArrayList<Card> deck = new ArrayList<Card>();
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        for(String suit : suits){
            for(int i = 2; i <= 14; i++){
                deck.add(new Card(suit, i));
            }
        }
        Random rand = new Random();
        for(int i = 0; i < deck.size(); i++){
            int j = rand.nextInt(deck.size());
            Card temp = deck.get(i);
            deck.set(i, deck.get(j));
            deck.set(j, temp);
        }
        return deck;
    }

    //playing the game
    public static void play(Player player){
        Scanner scanner = new Scanner(System.in);
        ArrayList<Card> deck = shuffle();
        int pot = 100;
        int playerBet = 50;

        int round = 0;
        out.println("50 chips have been take from your account.");
        out.println("100 chips are in the pot.");
        out.println("You have " + player.getChips().subtract(new BigInteger(playerBet+"")) + " chips left.");

        out.println("Dealing cards...");
        delay(2000);
        ArrayList<Card> playerHand = new ArrayList<Card>();
        ArrayList<Card> computerHand = new ArrayList<Card>();
        for(int i = 0; i < 5; i++){
            playerHand.add(deck.remove(0));
            computerHand.add(deck.remove(0));
        }

        while(true){
            out.println("Your hand: ");
            for(Card card : playerHand){
            out.println(card);
            }
            delay(4000);
            out.println("\n\n\n");
            out.println("2 of the Computer's cards: ");
                for(int i = 0; i < 2; i++){
                out.println(computerHand.get(i));
            }
            delay(2000);
            out.println("\n\n\n");
                out.println("Do you to call? (up to 3)? (y/n)");
                String input = scanner.nextLine();
                if(input.equals("y")){
                    while(true){
                    out.println("Enter the index of the card (1-" + playerHand.size()+") or q to quit. (index 1 is at the top)");
                    input = scanner.nextLine();
                    if(input.equals("q")){
                        
                        break;
                    }
                    int index = Integer.parseInt(input);
                    playerHand.remove(index-1);
                    if(playerHand.size() == 2){
                        out.println("Dealing your new cards...");
                        break;
                    }
                    }
            }

        
        while(playerHand.size()<5){
            playerHand.add(deck.remove(0));
        }
        out.println("Your hand: ");
        for(Card card : playerHand){
            out.println(card);
        }
        out.println("\n\n\n");
        delay(2000);
        out.println("Do you want to raise, or fold or stay?");
        input = scanner.nextLine();
        if(input.equalsIgnoreCase("raise")){
            out.println("How much");
            input = scanner.nextLine();
            int bet = Integer.parseInt(input);
            pot += (bet*2);
            System.out.println("Done! the pot is now " + pot);
            playerBet += bet;
        }

        if(input.equalsIgnoreCase("fold")){
            out.println("You folded.");
            out.println("you lose the pot.");
            player.addLosses();
            player.subtractChips(playerBet);
            
            return;
        }
        if(input.equalsIgnoreCase("stay")){
            out.println("You stayed.");
            out.println("The pot is now " + pot);
        }
        out.println("computer's turn...");
        delay(2000);
        int[] computerActions = Computer.calculateMove(computerHand, round);
        if(computerActions[0]==2){
            out.println("Computer Folded.");
            out.println("You win the pot!");
            player.addChips(pot-playerBet);
            player.addWins();
            
            return;
        }
        if(computerActions[0]==3){
            out.println("The computer is drawing cards...");
            delay(2000);
            if(computerActions[2]==0){
                for(int i = 0; i < 3; i++){
                    computerHand.remove(0);
                }
                for(int i = 0; i < 3; i++){
                    computerHand.add(deck.remove(0));
                }
                
            }
            if(computerActions[2]>0){
                for(int i = 0; i < computerHand.size(); i++){
                    if(!(computerHand.get(i).getValue() == computerActions[2])){
                        computerHand.remove(i);
                    }
                }
                while(computerHand.size()<5){
                    computerHand.add(deck.remove(0));
                }
            }
            
        }
        if(computerActions[1] > 0){
            out.println("The computer raised " + computerActions[1] + " chips.");
            out.println("Do you wish to match or fold?");
            input = scanner.nextLine();
            if(input.equalsIgnoreCase("fold")){
                out.println("You folded.");
                out.println("You lose the pot.");
                player.addLosses();
                player.subtractChips(pot);
                
                return;
             }
                else{
                    out.println("You matched the bet.");
                    pot += computerActions[1];
                    playerBet += computerActions[1];
                }
            }
            out.println("\n\n\n\n\n\n");
        delay(4000);
            round++;
            if(round == 2){
                break;
            }
        }
        out.println("The pot is now " + pot);
        out.println("The computer's hand: ");
        for(Card card : computerHand){
            out.println(card);
        }
        out.println("\n\n\n");
        delay(2000);
        out.println("Your hand: ");
        for(Card card : playerHand){
            out.println(card);
        }
        delay(2000);
        out.println("Calculating winner...");
        delay(2000);
        long playerHandValue = Computer.evaluateHand(playerHand);
        long computerHandValue = Computer.evaluateHand(computerHand);
        //Debug
        out.println("Your hand value: " + playerHandValue);
        out.println("Computer's hand value: " + computerHandValue);
        if(playerHandValue > computerHandValue){
            out.println("You win the pot!");
            player.addChips(pot-playerBet);
            player.addWins();
        }
        else if(playerHandValue < computerHandValue){
            player.addLosses();
            player.subtractChips(playerBet);
        }
        else{
            out.println("It's a tie!");
            player.addTies();
        }
        

    }
    

    //delays game
    public static void delay(int milliseconds){
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            out.println("Error: " + e.getMessage());
        }


    }
}
