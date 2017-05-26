import java.awt.image.*;
import java.awt.Graphics;
import java.io.*;
import javax.imageio.*;
public class Picture{
    public BufferedImage img;
    public Picture(String name){
        try {
            img = ImageIO.read(new File(name));
        } catch (IOException e) {
        
        }
    }
    
    public void paint(int x, int y){
         if(img != null){
            Graphics.drawImage(img, x, y, null);
        }
    }
}