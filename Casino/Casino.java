import java.util.Scanner;

public class Casino
{
    public static void main(String[] args)
    {
        Player player = new Player();
        
        System.out.println("Hello, welcome to the Crispy Succotash Casino!");
        
        String playerInput = "blackjack"; //Placeholder stuff
        
        switch(playerInput)
        {
            case "help":
                System.out.println("There is Keno and Blackjack.");
                break;
            case "blackjack": 
                Blackjack blackjack = new Blackjack(player);
                player = blackjack.exit();
                break;
            case "keno":
                Keno keno = new Keno();
                keno.playKeno(player);
                break;    
            case "slots":
                
                break;
            default:
                System.out.println("You can type \"help\" for help.");
                break;
        }
    }
}
