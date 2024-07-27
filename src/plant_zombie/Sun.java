/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package plant_zombie;

import com.sun.java.accessibility.util.AWTEventMonitor;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;
import javax.swing.ImageIcon;
import org.w3c.dom.events.MouseEvent;

/**
 *
 * @author dell
 */
public class Sun extends Object {
    private boolean state;
public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
    public Sun(int x, int y) {
        super(x, y);
        state=false;
        setImg(new ImageIcon("src\\plant_zombie\\Image\\sun.gif").getImage());
    }
   

    @Override
    public void Draw(Graphics2D g2d, ImageObserver observer) {
        g2d.drawImage(getImg(), getX(), getY(), 70, 70, observer);
    }

    

  
    
}
