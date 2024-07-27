/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package plant_zombie;

import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;

/**
 *
 * @author dell
 */
public  class Pea extends Object{
    private int preX;
    private int preY;
    private boolean state;
    private File fileAudio=new File("src\\plant_zombie\\Sound\\Pea.wav");
    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
      
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

    public File getFileAudio() {
        return fileAudio;
    }

    public void setFileAudio(File fileAudio) {
        this.fileAudio = fileAudio;
    }
    
    public Pea(int x, int y) {
        super(x, y);
        preX=x;
        preY=y;
        state=false;
        setImg(new ImageIcon("src\\plant_zombie\\Image\\pea.png").getImage());
    }
   

    @Override
    public void Draw(Graphics2D g2d, ImageObserver observer) {
        g2d.drawImage(getImg(), getX(), getY(), 30, 30, observer);
    }

    public void audioPea()
    {
       new Sound().sound(fileAudio);
    }
}
