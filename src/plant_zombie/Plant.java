/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package plant_zombie;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;

/**
 *
 * @author dell
 */
public abstract class Plant {
    private int x;
    private int y;
    private int preX;
    private int preY;
    private int row;
    private int col;
    private Image img;
    public final int width =100;
    public final int heigth=100;
    private Timer timer;
    private int power;
    private double health=100;
    private boolean isSet=false;
    private Image imgSet;
    private ArrayList<ArrayList<Plant>> matPlant;
    private ArrayList<ArrayList<Zombie>> matZombie;
    
    public Plant(int row, int col, int power,ArrayList<ArrayList<Plant>> matPlant, ArrayList<ArrayList<Zombie>> matZombie) {
        timer=new Timer();
        this.power=power;
        this.row = row;
        this.col = col;
        this.x = col*Plant_Zombie.heightSquare+55;
        this.y = row*Plant_Zombie.heightSquare+115;
        this.imgSet=new ImageIcon("src\\plant_zombie\\Image\\GrowSoil.gif").getImage();
        this.imgSet.flush();
        this.matPlant=matPlant;
        this.matZombie=matZombie;
    }

    public ArrayList<ArrayList<Plant>> getMatPlant() {
        return matPlant;
    }

    public ArrayList<ArrayList<Zombie>> getMatZombie() {
        return matZombie;
    }

    public Image getImgSet() {
        return imgSet;
    }

    public void setImgSet(Image imgSet) {
        this.imgSet = imgSet;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }
    
    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
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

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }
    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public boolean isIsSet() {
        return isSet;
    }

    public void setIsSet(boolean isSet) {
        this.isSet = isSet;
    }
    
    public abstract void Draw(Graphics2D g2d, ImageObserver observer);
    public abstract  void Timer();
    public abstract  void Update( int row, int col);
    
}
