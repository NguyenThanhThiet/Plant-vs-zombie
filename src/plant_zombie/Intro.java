/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package plant_zombie;

import java.awt.AlphaComposite;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author dell
 */
public class Intro extends JPanel {

    private Image background;
    private boolean isClicked=false;
    public Intro() {
        background = new ImageIcon("src\\plant_zombie\\Image\\background.png").getImage();
        setSize(1400, 800);
        setBackground(Color.red);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();

                if (x >= 240 && x <= 1120 && y >= 670 && y <= 745) {
                    new Sound().sound(new File("src\\plant_zombie\\Sound\\clickButton.wav"));
                    isClicked=true;
                    repaint();
                    Plant_Zombie plant_Zombie=new Plant_Zombie();
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            getParent().add(plant_Zombie,"plantvszombie");
                            CardLayout cardLayout = (CardLayout) getParent().getLayout();
                            cardLayout.show(getParent(), "plantvszombie");
                        }
                    }, 1000);

                }
            }

        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(background, 0, 0, 1400, 765, this);
        if(isClicked)
        {
            g2d.setColor(Color.BLACK);
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.3f));
            g2d.fillRect(240, 670, 880, 75);
        }
    }

}
