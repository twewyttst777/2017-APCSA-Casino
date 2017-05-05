import java.util.ArrayList;
public abstract class Deck{
    ArrayList<Card> deck;
    public abstract Card draw();
    public void reset(Game toBeReset){
        
    }
}