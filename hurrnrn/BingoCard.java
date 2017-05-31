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
    int[][] nums;
    public BingoCard(){
        nums = new int[5][5];
        for(int c = 0; c < 5; c++){
            ArrayList<Integer> num = new ArrayList<Integer>();
            for(int k = 1; k <= 15; k++){
                num.add(k + (15 * c));
            }
            Collections.shuffle(num);
            for(int r = 0; r < 5; r++){
                if(c == 2 && r == 2){
                    nums[r][c] = 0;
                } else {
                    nums[r][c] = num.remove(0);
                }
            }
            num.clear();
        }
    }

    public int[][] showCard(){
        return nums;
    }

    public int getNumber(int col, int row){
        return nums[col][row];
    }
}