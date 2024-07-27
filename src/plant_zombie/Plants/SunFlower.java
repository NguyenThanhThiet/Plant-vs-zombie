/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package plant_zombie.Plants;

import com.sun.java.accessibility.util.AWTEventMonitor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.accessibility.AccessibleContext;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.event.EventListenerList;
import javax.swing.plaf.ComponentUI;
import plant_zombie.Plant;
import plant_zombie.Plant_Zombie;
import plant_zombie.Sound;
import plant_zombie.Sun;
import plant_zombie.Zombie;

/**
 *
 * @author dell
 */
public class SunFlower extends Plant implements MouseListener {

    private Sun sun;
    private Timer timer;
    private int TimerSun = 15000;

    public SunFlower(int row, int col, int power, ArrayList<ArrayList<Plant>> matPlant, ArrayList<ArrayList<Zombie>> matZombie) {
        super(row, col, power, matPlant, matZombie);
         setImg(new ImageIcon("src\\plant_zombie\\Image\\sunflower.gif").getImage());
        sun = new Sun(getX() + 80, getY());
    }


    public void Timer() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Timer Sun");
                if (!sun.isState()) {
                    sun.setState(true);
                }
            }
        }, TimerSun);
    }

    public Sun getSun() {
        return sun;
    }

    public void setSun(Sun sun) {
        this.sun = sun;
    }

    @Override
    public void Draw(Graphics2D g2d, ImageObserver observer) {
        if (getHealth() > 0) {
            if(isIsSet()){
              g2d.drawImage(getImgSet(), getX(), getY()+50, 100, 100,  observer);
            }
            g2d.drawImage(getImg(), getX(), getY(), width, heigth, observer);
            if (sun.isState()) {
                sun.Draw(g2d, observer);
            }
        } else {
            getMatPlant().get(getRow()).remove(this);
            timer.cancel();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getX() >= getX() + 80 && e.getX() <= getX() + 150 && e.getY() >= getY() && e.getY() <= getY() + 70) {

            if (sun.isState()) {
                Plant_Zombie.powerSun += 25;
                new Sound().sound(new File("src\\plant_zombie\\Sound\\points.wav"));
                diChuyen(e);
            }
        }
    }

    public void diChuyen(MouseEvent e) {
        Timer time = new Timer();
        time.schedule(new TimerTask() {
            @Override
            public void run() {
                sun.setY(sun.getY() - 1);
                sun.setX(((-(65 - e.getX()) * (sun.getY() - e.getY())) / (e.getY() - 25)) + e.getX());
                if (sun.getY() <= 25) {
                    sun.setState(false);
                    sun.setX(getX() + 80);
                    sun.setY(getY());
                    time.cancel();
                    Timer();
                }
            }
        }, 0, 1);
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void Update(int row, int col) {
        setRow(row);
        setCol(col);
        setX(col * Plant_Zombie.widthSquare + 55);
        setY(row * Plant_Zombie.heightSquare + 115);
        sun.setX(getX() + 80);
        sun.setY(getY());
        Timer();
        setIsSet(true);
        Timer setTime = new Timer();
        setTime.schedule(new TimerTask() {
            @Override
            public void run() {
                setIsSet(false);
            }
        }, 500);
    }

}
