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
import plant_zombie.FreezePea;
import plant_zombie.Pea;
import plant_zombie.Plant;
import plant_zombie.Plant_Zombie;
import plant_zombie.Zombie;

/**
 *
 * @author dell
 */
public class FreezePeaShooter extends Plant{
    private FreezePea pea;
    private Zombie zombie;
    Timer timer=new Timer();


    public FreezePeaShooter(int row, int col, int power, ArrayList<ArrayList<Plant>> matPlant, ArrayList<ArrayList<Zombie>> matZombie) {
        super(row, col, power, matPlant, matZombie);
                setImg(new ImageIcon("src\\plant_zombie\\Image\\freezepeashooter.gif").getImage());
        pea=new FreezePea(getX()+80, getY()+10);
    }

   

    public Pea getPea() {
        return pea;
    }

    public void setPea(Pea pea) {
        this.pea = (FreezePea) pea;
    }
    
    
    @Override
    public void Draw(Graphics2D g2d, ImageObserver observer) {
        if(getHealth()>0){
            if(isIsSet()){
              g2d.drawImage(getImgSet(), getX(), getY()+50, 100, 100,  observer);
            }
          g2d.drawImage(getImg(), getX(), getY(), width, heigth,  observer);
          if(pea.isState())
           pea.Draw(g2d, observer);
        }
        else{
            getMatPlant().get(getRow()).remove(this);
            timer.cancel();
        }
        
    }
    public void Timer()
    {
        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //System.out.println("Timer Pea");
                if (lastestCol() != 1330) {
                    if (pea.getX() < lastestCol() + 100) {
                        pea.setX(pea.getX() + 8);
                    } else {
                        if(zombie!=null){
                            zombie.recieveEffect(pea);
                            pea.audioPea();
                        }
                        pea.setState(false);
                        zombie.setHealth(zombie.getHealth() - 10);
                        timer.cancel();
                    }
                }else
                    pea.setState(false);
            }
        }, 0,10);
    }
    public int lastestCol()
    {
        int min=1330;
        ArrayList<Zombie> array=getMatZombie().get(getRow());
        for(Zombie z:array)
        {
            if(z.getX()<min){
                min=z.getX();
                zombie=z;
            }
        }
        return min;
    }
    

    public int getWidth() {
        return width;
    }

    public int getHeigth() {
        return heigth;
    }

    @Override
    public void Update(int row, int col) {
        setRow(row);
        setCol(col);
        setX(col*Plant_Zombie.widthSquare+55);
        setY(row*Plant_Zombie.heightSquare+115);
        pea.setX(getX()+80);
        pea.setY(getY()+10);
        pea.setPreX(pea.getX());
        pea.setPreY(pea.getY());
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                pea.setState(true);
                pea.setX(pea.getPreX());
                Timer();
            }
        }, 1000,5000);
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
