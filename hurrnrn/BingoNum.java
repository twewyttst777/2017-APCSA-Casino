public class BingoNum{
    private int value;
    private boolean marked; 
    public BingoNum(int value){
        this.value = value;
        marked = false;
    }
    
    public int getNumber(){
        return value;
    }
    
    public void mark(){
        marked = true;
    }
}