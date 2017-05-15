import java.util.Scanner;
public class Blackjack extends Game
{
    public Blackjack(Player player)
    {
        if(player.checkWallet() < 2)
        {
            System.out.println("You must have a minimum of $2 to play this game, you have $" + player.checkWallet());
            System.out.println("Goodbye");
        }
        else
        {
            Player dealer = new Player("The Dealer", 9001);
            Player blue = new Player("Mr. Blue", 50);
            Player green = new Player("Mr. Green", 50);
            //player.clearHand();
        
            System.out.println("Welcome to Blackjack!");
            System.out.println("You have $" + player.checkWallet());
            System.out.println("Place your bet: ");
            Scanner reader = new Scanner(System.in);
            
            int moneyBet = reader.nextInt();
            
            
            PlayingCardDeck playingDeck = new PlayingCardDeck();
            System.out.println(playingDeck.toString());
        
            
        }
    }
}