import java.util.ArrayList;
public class Player{
    private String name;
    private double money;
    private double bet;
    private ArrayList<Card> contents;
    public Player(String name, double cash){
        this.name = name;
        this.money = cash;
        contents = new ArrayList();
    }
    
    public Player(String name){
        this.name = name;
        money = 500;
        contents = new ArrayList();
    }
    
    public void setBet(double bet)
    {
        this.bet = bet;
    }
    
    public double getBet()
    {
        return this.bet;
    }
    
    public String getName(){
        return name;
    }
    
    public double checkWallet(){
        return money;
    }
    
    public void addMoney(double moneyAdded){
        money += moneyAdded;
    }
    
    public String draw(Deck currentDeck){
        Card drawnCard = currentDeck.drawCard();
        if(drawnCard == null){
            return "The deck is empty!";
        }
        
        contents.add(drawnCard);
        return "You drew a " + drawnCard;
    }
    
    public Card seeCard(int index){
        return contents.get(index);
    }
    
    public Card playCard(int index){
        return contents.remove(index);
    }
    
    public void clearHand(){
        contents.clear();
    }
    
    public String showHand(){
        String contains = "";
        for(Card card : contents){
            contains = contains + card.toString() + ", ";
        }
        return name + "'s hand contains:\n" + contains;
    }
    
    public int cardsValue() //The following values are according to generic game rules
    {
        int totalValue = 0;
        int aces = 0;
        for(Card c : this.contents)
        {
            PlayingCard card = null;
            if(c instanceof PlayingCard){
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
    
    @Override
    public String toString(){
        return name;
    }
}