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
public class Wallnut extends plant_zombie.Plant{

    public Wallnut(int row, int col, int power, ArrayList<ArrayList<Plant>> matPlant, ArrayList<ArrayList<Zombie>> matZombie) {
        super(row, col, power, matPlant, matZombie);
        setImg(new ImageIcon("D:\\java\\Plant_Zombie\\src\\plant_zombie\\Image\\Wallnut.gif").getImage());
        setHealth(500);
    }


    @Override
    public void Draw(Graphics2D g2d, ImageObserver observer) {
        if(getHealth()>0){
            if(isIsSet()){
              g2d.drawImage(getImgSet(), getX(), getY()+50, 100, 100,  observer);
            }
            if(getHealth()<300)
                setImg(new ImageIcon("D:\\java\\Plant_Zombie\\src\\plant_zombie\\Image\\Wallnut_cracked1.gif").getImage());
            if(getHealth()<150)
                setImg(new ImageIcon("D:\\java\\Plant_Zombie\\src\\plant_zombie\\Image\\Wallnut_cracked2.gif").getImage());
            g2d.drawImage(getImg(), getX(), getY(),width , heigth,  observer);
        }else
        {
          getMatPlant().get(getRow()).remove(this);
        }
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
