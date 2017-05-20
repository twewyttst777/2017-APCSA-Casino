import java.util.ArrayList;
import java.util.Collections;
public class BingoCard{
    BingoNum[][] nums;
    public BingoCard(){
        for(int j = 0; j < 5; j++){
                ArrayList<Integer> num = new ArrayList<Integer>();
                for(int k = 1; k <= 15; k++){
                    num.add(k);
                }
                Collections.shuffle(num);
                for(int r = 0; r < 5; r++){
                    if(j == 2 && r == 2){
                        nums[r][j] = new BingoNum(0);
                    } else {
                        nums[r][j] = new BingoNum(num.remove(0));
                    }
                }
            }
    }
    
    public void mark(int col, int row){
        nums[col][row].mark();
    }
    
    public BingoNum[][] showCard(){
        return nums;
    }
    
    public BingoNum seeNumber(int col, int row){
        return nums[col][row];
    }
}