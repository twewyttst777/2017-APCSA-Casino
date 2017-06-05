package hurrnrn;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.*;
public class RouletteWheel extends JPanel implements ActionListener{
    Timer tm = new Timer(5, this);
    BufferedImage img;
    int x;
    int y;
    int t = 0;
    int rollTime;
    int adjustedt = 0;
    JFrame f;
    private static final int WHEEL_CENTER_X = 185;
    private static final int WHEEL_CENTER_Y = 185;
    private static final int RADIUS = 175;
    private static final double TIME_TO_RADIANS = 2 * Math.PI / 1000;
    public RouletteWheel(){
        tm.start();
        try {
            img = ImageIO.read(new File("americanroulette.png"));
        } catch (IOException e) {
            System.out.println("!canRead");
        }
        f = new JFrame();
        f.add(this);
        f.setSize(416,439);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(img, 0, 0, null);
        g.setColor(Color.BLUE);
        g.fillOval(x,y,25,25);
    }

    public void actionPerformed(ActionEvent e){
        t++;
        double randomizationFactor = (Math.random() * 10) + 55;
        adjustedt += (-Math.pow(t,2)/100000) + t / randomizationFactor;
        double angle = adjustedt * TIME_TO_RADIANS;

        if(((-Math.pow(t,2)/100000) + t / randomizationFactor) >= 0){
            x = (int) (Math.cos(angle) * RADIUS + WHEEL_CENTER_X);
            y = (int) (Math.sin(angle) * RADIUS + WHEEL_CENTER_Y);
            repaint();
        }
    }

    public double returnAngle(){
        int xadjust = x - WHEEL_CENTER_X;
        int yadjust = (y - WHEEL_CENTER_Y);
        double potentialAngle = Math.asin((double) (yadjust)/RADIUS);
        //if(xadjust >= 0 && yadjust >= 0){
        if(potentialAngle < 0){
            return potentialAngle += 2 * Math.PI;
        }
        return potentialAngle;
        /*}
        else if (xadjust >= 0 && yadjust <=0){
            return Math.PI - potentialAngle;
        } else if (xadjust <= 0 && yadjust >= 0){
            return 2 * Math.PI + potentialAngle;
        } else {
            return Math.PI - potentialAngle;
        }*/
    }

    public void rollAgain(){
        t = 0;
        f.setVisible(true);
    }
}