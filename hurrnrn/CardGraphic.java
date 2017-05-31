import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.event.*;
public class CardGraphic extends JPanel{
    BufferedImage img;
    private JPanel panel;
    private JButton bingoButton;
    private int[][] nums;
    public CardGraphic(BingoCard card, Bingo bingo){
        //super("Bingo Card");
        //panel = new JPanel();
        try {
            img = ImageIO.read(new File("bingoCard.jpg"));
        } catch (IOException e) {
            System.out.println("!canRead");
        }
        JLabel pic = new JLabel(new ImageIcon(img));
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
        JFrame x = new JFrame();
        x.add(bingoButton, BorderLayout.SOUTH);
        x.add(this);
        x.setSize(307, 437);
        x.setResizable(false);
        x.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(img, 0, 0, null);
        for(int r = 0; r < 5; r++){
            for(int c = 0; c < 5; c++){
                g.drawString(Integer.toString(nums[r][c]), 30 + 57*c, 72 + 65*r);
            }
        }
    }
}