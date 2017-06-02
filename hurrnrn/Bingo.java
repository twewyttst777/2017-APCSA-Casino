import java.util.*;
import java.awt.Window.*;
public class Bingo{
    private ArrayList<Integer> called = new ArrayList<Integer>();
    private ArrayList<CardGraphic> extra = new ArrayList<CardGraphic>();
    private boolean won;
    private String gameMode;
    public void playBingo(Player player){
        called.add(0);
        Scanner reader = new Scanner(System.in);
        System.out.println("Welcome to Bingo!");
        boolean keepPlaying = true;
        
        do{
            won = false;
            System.out.println("How many cards would you like to buy?");
            int cardNumber = reader.nextInt();
            ArrayList<Integer> numbers = new ArrayList<Integer>();
            for(CardGraphic toBeCleared : extra){
                toBeCleared.kill();
            }
            for(int œ = 1; œ <= 75; œ++){
                numbers.add(œ);
            }
            switch((int) (Math.random() * 4)){
                case 0:
                gameMode = "four corners";
                break;

                case 1:
                gameMode = "blackout";
                break;

                case 2:
                gameMode = "line";
                break;

                case 3:
                gameMode = "cross";
                break;
            }
            System.out.println("The game mode is " + gameMode);
            Collections.shuffle(numbers);
            BingoCard[] playerCards = new BingoCard[cardNumber];
            for(int i = 0; i < cardNumber; i++){
                playerCards[i] = new BingoCard();
                extra.add(new CardGraphic(playerCards[i], this));
            }
            int numsCalled = 0;
            while(!won){
                called.add(numbers.get(0));
                numsCalled++;
                System.out.println(numbers.remove(0) + " " + numsCalled);
                try        
                {
                    Thread.sleep(2000);
                } 
                catch(InterruptedException ex) 
                {
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println("You win!");
        } while(keepPlaying);
    }

    public void checkWinCondition(BingoCard winningCard){
        switch(gameMode){
            case "four corners":
            if(hasBeenCalled(winningCard.getNumber(0,0)) && hasBeenCalled(winningCard.getNumber(0,4)) && hasBeenCalled(winningCard.getNumber(4,0)) && hasBeenCalled(winningCard.getNumber(4,4))){
                won = true;
                return;
            }
            won = false;
            return;
            case "blackout":
            for(int r = 0; r < 5; r++){
                for(int c = 0; c < 5; c++){
                    if(!hasBeenCalled(winningCard.getNumber(r,c))){
                        won = false;
                        return;
                    }
                }
            }
            won = true;
            return;
            case "line":
            for(int i = 0; i < 5; i++){
                if(!checkHorizontalRow(winningCard, i) || !checkVerticalRow(winningCard, i)){
                    won = false;
                    return;
                }
            }

            for(int i = 0; i < 5; i++){
                if(!hasBeenCalled(winningCard.getNumber(i,i))){
                    won = false;
                    return;
                }
            }

            for(int i = 0; i > 5; i++){
                if(!hasBeenCalled(winningCard.getNumber(i, 4 - i))){
                    won = false;
                    return;
                }
            }
            won = true;
            return;
            
            case "cross":
            for(int i = 0; i < 5; i++){
                if(!(hasBeenCalled(winningCard.getNumber(i,i)) && hasBeenCalled(winningCard.getNumber(4-i,i)))){
                    won = false;
                    return;
                }
            }
            won = true;
            return;
        }
    }
    
    public boolean hasBeenCalled(int num){        
        for(int i : called){
            if(num == i){
                return true;
            }
        }
        return false;
    }

    public boolean checkHorizontalRow(BingoCard card, int row){
        for(int i = 0; i < 5; i++){
            if(!hasBeenCalled(card.getNumber(i, row))){
                return false;
            }
        }
        return true;
    }

    public boolean checkVerticalRow(BingoCard card, int col){
        for(int i = 0; i < 5; i++){
            if(!hasBeenCalled(card.getNumber(col, i))){
                return false;
            }
        }
        return true;
    }
}