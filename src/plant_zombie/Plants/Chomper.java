/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package plant_zombie.Plants;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.io.File;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import plant_zombie.Plant;
import plant_zombie.Plant_Zombie;
import plant_zombie.Sound;
import plant_zombie.Zombie;

/**
 *
 * @author dell
 */
public class Chomper extends plant_zombie.Plant{
    private boolean isEate=true;  

    public Chomper(int row, int col, int power, ArrayList<ArrayList<Plant>> matPlant, ArrayList<ArrayList<Zombie>> matZombie) {
        super(row, col, power, matPlant, matZombie);
                setImg(new ImageIcon("src\\plant_zombie\\Image\\Chomper.gif").getImage());
        setHealth(100);
    }

    @Override
    public void Draw(Graphics2D g2d, ImageObserver observer){
        if(getHealth()>0){
            if(isIsSet()){
              g2d.drawImage(getImgSet(), getX(), getY()+50, 100, 100,  observer);
            }
              
           eating();
           g2d.drawImage(getImg(), getX(), getY()-50, 150, 150, observer);
        }
        else
        {
            getMatPlant().get(getRow()).remove(this);
            
        }
    }
    public void eating()
    {
        if(!isEate){
        ArrayList<Zombie> array=getMatZombie().get(getRow());
        for(Zombie z:array)
        {
            if(z.getX()<=getX()+10){
                //eat zombie
                isEate=true;
                Image img=new ImageIcon("src\\plant_zombie\\Image\\ChomperAttack.gif").getImage();
                img.flush();
                setImg(img);
                new Sound().sound(new File("src\\plant_zombie\\Sound\\chomperattack.wav"));
                Timer timer=new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        z.setCause("Chomper");
                        z.setHealth(0);
                        setImg(new ImageIcon("src\\plant_zombie\\Image\\ChomperDigest.gif").getImage());
                        Timer time=new Timer();
                        time.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                  isEate=false;  
                                  setImg(new ImageIcon("src\\plant_zombie\\Image\\Chomper.gif").getImage());
                            }
                        }, 50000);
                    }
                }, 1100);
            }
        }}
    }
    @Override
    public void Timer() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public void Update(int row, int col) {
         setRow(row);
        setCol(col);
        setX(col*Plant_Zombie.widthSquare+55);
        setY(row*Plant_Zombie.heightSquare+115);
        isEate=false;
        setIsSet(true);
        Timer setTime=new Timer();
        setTime.schedule(new TimerTask() {
            @Override
            public void run() {
                setIsSet(false);
            }
        }, 500);
    }
    
}
