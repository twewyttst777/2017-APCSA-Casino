import java.util.ArrayList;
public class Player
{
    private String name;
    private double money;
    private double bet;
    private ArrayList<Card> hand;
    
    public Player(String name, double money)
    {
        this.name = name;
        this.money = money;
        this.hand = new ArrayList();
    }
    
    public Player(String name)
    {
        this.name = name;
        this.money = 500;
        this.hand = new ArrayList();
    }
    
    public void setBet(double bet)
    {
        this.bet = bet;
    }
    
    public double getBet()
    {
        return this.bet;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public double checkWallet()
    {
        return this.money;
    }
    
    public void addMoney(double moneyAdded)
    {
        this.money += moneyAdded;
    }
    
    public void subtractMoney(double moneySubtracted)
    {
        this.money -= moneySubtracted;
    }
    
    public String draw(Deck otherDeck)
    {
        Card drawnCard = otherDeck.drawCard();
        if(drawnCard == null)
        {
            return "The deck is empty!";
        }
        
        this.hand.add(drawnCard);
        return "You drew a " + drawnCard;
    }
    
    public Card seeCard(int index)
    {
        if(index < this.hand.size())
        {
            return this.hand.get(index);
        }
        return null;
    }
    
    public Card playCard(int index)
    {
        if(index < this.hand.size())
        {
            return this.hand.remove(index);
        }
        return null;
    }
    
    public void clearHand()
    {
        this.hand.clear();
    }
    
    public String showHand()
    {
        String cards = "";
        for(Card card : this.hand)
        {
            cards += card.toString() + ", ";
        }
        return this.name + "'s hand contains: " + cards + "a hand value of " + this.cardsValue() + ".";
    }
    
    public boolean hasSoftHand()
    {
        for(Card c : this.hand)
        {
            PlayingCard card = null;
            if(c instanceof PlayingCard)
            {
                card = (PlayingCard) c;
            }
            if(card.getValue() == 1)
            {
                return true;
            }
        }
        return false;
    }
    
    public int cardsValue(int index)
    {
        if(index < this.hand.size())
        {
            Card c = this.hand.get(index);
            PlayingCard card = null;
            if(c instanceof PlayingCard)
            {
                card = (PlayingCard) c;
                if(card.getValue() >= 2 && card.getValue() <= 10)
                {
                    return card.getValue();
                }
                else
                {
                    switch(card.getValue())
                    {
                        case 1: return 11; //1 is referenced as an ace in the PlayingCard class
                        case 11: return 10; //11 is referenced as a Jack in the PlayingCard class
                        case 12: return 10; //12 is referenced as a Queen in the PlayingCard class
                        case 13: return 10; //13 is referenced as a King in the PlayingCard class
                        default: return 0; //The above could have been simplified here, but left to clarify set values
                    }
                }
            }
        }
        return 0;
    }
    
    public int cardsValue() //The following values are according to generic game rules
    {
        int totalValue = 0;
        int aces = 0;
        for(Card c : this.hand)
        {
            PlayingCard card = null;
            if(c instanceof PlayingCard)
            {
                card = (PlayingCard) c; 
            }
            if(card.getValue() >= 2 && card.getValue() <= 10)
            {
                totalValue += card.getValue();
            }
            else
            {
                switch(card.getValue())
                {
                    case 1: aces += 1; break; //1 is referenced as an ace in the PlayingCard class
                    case 11: totalValue += 10; break; //11 is referenced as a Jack in the PlayingCard class
                    case 12: totalValue += 10; break; //12 is referenced as a Queen in the PlayingCard class
                    case 13: totalValue += 10; break; //13 is referenced as a King in the PlayingCard class
                    default: break; //The above could have been simplified here, but left to clarify set values
                }
            }
        }
        for(int i = 0; i < aces; i++) //for every ace
        {
            if(totalValue > 10)
            {
                totalValue += 1;
            }
            else
            {
                totalValue += 11;
            }
        }
        return totalValue;
    }
}