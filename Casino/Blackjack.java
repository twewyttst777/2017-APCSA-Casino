public class Blackjack
{
    public Blackjack()
    {
        System.out.println("Welcome to Skyler's Cool Blackjack thingy");
        
        Deck playingDeck = new Deck();
        playingDeck.makeFullDeck();
        playingDeck.shuffle();
        
        Deck playerDeck = new Deck();
        Deck dealerDeck = new Deck();
        
        System.out.println(playingDeck);
    }
}
