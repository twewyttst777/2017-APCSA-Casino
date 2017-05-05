public class Player{
    private String name;
    private int cash;
    public Player(String name, int cash){
        this.name = name;
        this.cash = cash;
    }
    
    public Player(String name){
        this.name = name;
        cash = 0;
    }
    
    public String getName(){
        return name;
    }
    
    public int countMoney(){
        return cash;
    }
    
    public void addMoney(int moneyAdded){
        cash += moneyAdded;
    }
    
    @Override
    public String toString(){
        return name;
    }
}