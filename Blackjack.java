import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import java.util.Collections;

public class Blackjack
{
    private ArrayList<Player> players = new ArrayList();
    private PlayingCardDeck playingDeck = new PlayingCardDeck();
    private Scanner userInput = new Scanner(System.in);
    private Player dealer;
    private boolean playerWantsToPlay = true;
    
    public Blackjack(Player player)
    {
        this.players.add(player);
        
        
        System.out.println("Welcome to Blackjack!");
        playGame();
    }
    
    public void playGame()
    {
        setPlayers();
        while(playerWantsToPlay)
        {
            dealer = new Player("Bob The Dealer", 9001);
            setBets();
            
            playRound();
            playRound();
            
            checkValues();
            checkIfPlayerWantsToPlay();
        }
    }
    
    public void setPlayers()
    {
        int numberOfCpu = (int) (Math.random() * 5 + 1);
        for(int i = 0; i < numberOfCpu; i++)
        {
            this.players.add(new Player("Guy"+i, 50));
        }
        Collections.shuffle(players);
    }
    
    public void setBets()
    {
        double playerBet = 0;
        if(players.get(0).checkWallet() > 0)
        {
            System.out.println("You have $" + players.get(0).checkWallet() + ", what is your bet?");
            while(true)
            {
                playerBet = userInput.nextDouble();
                //System.out.println("Okay wise guy, I don't know what casinos you've been to, but at Crispy Succotash, you have to bet NUMBERS!");
                
                if(playerBet > players.get(0).checkWallet())
                {
                    System.out.println("You can't bet more than what you have!");
                }
                else if (playerBet < 1)
                {
                    System.out.println("You have to bet more than $0!");
                }
                else
                {
                    players.get(0).setBet(playerBet);
                    break;
                }
            }           
        }
        else
        {
            System.out.println("You don't have any money. I'm sorry, you have to leave. >:(");
        }
    }
    
    public void playRound()
    {
        for(Player player : players)
        {
            player.draw(playingDeck);
            System.out.println(player.showHand());    
        }
        //dealer.draw(playingDeck);
        if(dealer.cardsValue() < 1)
        {
            dealer.draw(playingDeck);
            System.out.println(dealer.showHand());
        }
        else
        {
            dealer.draw(playingDeck);
            System.out.println(dealer.getName() + " drew a face down card of unknown value.");
        }
    }
    
    public void checkValues()
    {
        for(Player player : players)
        {
            if(dealer.cardsValue() == player.cardsValue())
            {
                System.out.println("Push, " + player.getName() + " has equal value to " + dealer.getName());
            }
            else if(player.cardsValue() == 21)
            {
                System.out.println(player.getName() + " hit blackjack ");
            }
        }
    }
    
    public void checkIfPlayerWantsToPlay()
    {
        System.out.println("Do you want to play again?");
        while(true)
        {
            String playerResponse = userInput.next();
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
    
    public Player exit()
    {
        return players.get(0);
    }
}