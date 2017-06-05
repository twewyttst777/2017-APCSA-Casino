package hurrnrn;

import java.util.*;
import java.awt.Window.*;
public class Bingo{
    private ArrayList<Integer> called = new ArrayList<Integer>();
    private ArrayList<CardGraphic> extra = new ArrayList<CardGraphic>();
    private boolean won;
    private boolean enemyWon;
    private int pool;
    private String[] names = {"Skyler", "Tim", "Gheirrhett", "John", "Ryan", "Paul", "Austin", "The Spanish Inquisition"};
    private String gameMode;
    /**
     * Makes a bingo class so you can play bingo
     */
    public Bingo(){
        
    }

    /**
     * When you call this, it starts the bingo game. Cannot be called statically.
     */
    public void playBingo(Player player){
        called.add(0);
        boolean keepPlaying = true;
        Scanner reader = new Scanner(System.in);
        System.out.println("Welcome to Bingo!");
        String[] names = {"Skyler", "Tim", "Gheirrhett", "John", "Ryan", "Paul", "Austin", "The Spanish Inquisition"};
        do{
            won = false;
            enemyWon = false;
            System.out.println("How many cards would you like to buy?");
            int cardNumber = reader.nextInt();
            reader.nextLine(); //Can't do a nextLine() immediately after a nextInt() for reasons so we fire off a blank one.
            player.addMoney(-cardNumber * 5);
            pool += cardNumber * 5;
            int competitors = (int) (Math.random() * 12) + 1;
            BingoCard[] enemyCards = new BingoCard[competitors];
            for(int i = 0; i <competitors; i++){
                enemyCards[i] = new BingoCard();
            }
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
            int numsCalled = 0;
            for(int i = 0; i < cardNumber; i++){
                playerCards[i] = new BingoCard();
                extra.add(new CardGraphic(playerCards[i], this));
            }
            while(!won && !enemyWon){
                numsCalled ++;
                called.add(numbers.get(0));
                System.out.println(numbers.remove(0));
                for(BingoCard enemies : enemyCards){
                    checkEnemyWinCondition(enemies);
                }
                try        
                {
                    Thread.sleep(3000);
                } 
                catch(InterruptedException ex) 
                {
                    Thread.currentThread().interrupt();
                }
            }
            if(won){
                System.out.println("You won $" + pool + "!");
                player.addMoney(pool);
            } else {
                System.out.println(names[(int) (Math.random() * 8)] + " won. Better luck next time!");
            }
            boolean correct = false;
            System.out.println("Keep Playing or nah");
            
            while(!correct){
                String playerInput = reader.nextLine().toLowerCase();
                switch(playerInput){
                    case "y":
                    correct = true;
                    break;

                    case "n":
                    System.out.println("Bye");
                    return;

                    case "yea":
                    correct = true;
                    break;

                    case "nah":
                    System.out.println("Bye");
                    return;

                    case "keep playing":
                    correct = true;
                    break;

                    default:
                    System.out.println("Sorry, I don't understand what you're saying. Say it again but different.");
                }
            }
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
                if(checkHorizontalRow(winningCard, i) || checkVerticalRow(winningCard, i)){
                    won = true;
                    return;
                }
            }

            for(int i = 0; i < 5; i++){
                if(!hasBeenCalled(winningCard.getNumber(i,i))){
                    won = false;
                }
            }

            for(int i = 0; i > 5; i++){
                if(!hasBeenCalled(winningCard.getNumber(i, 4 - i))){
                    won = false;
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

    public void checkEnemyWinCondition(BingoCard winningCard){
        switch(gameMode){
            case "four corners":
            if(hasBeenCalled(winningCard.getNumber(0,0)) && hasBeenCalled(winningCard.getNumber(0,4)) && hasBeenCalled(winningCard.getNumber(4,0)) && hasBeenCalled(winningCard.getNumber(4,4))){
                enemyWon = true;
                return;
            }
            enemyWon = false;
            return;
            case "blackout":
            for(int r = 0; r < 5; r++){
                for(int c = 0; c < 5; c++){
                    if(!hasBeenCalled(winningCard.getNumber(r,c))){
                        enemyWon = false;
                        return;
                    }
                }
            }
            enemyWon = true;
            return;
            case "line":
            for(int i = 0; i < 5; i++){
                if(checkHorizontalRow(winningCard, i) || checkVerticalRow(winningCard, i)){
                    enemyWon = true;
                    return;
                }
            }

            for(int i = 0; i < 5; i++){
                if(!hasBeenCalled(winningCard.getNumber(i,i))){
                    enemyWon = false;
                }
            }

            for(int i = 0; i > 5; i++){
                if(!hasBeenCalled(winningCard.getNumber(i, 4 - i))){
                    enemyWon = false;
                }
            }
            enemyWon = true;
            return;

            case "cross":
            for(int i = 0; i < 5; i++){
                if(!(hasBeenCalled(winningCard.getNumber(i,i)) && hasBeenCalled(winningCard.getNumber(4-i,i)))){
                    enemyWon = false;
                    return;
                }
            }
            enemyWon = true;
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