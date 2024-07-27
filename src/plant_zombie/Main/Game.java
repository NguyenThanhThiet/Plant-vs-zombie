/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package plant_zombie.Main;

import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import plant_zombie.Intro;
import plant_zombie.Plant_Zombie;

/**
 *
 * @author dell
 */
public class Game {
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame jframe = new JFrame();
        JPanel jpanel = new JPanel();

        Intro intro = new Intro();
        Plant_Zombie plant_Zombie = new Plant_Zombie();

        jpanel.setLayout(new CardLayout());
        jpanel.add(intro, "intro");

        jpanel.setSize(1400, 800);

        jframe.add(jpanel);

        jframe.setLayout(null);
        jframe.setSize(1400, 800);
        jframe.setVisible(true);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setLocationRelativeTo(null);
    }
}
