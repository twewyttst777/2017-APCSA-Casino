import java.util.ArrayList;
import java.util.Collections;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Graphics;
public class BingoCard{
    BingoNum[][] nums;
    public BingoCard(){
        nums = new BingoNum[5][5];
        for(int j = 0; j < 5; j++){
            ArrayList<Integer> num = new ArrayList<Integer>();
            for(int k = 1; k <= 15; k++){
                num.add(k);
            }
            Collections.shuffle(num);
            for(int r = 0; r < 5; r++){
                if(j == 2 && r == 2){
                    BingoNum adder = new BingoNum(0);
                    nums[r][j] = adder;
                } else {
                    BingoNum removed = new BingoNum(num.remove(0));
                    nums[r][j] = removed;
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

    public BingoNum getNum(int col, int row){
        return nums[col][row];
    }

    public int getNumber(int col, int row){
        return nums[col][row].getValue();
    }
}