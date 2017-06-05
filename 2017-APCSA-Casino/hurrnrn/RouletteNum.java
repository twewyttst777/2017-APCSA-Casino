package hurrnrn;
import java.awt.Color;
public class RouletteNum{
    private String color;
    private int number;
    
    public RouletteNum(String color, int number){
        this.color = color;
        this.number = number;
    }
    
    public String getColor(){
        return color;
    }
    
    public int getNumber(){
        return number;
    }
}