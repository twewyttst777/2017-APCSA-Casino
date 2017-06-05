import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Collections;

public class Blackjack
{   
    //names
    private ArrayList<String> names = new ArrayList();
    private String[] stringArrayNames = { "Billy", "Joe", "Suzy", "Kyle", "Guy", "Teddy" };
    //input
    private Scanner userInput = new Scanner(System.in);
    //players
    private ArrayList<Player> players = new ArrayList();
    private Player mainPlayer;
    private int mainPlayerIndex;
    
    private Player dealer = new Player("Bob The Dealer", 9001);
    //game
    private PlayingCardDeck playingDeck;
    private boolean playerPlaying;
    //other
    
    
    public Blackjack(Player player)
    {
        this.mainPlayer = player;
        
        System.out.println("Welcome to Blackjack!");
        
        for(String string : stringArrayNames)
        {
            names.add(string);
        }
        
        setPlayers();
        playGame();
    }
    
    private void playGame()
    {
        playerPlaying = true;
        while(playerPlaying)
        {
            if(players.get(this.mainPlayerIndex).checkWallet() <= 0)
            {
                System.out.println("You don't have any money. I'm sorry, you have to leave. >:(");
                this.playerPlaying = false;
                return;
            }
            setBets();
            
            System.out.println("Shuffling...\n");
            playingDeck = new PlayingCardDeck(6); //shuffles six decks
            
            deal(); //deals two cards to each player then the dealer
            deal();
            
            if(dealer.cardsValue(0) == 11 || dealer.cardsValue(0) == 10) //if the dealer's upcard is an ace or a 10, they'll peek for blackjack
            {
                dealerPeek();
            }
            else
            {
                playersTurn();
                dealersTurn();
            }   
            checkWinnings();
            checkIfPlayerWantsToPlay();
        }
    }
    
    private void setPlayers()
    {
        this.players.add(mainPlayer);
        int numberOfComputerPlayers = ThreadLocalRandom.current().nextInt(0, 5 + 1); //possibly 5 other random computer players
        for(int i = 0; i < numberOfComputerPlayers; i++)
        {
            this.players.add(new Player(getRandomName(), 50));
        }
        Collections.shuffle(this.players); //put the players in a random order
        for(int i = 0; i < this.players.size(); i++) //find the main players index
        {
            if(this.players.get(i).getName().equals(this.mainPlayer.getName()) && this.players.get(i).checkWallet() == this.mainPlayer.checkWallet())
            {
                this.mainPlayerIndex = i;
            }
        }
    }
    
    private void setBets()
    {
        int computerBet = 0;
        for(Player player : this.players)
        {
            if(player == this.players.get(this.mainPlayerIndex)) //if its the human player
            {
                setPlayerBet(player);
            }
            else //it's a computer
            {
                setComputerBet(player);
            }
        }
    }
    
    private void setPlayerBet(Player player)
    {
        double playerBet = 0;
        while(true)
        {
            System.out.println("You have $" + player.checkWallet() + ", what is your bet?");
            playerBet = userInput.nextDouble();
            //System.out.println("Okay wise guy, I don't know what casinos you've been to, but at Crispy Succotash, you have to bet NUMBERS!");
            if(playerBet > player.checkWallet())
            {
                System.out.println("You can't bet more than what you have!");
            }
            else if(playerBet < 1)
            {
                System.out.println("You have to bet more than $0!");
            }
            else
            {
                player.setBet(playerBet);
                return;
            }
        }           
    }
    
    private void setComputerBet(Player computer)
    {
        computer.setBet(ThreadLocalRandom.current().nextInt(2, 500 + 1)); //computer bets between 2 - 500
    }
    
    private void deal()
    {
        for(Player player : this.players)
        {
            hit(player);
        }
        if(this.dealer.cardsValue() < 1)
        {
            hit(this.dealer);
        }
        else //dealer draws a face down card after the first one
        {
            this.dealer.draw(this.playingDeck);
            System.out.println(this.dealer.getName() + " drew a face down card of unknown value.");
        }
    }
    
    private void dealerPeek()
    {
        if(dealer.cardsValue(0) == 11) //if the dealer's upcard is an ace then ask players for insurance
        {
            for(Player player : this.players) //ask every player if they'll take insurance
            {
                if(player == this.players.get(this.mainPlayerIndex)) //if its the human player
                {
                    
                }
                else //it's a computer
                {
                    
                }
            }
        }
        else if(dealer.cardsValue(0) == 10) //if the dealer's upcard is a 10
        {
            
        }
        System.out.println(this.dealer.getName() + " flipped over the face down card, revealing a(n) " + this.dealer.seeCard(1) + "."); //reveals second card
        System.out.println(this.dealer.showHand());
        if(dealer.cardsValue() == 21) //the dealer definitely got a blackjack, we'll skip the players turn and collect bets
        {
            return;
        }
        else //we'll collect the insurance bets and continue the game here
        {
            
            playersTurn();
            dealersTurn();
        }
    }
    
    private void hit(Player player)
    {
        player.draw(this.playingDeck);
        System.out.println(player.showHand());  
    }
    
    private void doublingDown(Player player) //hits and doubles players bet
    {
        hit(player);
        player.setBet(player.getBet() * 2);
    }
    
    private void surrender(Player player)
    {
        player.subtractMoney(player.getBet() / 2);
        System.out.println(player.getName() + " has surrendered. Losing $" + player.getBet() / 2 + " of their original bet of $" + player.getBet() + ".");
        player.setBet(0);
        player.clearHand();
    }
    
    private void split(Player player)
    {
        
    }
    
    private void playersTurn()
    {
        for(Player player : this.players)
        {
            if(player == this.players.get(this.mainPlayerIndex)) //if its the human player
            {
                playerDecision(player);
            }
            else //it's a computer
            {
                computerDecision(player);
            }
        }
    }
    
    private void dealersTurn() //dealer reveals his down card and hits soft 17
    {
        System.out.println(this.dealer.getName() + " flipped over the face down card, revealing a(n) " + this.dealer.seeCard(1) + "."); //reveals second card
        System.out.println(this.dealer.showHand());
        while(this.dealer.cardsValue() < 17) //until the dealer hits 17 or higher
        {
            hit(this.dealer);
            if(this.dealer.cardsValue() == 17 && this.dealer.hasSoftHand()) //if the dealer has a soft 17 then they must hit
            {
                hit(this.dealer);
            }
        }
    }
    
    private void playerDecision(Player player)
    {
        String playerInput;
        while(true)
        {
            if(player.cardsValue() == 21 || player.cardsValue() > 21) //the player is automatically skipped if they have a 21 or busts
            {
                return;
            }
            else if(player.seeCard(2) == null) //if the player has only two cards
            {
                if(player.cardsValue(0) == player.cardsValue(1)) //if the players two cards are of the same value they also have a split option
                {
                    while(true)
                    {
                        System.out.println("Do you want to hit, stand, double, split, or surrender?");
                        playerInput = userInput.next();
                        if(playerInput.equals("hit"))
                        {
                            hit(player);
                            break;
                        }
                        else if(playerInput.equals("stand"))
                        {
                            return;
                        }
                        else if(playerInput.equals("double"))
                        {
                            doublingDown(player);
                            return;
                        }
                        else if(playerInput.equals("split"))
                        {
                            split(player);
                            break;
                        }
                        else if(playerInput.equals("surrender"))
                        {
                            surrender(player);
                            return;
                        }   
                        else
                        {
                            System.out.println("HAHA, oh man are you funny, answer for real this time...");
                        }
                    }
                }
                else
                {
                    while(true)
                    {
                        System.out.println("Do you want to hit, stand, double, or surrender?");
                        playerInput = userInput.next();
                        if(playerInput.equals("hit"))
                        {
                            hit(player);
                            break;
                        }
                        else if(playerInput.equals("stand"))
                        {
                            return;
                        }
                        else if(playerInput.equals("double"))
                        {
                            doublingDown(player);
                            return;
                        }
                        else if(playerInput.equals("surrender"))
                        {
                            surrender(player);
                            return;
                        }
                        else
                        {
                            System.out.println("HAHA, oh man are you funny, answer for real this time...");
                        }
                    }
                }
            }
            else //the player has more than two cards
            {
                System.out.println("Do you want to hit or stand?");
                playerInput = userInput.next();
                switch(playerInput)
                {
                    case "hit": hit(player); break;
                    case "stand": return;
                    default: break;
                }
            }
        }
    }
    
    private void computerDecision(Player computer)
    {
        if(computer.cardsValue() == 21 || computer.cardsValue() > 21) //the computer is automatically skipped if they have a 21 or busts
        {   
            return;
        }
    }
    
    private void checkWinnings() //check winnings at the end of the round
    {
        String status = "";
        for(Player player : this.players)
        {
            if(player.getBet() == 0)
            {
                //player had surrendered
                status = player.getName() + " had surrendered earlier."; 
            }
            else if(player.cardsValue() > 21)
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
            else if(player.cardsValue() == 21 && player.seeCard(2) == null) //if there is no third card then the player definitely got a blackjack
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
            else if(dealer.cardsValue() == 21 && player.seeCard(2) == null) //if there is no third card then the dealer definitely got a blackjack
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
        this.dealer.clearHand();
    }
    
    private void checkIfPlayerWantsToPlay()
    {
        String playerInput;
        while(true)
        {
            System.out.println("You now have $" + players.get(mainPlayerIndex).checkWallet() + ", do you want to play again?");
            playerInput = userInput.next();
            switch(playerInput)
            {
                case "yes":
                    return;
                case "no":
                    this.playerPlaying = false;
                    return;
                default:
                    System.out.println("Please give a valid response...");
                    break;
            }
        }
    }
    
    private String getRandomName()
    {
        return this.names.remove((int) (Math.random() * this.names.size())); //gets a random name for the computer and removes it to make sure it is not used again
    }
    
    public Player exit()
    {
        return this.players.get(this.mainPlayerIndex); //we return the human player in the array back to the casino
    }
}