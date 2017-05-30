import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.event.*;
public class CardGraphic extends JFrame{
    BufferedImage img;
    private JPanel panel;
    private JButton bingoButton;
    private JButton num;
    public CardGraphic(BingoCard card, Bingo bingo){
        super("Bingo Card");
        panel = new JPanel();
        try {
            img = ImageIO.read(new File("bingoCard.jpg"));
        } catch (IOException e) {
            System.out.println("!canRead");
        }
        JLabel pic = new JLabel(new ImageIcon(img));
        panel.add(pic);
        bingoButton = new JButton("BINGO!!!!!!");
        bingoButton.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent event){
                    bingo.checkWinCondition(card);
                }
            }
        );
        BingoNum[][] numbers = card.showCard();
        for(int r = 0; r < 5; r++){
            for(int c = 0; c < 5; c++){
                num = new JButton(Integer.toString(numbers[r][c].getValue()));
                panel.add(num);
                num.setLocation(6 + 57*c,38 + 65*r);
                num.setPreferredSize(new Dimension(55,63));
            }
        }
        add(panel);
        add(bingoButton, BorderLayout.SOUTH);
        setSize(307, 437);
        this.setResizable(false);
        setVisible(true);
    }
}