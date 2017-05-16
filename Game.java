import java.util.ArrayList;
public abstract class Game{
    ArrayList<Player> players = new ArrayList<Player>();
    public Game(){
        
    }
    
    public Game(Player... gamblers){
        for(Player toBeAdded : gamblers){
            players.add(toBeAdded);
        }
    }
    
    public ArrayList<Player> listPlayers(){
        return players;
    }
}