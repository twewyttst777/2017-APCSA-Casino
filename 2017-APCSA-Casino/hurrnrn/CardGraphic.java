package hurrnrn;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.event.*;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.*;
public class CardGraphic extends JPanel{
    BufferedImage img;
    private JPanel panel;
    private JButton bingoButton;
    private int[][] nums;
    public JFrame x;
    public CardGraphic(BingoCard card, Bingo bingo){
        //super("Bingo Card");
        //panel = new JPanel();
        try {
            img = ImageIO.read(new File("bingoCard.jpg"));
        } catch (IOException e) {
            System.out.println("!canRead");
        }
        JLabel pic = new JLabel(new ImageIcon(img));
        Graphics2D g2 = img.createGraphics();
        g2.setColor(Color.RED);
        //panel.add(pic);
        bingoButton = new JButton("BINGO!!!!!!");
        bingoButton.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent event){
                    bingo.checkWinCondition(card);
                }
            }
        );
        nums = card.showCard();
        x = new JFrame();
        x.add(bingoButton, BorderLayout.SOUTH);
        x.add(this);
        x.setSize(307, 437);
        addMouseListener(new MouseAdapter() { 
          public void mousePressed(MouseEvent me) { 
            int x = me.getX();
            int y = me.getY();
            g2.fillRect(x - 10,y - 10,20,20);
            repaint();
          } 
        }); 
        x.setResizable(false);
        x.setVisible(true);
    }

    public void kill(){
        setVisible(false);
        x.dispose();
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(img, 0, 0, null);
        for(int r = 0; r < 5; r++){
            for(int c = 0; c < 5; c++){
                if(!(c==2 && r == 2)){
                
                g.drawString(Integer.toString(nums[r][c]), 31 + 57*c, 78 + 65*r);
            }
            }
        }
    }
}