/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package plant_zombie.Plants;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import plant_zombie.Plant;
import plant_zombie.Plant_Zombie;
import plant_zombie.Zombie;

/**
 *
 * @author dell
 */
public class Squash extends plant_zombie.Plant{
    private boolean squash=true;

    public Squash(int row, int col, int power, ArrayList<ArrayList<Plant>> matPlant, ArrayList<ArrayList<Zombie>> matZombie) {
        super(row, col, power, matPlant, matZombie);
        setImg(new ImageIcon("src\\plant_zombie\\Image\\Squash.gif").getImage());
    }

    @Override
    public void Draw(Graphics2D g2d, ImageObserver observer) {
        if (getHealth() > 0) {
            eating();
            if(isIsSet()){
              g2d.drawImage(getImgSet(), getX(), getY()+50, 100, 100,  observer);
            }
            g2d.drawImage(getImg(), getX(), getY()-250, 130, 350, observer);
        } else {
            getMatPlant().get(getRow()).remove(this);
        }   
    }

    @Override
    public void Timer() {
        
    }
    public void eating()
    {
       if(!squash)
       {
            ArrayList<Zombie> array=getMatZombie().get(getRow());
        for(Zombie z:array)
        {
            if(z.getX()<=getX()+15){
                //eat zombie
                if(!squash){
                    setX(getX()+100);
                    squash=true;
                }
                Image img=new ImageIcon("src\\plant_zombie\\Image\\SquashAttack.gif").getImage();
                img.flush();
                setImg(img);
                Timer timer=new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        z.setCause("Chomper");
                        z.setHealth(0);
                        Timer time=new Timer();
                        time.schedule(new TimerTask() {
                            @Override
                            public void run() { 
                                  setHealth(0);
                            }
                        }, 1100);
                    }
                }, 300);
                break;
            }
        }
       }
    }
    @Override
    public void Update(int row, int col) {
        setRow(row);
        setCol(col);
        setX(col*Plant_Zombie.widthSquare+55);
        setY(row*Plant_Zombie.heightSquare+115);
        squash=false;
        setIsSet(true);
        Timer setTime=new Timer();
        setTime.schedule(new TimerTask() {
            @Override
            public void run() {
                setIsSet(false);
            }
        }, 5000);
    }
    
}
