import java.util.ArrayList;
public class testTestington{
    public static void main(String args[]){
        PlayingCardDeck sampleDeck = new PlayingCardDeck();
        Hand sampleHand = new Hand(new Player("Timothy"), 5);
        System.out.println(sampleHand.drawCard(sampleDeck));
        System.out.println(sampleHand.drawCard(sampleDeck));
        System.out.println(sampleHand.drawCard(sampleDeck));
        System.out.println(sampleHand.drawCard(sampleDeck));
        System.out.println(sampleHand.drawCard(sampleDeck));
        System.out.print(sampleHand);
    }
}