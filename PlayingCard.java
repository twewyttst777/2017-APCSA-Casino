public class PlayingCard extends Card{
    private int suit;
    private int value;
    public PlayingCard(){
        suit = (int) (Math.random() * 4);
        value = (int) (Math.random() * 13) + 1;
    }
    
    public PlayingCard(int suit, int value){
        this.suit = suit;
        this.value = value;
    }
    
    public int getValue(){
        return value;
    }
    
    public int getsuit(){
        return suit;
    }
    
    @Override
    public String toString(){
        String suitName = "";
        String valueName = "";
        if(suit == 0){
            suitName = "Clubs";
        } else if (suit == 1){
            suitName = "Diamonds";
        } else if (suit == 2){
            suitName = "Hearts";
        } else {
            suitName = "Spades";
        }
        
        if(value == 1){
            valueName = "Ace";
        } else if (value == 11){
            valueName = "Jack";
        } else if (value == 12){
            valueName = "Queen";
        } else if (value == 13){
            valueName = "King";
        } else {
            valueName += value;
        }
        return valueName + " of " + suitName;
    }
}