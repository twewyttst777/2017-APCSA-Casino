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
    
    public void drawCard(Deck deck) //draws a card from the top of another deck and adds to the current deck
    {
        addCard(deck.getCard(0));
        deck.removeCard(0);
    }
    
    public int deckValues() //The following values are according to blackjack game rules
    {
        int totalValue = 0;
        int aces = 0;
        for(Card card : this.cards)
        {
            switch(card.getValue())
            {
                case TWO: totalValue += 2; break;
                case THREE: totalValue += 3; break;
                case FOUR: totalValue += 4; break;
                case FIVE: totalValue += 5; break;
                case SIX: totalValue += 6; break;
                case SEVEN: totalValue += 7; break;
                case EIGHT: totalValue += 8; break;
                case NINE: totalValue += 9; break;
                case TEN: totalValue += 10; break;
                case ACE: aces += 1; break; //aces will be counted separately, as they change values
                case KING: totalValue += 10; break;
                case QUEEN: totalValue += 10; break;
                case JACK: totalValue += 10; break;
                default: break; //If a card somehow has another value it will be ignored
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
