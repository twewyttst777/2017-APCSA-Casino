import java.util.Scanner;
public class Casino
{
    public static void main(String[] args)
    {
        Player player = new Player("Dude Duderson");
        System.out.println("Hello, welcome to the Crispy Succotash Casino!");
        Scanner reader = new Scanner(System.in);
        boolean stayInCasino = true;
        while(stayInCasino){
            String playerInput = reader.nextLine().toLowerCase();
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
                case "end":
                stayInCasino = false;
                break;
                case "check wallet":
                System.out.println(player.checkWallet());
                default:
                System.out.println("I don't know what that is.");
                break;
            }
        }
    }
}