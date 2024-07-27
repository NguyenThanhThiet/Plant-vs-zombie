/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package plant_zombie;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author dell
 */
public class BackGround{
    private Image background;
    private int width;
    private int height;
    public BackGround(int width, int height) {
        this.width=width;
        this.height=height;
        this.background = new ImageIcon("src\\plant_zombie\\Image\\mainBG.png").getImage();
    }

   
    protected void Draw(Graphics2D g2d,ImageObserver observer) {     
        g2d.drawImage(background, 0, 0, width, height, observer);
    }
 
}
