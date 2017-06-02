import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import java.util.Collections;

public class Blackjack
{
    private PlayingCardDeck playingDeck = new PlayingCardDeck();
    private ArrayList<Player> players = new ArrayList();
    private ArrayList<String> names = new ArrayList();
    private Scanner userInput = new Scanner(System.in);
    private String[] stringArrayNames = { "Billy", "Joe", "Suzy", "Kyle", "Guy", "Teddy" };
    private Player dealer = new Player("Bob The Dealer", 9001);
    private Player mainPlayer;
    private int mainPlayerIndex;
    private boolean playerWantsToPlay = true;
    
    public Blackjack(Player player)
    {
        this.mainPlayer = player;
        
        System.out.println("Welcome to Blackjack!");
        playGame();
    }
    
    public void playGame()
    {
        for (String string : stringArrayNames)
        {
            names.add(string);
        }
        setPlayers();
        while(playerWantsToPlay)
        {
            setBets();
            
            deal(); //deals two cards to each player then the dealer
            deal();
            
            playersTurn();
            
            dealersTurn();
            
            checkWinnings();
            checkIfPlayerWantsToPlay();
        }
    }
    
    public void setPlayers()
    {
        this.players.add(mainPlayer);
        int numberOfCpu = (int) (Math.random() * 5 + 1);
        for(int i = 0; i < numberOfCpu; i++)
        {
            this.players.add(new Player(getRandomName(), 50));
        }
        Collections.shuffle(players);
        for(int i = 0; i < players.size(); i++)
        {
            if(players.get(i).getName().equals(mainPlayer.getName()) && players.get(i).checkWallet() == mainPlayer.checkWallet())
            {
                mainPlayerIndex = i;
            }
        }
    }
    
    public void setBets()
    {
        double playerBet = 0;
        if(players.get(mainPlayerIndex).checkWallet() > 0)
        {
            System.out.println("You have $" + players.get(mainPlayerIndex).checkWallet() + ", what is your bet?");
            while(true)
            {
                playerBet = userInput.nextDouble();
                //System.out.println("Okay wise guy, I don't know what casinos you've been to, but at Crispy Succotash, you have to bet NUMBERS!");
                
                if(playerBet > players.get(mainPlayerIndex).checkWallet())
                {
                    System.out.println("You can't bet more than what you have!");
                }
                else if (playerBet < 1)
                {
                    System.out.println("You have to bet more than $0!");
                }
                else
                {
                    players.get(mainPlayerIndex).setBet(playerBet);
                    break;
                }
            }           
        }
        else
        {
            System.out.println("You don't have any money. I'm sorry, you have to leave. >:(");
        }
    }
    
    public void deal()
    {
        for(Player player : players)
        {
            hit(player);
        }
        if(dealer.cardsValue() < 1)
        {
            hit(dealer);
        }
        else //dealer draws a face down card after the first one
        {
            dealer.draw(playingDeck);
            System.out.println(dealer.getName() + " drew a face down card of unknown value.");
        }
    }
    
    public void hit(Player player)
    {
        player.draw(playingDeck);
        System.out.println(player.showHand());  
    }
    
    public void doubling(Player player)
    {
        hit(player);
        player.setBet(player.getBet() * 2);
    }
    
    public void surrender(Player player)
    {
        
    }
    
    public void playersTurn()
    {
        for(int i = 0; i < players.size(); i++)
        {
            if(players.get(i) == players.get(mainPlayerIndex)) //if its the human player
            {
                playerDecision();
            }
            else //it's a computer
            {
                computerDecision();
            }
        }
    }
    
    public void dealersTurn() //dealer reveals his down card and hits soft 17
    {
        System.out.println(dealer.getName() + " flipped over the face down card, revealing a(n) " + dealer.seeCard(1) + "."); //reveals second card
        System.out.println(dealer.showHand());
        while(true)
        {
            if(dealer.cardsValue() <= 17)
            {
                hit(dealer);
            }
            else
            {
                break;
            }
        }
    }
    
    public void checkStandings()
    {
        
    }
    
    public void playerDecision()
    {
        
    }
    
    public void computerDecision()
    {
        
    }
    
    public void checkWinnings() //check winnings at the end of the round
    {
        String status = "";
        for(Player player : players)
        {
            if(player.cardsValue() > 21)
            {
                //player busts
                status = player.getName() + " busts. Losing their bet of $" + player.getBet() + ".";
                player.subtractMoney(player.getBet());
            }
            else if(dealer.cardsValue() > 21)
            {
                //dealer busts
                status = dealer.getName() + " busts. Paying " + player.getName() + " their bet of $" + player.getBet() + ".";
                player.addMoney(player.getBet());
            }
            else if(dealer.cardsValue() == player.cardsValue())
            {
                //push
                status = "Push between " + player.getName() + " & " + dealer.getName() + " with a value of " + dealer.cardsValue() + "."; 
            }
            else if(player.cardsValue() == 21 && player.seeCard(2).equals(null)) //if there is no third card then the player definitley got a blackjack
            {
                //player blackjack
                status = player.getName() + " hit blackjack! Winning $" + player.getBet() * 1.5 + ".";
                player.addMoney(player.getBet() * 1.5);
            }
            else if(player.cardsValue() > dealer.cardsValue())
            {
                //player wins
                status = player.getName() + " won over " + dealer.getName() + " with " + player.cardsValue() + " to " + dealer.cardsValue() + "! " + player.getName() + " won their bet of $" + player.getBet() + ".";
                player.addMoney(player.getBet());
            }
            else if(dealer.cardsValue() == 21 && player.seeCard(2).equals(null)) //if there is no third card then the dealer definitley got a blackjack
            {
                //dealer blackjack
                status = dealer.getName() + " hit blackjack! " + player.getName() + " lost their bet of $" + player.getBet() + ".";
                player.subtractMoney(player.getBet());
            }
            else //dealer has more than player
            {
                //dealer wins
                status = dealer.getName() + " won over " + player.getName() + " with " + dealer.cardsValue() + " to " + player.cardsValue() + "! " + player.getName() + " lost their bet of $" + player.getBet() + ".";
                player.subtractMoney(player.getBet());
            }
            player.clearHand();
            System.out.println(status);
        }
    }
    
    public void checkIfPlayerWantsToPlay()
    {
        String playerResponse;
        System.out.println("You now have $" + players.get(mainPlayerIndex).checkWallet() + ", do you want to play again?");
        while(true)
        {
            playerResponse = userInput.next();
            switch(playerResponse)
            {
                case "yes":
                    break;
                case "no":
                    playerWantsToPlay = false;
                    break;
            }
        }
    }
    
    public String getRandomName()
    {
        return names.remove((int) (Math.random() * names.size())); //gets a random name for the computer and removes it to make sure it is not used again
    }
    
    public Player exit()
    {
        return players.get(mainPlayerIndex); //we return the human player in the array back to the casino
    }
}