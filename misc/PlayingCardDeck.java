import java.util.ArrayList;
public class PlayingCardDeck extends Deck{
    /*private String alreadyDrawn;
    public PlayingCardDeck(){
        alreadyDrawn = "";
    }
    
    public PlayingCard draw(){
        if(alreadyDrawn.length() >= 4){
                return null;
        }
        while(true){
            PlayingCard newCard = new PlayingCard();
            if(alreadyDrawn.indexOf(newCard.hiddenValue()) != -1){
                alreadyDrawn = alreadyDrawn + newCard.hiddenValue() + " ";
                return newCard;
            }
        }
    }
    
    public void clearGame(Hand... players){
        for(Hand toBeCleared : players){
            toBeCleared.clearHand();
        }
    }
    //I still want to make this part work lol
    */
    public PlayingCardDeck(){
        deck = new ArrayList<Card>();
        Card n = new PlayingCard();
        for(int suit = 0; suit < 4; suit ++){
            for(int value = 1; value <= 13; value++){
                PlayingCard addedCard = new PlayingCard(suit, value);
                deck.add(addedCard);
            }
        }
    }
    
    public PlayingCard draw(){
        if(deck.size() == 0){
            return null;
        }
        
        while(true){
            PlayingCard drawnCard = new PlayingCard();
            for(int i = 0; i < deck.size(); i++){
                if(deck.get(i).hiddenValue() == drawnCard.hiddenValue()){
                    deck.remove(i);
                    return drawnCard;
                }
            }
        }
    }
}