import java.util.Scanner;

public class Blackjack
{
    private Player player;
    private Scanner userInput = new Scanner(System.in);
    
    public Blackjack(Player player)
    {
        this.player = player;
        
        System.out.println("Welcome to Blackjack!");
        playRound();
    }
    
    public void playRound()
    {
        setBets();
        
        
    }
    
    public void setBets()
    {
        double playerBet = 0;
        if(player.getMoney() > 0)
        {
            System.out.println("You have $" + player.getMoney() + ", what is your bet?");
            while(true)
            {
                playerBet = userInput.nextDouble();
                //System.out.println("Okay wise guy, I don't know what casinos you've been to, but at Crispy Succotash, you have to bet NUMBERS!");
                
                if(playerBet > player.getMoney())
                {
                    System.out.println("You can't bet more than what you have!");
                }
                else if (playerBet < 1)
                {
                    System.out.println("You have to bet more than $0!");
                }
                else
                {
                    player.setBet(playerBet);
                    break;
                }
            }           
        }
        else
        {
            System.out.println("You don't have any money. I'm sorry, you have to leave. >:(");
        }
    }
    
    public Player exit()
    {
        return player;
    }
}
