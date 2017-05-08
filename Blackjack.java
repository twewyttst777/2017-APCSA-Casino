import java.util.Scanner;
public class Blackjack extends Game
{
    public static void playBlackjack(Player player)
    {
        if(player.countMoney() < 2)
        {
            System.out.println("You must have a minimum of $2 to play this game, you have $" + player.countMoney());
            System.out.println("Goodbye");
        }
        else
        {
            
        
        
            Player dealer = new Player("The Dealer", 9001);
            Player blue = new Player("Mr. Blue", 50);
            Player green = new Player("Mr. Green", 50);
            player.clearHand();
        
            System.out.println("Welcome to Blackjack!");
            System.out.println("You have $" + player.countMoney());
            System.out.println("Place your bet: ");
            Scanner reader = new Scanner(System.in);
            
            int moneyBet = reader.nextInt();
            
            PlayingCardDeck playingDeck = new PlayingCardDeck();
        
        
        
        }
    }
}