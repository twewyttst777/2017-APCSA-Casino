import java.util.Scanner;
public class Casino
{
    public static void main(String[] args)
    {
        Player player = new Player("Dude Duderson", 50);
        System.out.println("Hello, welcome to the Crispy Succotash Casino!");
        Scanner reader = new Scanner(System.in);
        String playerInput = reader.next();
        
        switch(playerInput)
        {
            case "blackjack": 
                Blackjack blackjack = new Blackjack(player);
                break;
            case "solitaire":
                
                break;    
            case "slots":
                
                break;
            case "keno":
                Keno keno = new Keno();
                keno.playKeno(player);
                break;
            default:
                System.out.println("Generic Text");
                break;
        }
    }
}