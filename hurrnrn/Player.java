import java.util.ArrayList;
public class Player{
    private String name;
    private double money;
    private ArrayList<Card> contents;
    public Player(String name, double cash){
        this.name = name;
        this.money = cash;
    }
    
    public Player(String name){
        this.name = name;
        money = 500;
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
    
    @Override
    public String toString(){
        return name;
    }
}