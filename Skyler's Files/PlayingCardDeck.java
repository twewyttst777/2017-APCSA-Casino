import java.util.ArrayList;
public class PlayingCardDeck extends Deck{
    public PlayingCardDeck()
    {
        for(int suit = 0; suit < 4; suit ++)
        {
            for(int value = 1; value <= 13; value++)
            {
                PlayingCard n = new PlayingCard(suit, value);
                cards.add(n);
            }
        }
        shuffle();
    }
    
    public PlayingCardDeck(int numberOfDecks)
    {
        for(int i = 0; i < numberOfDecks; i++)
        {
            for(int suit = 0; suit < 4; suit ++)
            {
                for(int value = 1; value <= 13; value++)
                {
                    PlayingCard n = new PlayingCard(suit, value);
                    cards.add(n);
                }
            }
        }
        shuffle();
    }
    
    public int cardsValue() //The following values are according to generic game rules
    {
        int totalValue = 0;
        int aces = 0;
        for(Card c : this.cards)
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