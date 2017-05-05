public class Player
{
    private double money;
    
    public Player()
    {
        this.money = 50.0;
    }
    
    public Player(double money)
    {
        this.money = money;
    }
    
    public double getMoney()
    {
        return this.money;
    }
    
    public void setMoney(double money)
    {
        this.money = money;
    }
}
