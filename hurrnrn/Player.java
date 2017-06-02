public class Player
{
    private String name;
    private double money;
    private double bet;
    
    public Player()
    {
        this.name = "Dude Duderson";
        this.money = 50.0;
    }
    
    public Player(String name, double money)
    {
        this.name = name;
        this.money = money;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public double getMoney()
    {
        return this.money;
    }
    
    public void setMoney(double money)
    {
        this.money = money;
    }
    
    public void addMoney(double money)
    {
        this.money += money;
    }
    
    public double getBet()
    {
        return this.bet;
    }
    
    public void setBet(double bet)
    {
        this.bet = bet;
    }
}
