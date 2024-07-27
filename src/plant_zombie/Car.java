/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package plant_zombie;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author dell
 */
public class Car{
    private boolean isExist=true;
    private boolean isRun=false;
    private int x;
    private int y;
    private int row;
    private int col;
    private Image img;
    private ArrayList<Car> listCar;
    private ArrayList<ArrayList<Zombie>> matZombie;
    public Car(int row, int col, ArrayList<Car> listCar,ArrayList<ArrayList<Zombie>> matZombie) {
        this.listCar=listCar;
        this.matZombie=matZombie;
        this.row=row;
        this.col=col;
        this.x=col*Plant_Zombie.widthSquare;
        this.y=row*Plant_Zombie.heightSquare+140;
        this.img=new ImageIcon("src\\plant_zombie\\Image\\LawnCleaner.png").getImage();
    }

    public void Draw(Graphics2D g2d, ImageObserver observer) {
        check();
        if(isExist)
        {
            if(isRun)
            {
                img=new ImageIcon("src\\plant_zombie\\Image\\LawnCleaner1.png").getImage();
                x+=8;
                if(x>=1330){
                    isExist=false;
                    listCar.remove(this);
                }else
                {
                    Zombie zombie=nearZombie();
                    if(zombie!=null)
                    {
                        if(zombie.getX()<=x)
                        {
                            zombie.setHealth(0);
                        }
                    }
                }
            }
            g2d.drawImage(img, x, y, 50, 50, observer);
        }
    }
    public void check()
    {
        if(isExist)
        {
            ArrayList<Zombie> array=matZombie.get(row);
            for(Zombie z:array)
            {
                if(z.getX()<=x)
                    isRun=true;
            }
        }
    }
    public Zombie nearZombie()
    {
         Zombie zombie=null;
         int min=1330;
         ArrayList<Zombie> array=matZombie.get(row);
            for(Zombie z:array)
            {
                if(z.getX()<min)
                {
                    min=z.getX();
                    zombie=z;
                }
            }
            return zombie;
    }
}
