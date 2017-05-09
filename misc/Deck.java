import java.util.ArrayList;
import java.util.Collections;
public abstract class Deck{
    ArrayList<Card> cards = new ArrayList<Card>();
    public void reset(Game toBeReset){
        
    }
    
    public void addCard(PlayingCard card)
    {
        this.cards.add(card);
    }
    
    public Card getCard(int cardIndex)
    {
        return this.cards.get(cardIndex);
    }
    
    public void removeCard(int cardIndex)
    {
        this.cards.remove(cardIndex);
    }
    
    public Card drawCard() //draws a card from your own deck; this is pretty much useless now isn't it?
    {
        return this.cards.remove(0);
    }
    
    public void shuffle(){
        Collections.shuffle(cards);
    }
    
    public String toString() //prints out all of the cards within the deck
    {
        String output = "";
        for(Card card : this.cards)
        {
            output += "\n" + card.toString();
        }
        return output;
    }
}