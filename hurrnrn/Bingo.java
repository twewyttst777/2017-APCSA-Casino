import java.util.*;
public class Bingo{
    public void playBingo(Player player){
        Scanner reader = new Scanner(System.in);
        System.out.println("Welcome to Bingo!");
        boolean keepPlaying = true;
        do{
            System.out.println("How many cards would you like to buy?");
            int cardNumber = reader.nextInt();
            ArrayList<Integer> numbers = new ArrayList<Integer>();
            ArrayList<Integer> drawnNumbers = new ArrayList<Integer>();
            for(int œ = 1; œ <= 75; œ++){
                numbers.add(œ);
            }
            Collections.shuffle(numbers);
            BingoCard[] playerCards = new BingoCard[cardNumber];
            for(int i = 0; i < cardNumber; i++){
                playerCards[i] = new BingoCard();
            }
            drawnNumbers.add(numbers.get(0));
            System.out.println(numbers.remove(0));
        } while(keepPlaying);
    }

    public boolean checkWinCondition(BingoCard winningCard, String gameMode, int[] drawn){
        switch(gameMode){
            case "four corners":
                
            break;

            case "blackout":

            break;

            case "line":

            break;

            case "cross":

            break;
        }
        return false;
    }

    public boolean checkHorizontalRow(BingoCard card, int[] drawn){
        return false;
    }

    public boolean checkVerticalRow(BingoCard card, int[] drawn){
        return false;
    }
}
