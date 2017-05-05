import java.util.ArrayList;
public class Hand{
    private Player owner;
    private ArrayList<Card> contents;
    private int maxSize;
    public Hand(){
        owner = new Player("The House", 100000000);
        contents = new ArrayList<Card>();
        maxSize = 1000;
    }
    
    public Hand(Player owner, int maxSize){
        this.owner = owner;
        this.maxSize = maxSize;
        contents = new ArrayList<Card>();
    }
    
    public String drawCard(Deck currentDeck){
        Card drawnCard = currentDeck.draw();
        if(drawnCard == null){
            return "The deck is empty!";
        }
        
        if(contents.size() >= maxSize){
            return "My hand is too full!";
        }
        contents.add(drawnCard);
        return "You drew a " + drawnCard;
    }
    
    public void clearHand(){
        contents.clear();
    }
    
    @Override
    public String toString(){
        String contains = "";
        for(Card card : contents){
            contains = contains + card.toString() + ", ";
        }
        return owner + "'s hand contains:\n" + contains;
    }
}