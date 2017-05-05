public class Casino
{
    public static void main(String[] args)
    {
        Player player = new Player();
        
        System.out.println("Hello, welcome to the Crispy Succotash Casino!");
        
        String playerInput = "blackjack"; //Placeholder stuff
        
        switch(playerInput)
        {
            case "blackjack": 
                Blackjack blackjack = new Blackjack();
                break;
            case "solitaire":
                
                break;    
            case "slots":
                
                break;
            default:
                System.out.println("Generic Text");
                break;
        }
    }
}
