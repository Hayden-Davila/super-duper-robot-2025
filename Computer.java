import java.util.ArrayList;
import java.util.Random;//Plan to use this for ranodmizing computer choice if time permits
public class Computer {

    public static int[] calculateMove(ArrayList<Card> computerHand, int round) {

        
        long computerScore = evaluateHand(computerHand);

        if(computerScore == Long.MAX_VALUE){//Royal Flush
            return new int[]{4, 50000, -1 , -1, -1};
        }
        if(computerScore >= 57657840L){//straight flush
            return new int[]{4, 10000, -1 , -1, -1};
        }
        if(computerScore >= 4100625L){//Four of a kind
            return new int[]{4, 1000, -1 , -1, -1};
        }
        if(computerScore >= 240301L){//full House
            return new int[]{4, 100, -1 , -1, -1};
        }
        if(computerScore == 240241L){//Flush
            return new int[]{4, 100,  -1 , -1, -1};
        }
        if(computerScore >= 120L){//Straight
            return new int[]{4, 100, 1, -1, -1};
        }
        if(computerScore >= 45L){//Three of a kind
            long cardNum = computerScore/3 - 13;
            return new int[]{3, 100, (int)cardNum, -1, -1};
        }
        if(computerScore >= 31){//Two pair
            return new int[]{4, 50, -1 , -1, -1};
        }
        if(computerScore >= 15){//pair
            long cardNum = computerScore - 13;
            return new int[]{3, 0, (int)(cardNum), -1, -1};
        }
        if(computerScore >= 2 && round == 1){//High card, round 2 fold
            return new int[]{2,0,-1,-1,-1};
        }
        //High card, round 1 no fold
        return new int[]{3, 0, 0, 1, 2};
        

    }
    //Logic to evaluate the hand
    //The higher the numner, the better the hand
    public static long evaluateHand(ArrayList<Card> hand) {
        long FLUSH = 240241;

        boolean isFlush = false; // checks to see if all suits are equal

        boolean isStraight = false; // checks to see if all valuse in a row are consecutive

        //If the above two are true, then the hand is a straight flush

        boolean isRoyal = false; // Checks to see if all values are face cards + a 10
        
        //If the above three are true, then the hand is a royal flush

        boolean isPair = false;//checks to see if there are two of the same value
        int pairNum = 0; //Value of the number making the pair
        boolean isPair2 = false;//Checks to see if there are another two of the same value
        int pairNum2 = 0; //Value of the number making the pair2
        boolean isThreeOfAKind = false;//checks to see if there are thrre of the same value
        int threeNum = 0; //Value of the number making the pair
        
        ArrayList<Card> copyHand = new ArrayList<Card>(hand);
        copyHand.sort(null);
        int sSum = 1;//Used if there is a straight
        for(int i = 1; i < copyHand.size(); i++){
            if(!(copyHand.get(i-1).getValue()+1 == copyHand.get(i).getValue())){
                break;
            }
            sSum *= copyHand.get(i).getValue();
            if(i==4){
                isStraight = true;
                if(copyHand.get(i).getValue() == 14){
                    isRoyal = true;
                }
            }
        
        }
        //Checks for a straight, then a flush
        for(int i = 1; i < copyHand.size(); i++){
            if(copyHand.get(i).getSuit() == copyHand.get(i-1).getSuit()){
                isFlush = true;
            } else {
                isFlush = false;
                break;
            }
        }
        if(isFlush && isStraight){
            if(isRoyal){
                return Long.MAX_VALUE; // Royal Flush
            }
            return (long) (sSum*(FLUSH+1)*2);
        }
        for(int i = 1; i < copyHand.size()-1; i++){
            if(!(copyHand.get(i).getValue() == copyHand.get(i-1).getValue())){
                break;
            }
            if(i==3){
                return (int)Math.pow((copyHand.get(i).getValue()+13)*3,4);
            }
        }
                for(int i = 2; i < copyHand.size(); i++){
            if(!(copyHand.get(i).getValue() == copyHand.get(i-1).getValue())){
                break;
            }
            if(i==4){
                return (int)Math.pow((copyHand.get(i).getValue()+13)*3,4);
            }
        }
        for(int i = 1; i < copyHand.size()-2; i++){
            if(!(copyHand.get(i).getValue() == copyHand.get(i-1).getValue())){
                break;
            }
            if(i == 2){
                isThreeOfAKind = true;
                threeNum = copyHand.get(i).getValue();
                for(int j = 0; j < copyHand.size(); j++){
                    copyHand.remove(0);
                }
            }
        }
                for(int i = 2; i < copyHand.size()-1; i++){
            if(!(copyHand.get(i).getValue() == copyHand.get(i-1).getValue())){
                break;
            }
            if(i == 3){
                isThreeOfAKind = true;
                threeNum = copyHand.get(i).getValue();
                for(int j = 0; j < copyHand.size(); j++){
                    copyHand.remove(1);
                }
            }
        }
                for(int i = 3; i < copyHand.size(); i++){
            if(!(copyHand.get(i).getValue() == copyHand.get(i-1).getValue())){
                break;
            }
            if(i == 4){
                isThreeOfAKind = true;
                threeNum = copyHand.get(i).getValue();
                for(int j = 0; j < copyHand.size(); j++){
                    copyHand.remove(2);
                }
            }
        }

        for(int i = 1; i < copyHand.size(); i++){
            if(!(copyHand.get(i).getValue() == copyHand.get(i-1).getValue())){
                break;
            }
            isPair = true;
            pairNum = copyHand.get(i).getValue();
            for(int j = 0;  j < copyHand.size(); j++){
                copyHand.remove(0);
                }
            }
            if(isPair && isThreeOfAKind){
                return (pairNum+13) + (threeNum+13)*3 + 240241L;
            }
        
            if(isFlush){
                return FLUSH;
            }
            if(isStraight){
                return sSum;
            }
            if(isThreeOfAKind){
                return (threeNum+13)*3;
            }
        for(int i = 1; i < copyHand.size(); i++){
                if(!(copyHand.get(i).getValue() == copyHand.get(i-1).getValue())){
                    break;
                }
            if(isPair){
                isPair2 = true;
                pairNum2 = copyHand.get(i).getValue();
                for(int j = 0; j < copyHand.size(); j++){
                    copyHand.remove(0);
                }
            }
            else{
                isPair = true;
                pairNum = copyHand.get(i).getValue();
                for(int j = 0;  j < copyHand.size(); j++){
                       copyHand.remove(0);
                }
            }
        
        }
        for(int i = 2; i < copyHand.size(); i++){
                if(!(copyHand.get(i).getValue() == copyHand.get(i-1).getValue())){
                    break;
                }
            if(isPair){
                isPair2 = true;
                pairNum2 = copyHand.get(i).getValue();
                for(int j = 0; j < copyHand.size(); j++){
                    copyHand.remove(0);
                }
            }
            else{
                isPair = true;
                pairNum = copyHand.get(i).getValue();
                for(int j = 0;  j < copyHand.size(); j++){
                       copyHand.remove(0);
                }
            }
        }
        for(int i = 3; i < copyHand.size(); i++){
                if(!(copyHand.get(i).getValue() == copyHand.get(i-1).getValue())){
                    break;
                }
            if(isPair){
                isPair2 = true;
                pairNum2 = copyHand.get(i).getValue();
                for(int j = 0; j < copyHand.size(); j++){
                    copyHand.remove(0);
                }
            }
            else{
                isPair = true;
                pairNum = copyHand.get(i).getValue();
                for(int j = 0;  j < copyHand.size(); j++){
                       copyHand.remove(0);
                }
            }
        }    
        if(isPair && isPair2){
            return (pairNum+13) + (pairNum2+13);
        }
        if(isPair){
            return (pairNum+13);
        }
        return (copyHand.get(copyHand.size()-1).getValue());
        

    }
 } 
