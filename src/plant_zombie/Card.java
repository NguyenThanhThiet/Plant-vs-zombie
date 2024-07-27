/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package plant_zombie;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;

/**
 *
 * @author dell
 */
public class Card {
    public static final int width=80;
    public static final int height=93;
    private int x;
    private int y;
    private Image img;
    private int preX;
    private int preY;
    private int col;
    private int power;
    private String name;
    public int getPreX() {
        return preX;
    }

    public void setPreX(int preX) {
        this.preX = preX;
    }

    public int getPreY() {
        return preY;
    }

    public void setPreY(int preY) {
        this.preY = preY;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Card(int col,int power,Image img,String name) {
        this.power=power;
        this.col=col;
        this.x = col*width+155;
        this.y = 10;
        this.img=img;
        this.preX=x;
        this.preY=y;
        this.name=name;
    }
    public Card(int power,Image img,String name) {
        this.power=power;
        this.x = col*width+155;
        this.y = 10;
        this.img=img;
        this.preX=x;
        this.preY=y;
        this.name=name;
    }
    public void Draw(Graphics2D g2d, ImageObserver observer) {
        g2d.drawImage(img, x, y, width, height,  observer);
        if(Plant_Zombie.powerSun<power)
        {
            g2d.setColor(Color.BLACK);
            AlphaComposite alcom = AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, 0.7f);
            g2d.setComposite(alcom);
            g2d.fillRect(x, y, width, height);
            g2d.setComposite(AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, 1f));
        }
        
    }
    public void update(int col)
    {
        this.col=col;
        this.x=col*width+155;
    }
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }
    
}
