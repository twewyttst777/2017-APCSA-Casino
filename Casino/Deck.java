import java.util.ArrayList;
import java.util.Collections;

public class Deck
{
    private ArrayList<Card> cards = new ArrayList();
    
    public Deck()
    {
        //empty deck; starting point for the dealer and players
    }
    
    public void makeFullDeck() //full deck; for the dealer and players to draw from
    {
        for(Suit suit : Suit.values())
        {
            for(Value value : Value.values())
            {
                this.cards.add(new Card(suit, value));
            }
        }
    }
    
    public void shuffle()
    {
        Collections.shuffle(cards);
    }
    
    public void addCard(Card card)
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
    
    public void drawCard() //draws a card from your own deck; this is pretty much useless now isn't it?
    {
        addCard(getCard(0));
        removeCard(0);
    }
    
    public void drawCard(Deck deck) //draws a card from the top of another deck and adds to the current deck
    {
        addCard(deck.getCard(0));
        deck.removeCard(0);
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
