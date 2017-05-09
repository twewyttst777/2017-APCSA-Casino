import java.util.ArrayList;
import java.util.Collections;
public abstract class Deck{
    ArrayList<PlayingCard> cards;
    
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
    
    public int cardsValue() //The following values are according to generic game rules
    {
        int totalValue = 0;
        int aces = 0;
        for(PlayingCard card : this.cards)
        {
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