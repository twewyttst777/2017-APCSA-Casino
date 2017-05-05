import java.util.ArrayList;
public class PlayingCardDeck extends Deck{
    public PlayingCardDeck(){
        Card n = new PlayingCard();
        for(int suit = 0; suit < 4; suit ++){
            for(int value = 1; value <= 13; value++){
                addCard(new PlayingCard(suit, value));
            }
        }
        shuffle();
    }
}