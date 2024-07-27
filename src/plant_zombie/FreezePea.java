/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package plant_zombie;

import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.io.File;
import javax.swing.ImageIcon;

/**
 *
 * @author dell
 */
public  class FreezePea extends Pea{
    
    public FreezePea(int x, int y) {
        super(x, y);
        setImg(new ImageIcon("src\\plant_zombie\\Image\\freezepea.png").getImage());
    }
    
    
}
