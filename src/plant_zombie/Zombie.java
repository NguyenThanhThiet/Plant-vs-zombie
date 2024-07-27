/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package plant_zombie;

import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author dell
 */
public class Zombie {

    private int x;
    private int y;
    private int width = 220;
    private int height = 220;
    private Image img;
    private int row;
    private int col;
    private Timer timer;
    private int health = 100;
    private Plant plant;
    private int speed = 100;
    private Effect effectBullet;
    private double step = 1;
    private Action action;
    private Image headZombie = new ImageIcon("src\\plant_zombie\\Image\\zombiehead.gif").getImage();
    private Image bodyZombie = new ImageIcon("src\\plant_zombie\\Image\\zombiedie.gif").getImage();
    private Timer timeAudio;
    private boolean isEat = false;
    private String cause = "normal";
    private ArrayList<ArrayList<Plant>> matPlants;
    private ArrayList<ArrayList<Zombie>> matZombies;
    public Zombie(int row, Action action,ArrayList<ArrayList<Plant>> matPlants, ArrayList<ArrayList<Zombie>> matZombies) {
        this.matPlants=matPlants;
        this.matZombies=matZombies;
        this.row = row;
        this.col = 9;
        this.x = parseX(col);
        this.y = parseY(row);
        img = new ImageIcon("src\\plant_zombie\\Image\\zombie.gif").getImage();
        timer = new Timer();
        timer.schedule(walkingZombie(), 0, speed);
        this.action = action;
        headZombie = new ImageIcon("src\\plant_zombie\\Image\\zombiehead.gif").getImage();
        bodyZombie = new ImageIcon("src\\plant_zombie\\Image\\zombiedie.gif").getImage();
        timeAudio = new Timer();
        timeAudio.schedule(new TimerTask() {
            @Override
            public void run() {
                audioZombie();
                if(Plant_Zombie.beEaten)
                    timeAudio.cancel();
            }
        }, 0, 10000);
        audioEating();
    }

    public TimerTask walkingZombie() {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if (getX() > parseX(lastestCol())) {
                    setX((int) (getX() - step));
                    if (isEat) {
                        setImg(new ImageIcon("src\\plant_zombie\\Image\\zombie.gif").getImage());
                        isEat = false;
                    }

                    if(getX() <= -50) {
                        Plant_Zombie.beEaten = true;
                    }
                } else {
                    if (plant != null) {
                        setImg(new ImageIcon("src\\plant_zombie\\Image\\zombieattack.gif").getImage());
                        System.out.println(plant.getHealth());
                        plant.setHealth((int) (plant.getHealth() - (0.000000001)));
                        isEat = true;
                        if (plant.getHealth() <= 0) {
                            setImg(new ImageIcon("src\\plant_zombie\\Image\\zombie.gif").getImage());
                            plant = null;
                            isEat = false;
                        }
                    }
                }

            }
        };
        return timerTask;
    }

    public void audioEating() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (isEat) {
                    new Sound().sound(new File("src\\plant_zombie\\Sound\\zombieeat.wav"));
                }
                if(Plant_Zombie.beEaten)
                    timer.cancel();
            }
        }, 0, 1000);
    }

    public void recieveEffect(Pea pea) {
        effectBullet = new Effect(getX() + 125, getY() + 100);
        if (pea instanceof FreezePea) {
            effectBullet.setImg(new ImageIcon("src\\plant_zombie\\PeaBulletHitFreeze.gif").getImage());
//            timer.cancel();
//            Timer time=new Timer();
//            time.schedule(new TimerTask() {
//                @Override
//                public void run() {
//                    timer.schedule(walkingZombie(),0,speed);
//                }
//            }, 1000);
        }
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                effectBullet = null;
            }
        }, 1000);
    }

    public int lastestCol() {
        int max = -1;
        ArrayList<Plant> array = matPlants.get(row);
        for (Plant p : array) {
            if (p.getCol() > max && parseX(p.getCol())<=getX()+100) {
                max = p.getCol();
                plant = p;
            }
             
        }
        return max;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public void Draw(Graphics2D g2d, ImageObserver observer) {
        if (health > 0) {
            g2d.drawImage(img, x, y, width, height, observer);
            if (effectBullet != null) {
                effectBullet.setX(getX() + 125);
                effectBullet.Draw(g2d, observer);
            }
        } else {
            if (cause.equals("normal")) {
                matZombies.get(row).remove(this);
                timer.cancel();
                timeAudio.cancel();
                action.ZombieDie(new ZombieDie(x, y, headZombie, bodyZombie));
                isEat = false;
            } else if (cause.equals("Chomper")) {
                matZombies.get(row).remove(this);
                timer.cancel();
                timeAudio.cancel();
                isEat = false;
            }
        }
    }

    public void audioZombie() {

        try {
            File file = new File("src\\plant_zombie\\Sound\\Zombie.wav");
            AudioInputStream audio = AudioSystem.getAudioInputStream(file.getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int parseY(int row) {
        return row * Plant_Zombie.heightSquare + 0;
    }

    public int parseX(int col) {
        return col * Plant_Zombie.widthSquare + 55;
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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
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

}
