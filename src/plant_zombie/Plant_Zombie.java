/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package plant_zombie;

import java.awt.CardLayout;
import plant_zombie.Plants.FreezePeaShooter;
import plant_zombie.Plants.SunFlower;
import plant_zombie.Plants.PeaShooter;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import plant_zombie.Plants.Chomper;
import plant_zombie.Plants.Squash;
import plant_zombie.Plants.Wallnut;

/**
 *
 * @author dell
 */
public class Plant_Zombie extends JPanel implements Runnable, Action {

    private BackGround background;
    public static final int widthSquare = 142;
    public static final int heightSquare = 130;
    public static int powerSun = 125;
    private Thread gameThread;
    private Mouse mouse = new Mouse();
    private Plant acticeCard;
    private ArrayList<Card> cards = new ArrayList<>();
    private  ArrayList<ArrayList<Plant>> matPlant = new ArrayList<>();
    private  ArrayList<ArrayList<Zombie>> matZombie = new ArrayList<>();
    private final int speed = 25000;
    private int count = 0;
    private final int maxZombie = 20;
    private  ArrayList<ZombieDie> zombieDies = new ArrayList<>();
    private  ArrayList<Car> listCar = new ArrayList<>();
    private ChoosePlant choosePlant = new ChoosePlant(cards);
    private boolean isPlay = false;
    private  boolean isEnd = false;
    public static boolean beEaten=false;
    private boolean phrase2=false;
    private Clip soundBackground=new Sound().Background(new File("src\\plant_zombie\\Sound\\pvzBG1.wav"));
    private Timer timePhrase1=new Timer();
    private Timer timePhrase2=new Timer();
    public Plant_Zombie() {
        setSize(1400, 800);
        background = new BackGround(1400, 800);

        ArrayList<Zombie> zombiesRow0 = new ArrayList<>();
        ArrayList<Zombie> zombiesRow1 = new ArrayList<>();
        ArrayList<Zombie> zombiesRow2 = new ArrayList<>();
        ArrayList<Zombie> zombiesRow3 = new ArrayList<>();
        ArrayList<Zombie> zombiesRow4 = new ArrayList<>();

        matZombie.add(zombiesRow0);
        matZombie.add(zombiesRow1);
        matZombie.add(zombiesRow2);
        matZombie.add(zombiesRow3);
        matZombie.add(zombiesRow4);

        ArrayList<Plant> plantRow0 = new ArrayList<>();
        ArrayList<Plant> plantRow1 = new ArrayList<>();
        ArrayList<Plant> plantRow2 = new ArrayList<>();
        ArrayList<Plant> plantRow3 = new ArrayList<>();
        ArrayList<Plant> plantRow4 = new ArrayList<>();

        matPlant.add(plantRow0);
        matPlant.add(plantRow1);
        matPlant.add(plantRow2);
        matPlant.add(plantRow3);
        matPlant.add(plantRow4);

        listCar.add(new Car(0, 0,listCar,matZombie));
        listCar.add(new Car(1, 0,listCar,matZombie));
        listCar.add(new Car(2, 0,listCar,matZombie));
        listCar.add(new Car(3, 0,listCar,matZombie));
        listCar.add(new Car(4, 0,listCar,matZombie));

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
//                CreatZombie();
                addMouseListener(mouse);
                addMouseMotionListener(mouse);
                launchGame();
            }
        });
    }

    public void launchGame() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void handleSetPlant() {
        if (mouse.isPressed()) {
            if (acticeCard == null) {
                if (mouse.getX() >= 155 && mouse.getX() <= 795 && mouse.getY() >= 10 && mouse.getY() <= 103) {
                    System.out.println("X: " + mouse.getX());
                    for (Card i : cards) {
                        if (i.getCol() == (mouse.getX() - 155) / Card.width && i.getPower() <= powerSun) {
                            switch (i.getName()) {
                                case "Sunflower":

                                    acticeCard = new SunFlower(0, 0, 50,matPlant,matZombie);
                                    acticeCard.setPreX(i.getPreX());
                                    acticeCard.setPreY(i.getPreY());
                                    acticeCard.setX(i.getPreX());
                                    acticeCard.setY(i.getPreY());
                                    break;
                                case "Peashooter":

                                    acticeCard = new PeaShooter(0, 0, 100,matPlant,matZombie);
                                    acticeCard.setPreX(i.getPreX());
                                    acticeCard.setPreY(i.getPreY());
                                    acticeCard.setX(i.getPreX());
                                    acticeCard.setY(i.getPreY());
                                    break;
                                case "Freezepeashooter":
                                    acticeCard = new FreezePeaShooter(0, 0, 175,matPlant,matZombie);
                                    acticeCard.setPreX(i.getPreX());
                                    acticeCard.setPreY(i.getPreY());
                                    acticeCard.setX(i.getPreX());
                                    acticeCard.setY(i.getPreY());
                                    break;
                                case "Wallnut":
                                    acticeCard = new Wallnut(0, 0, 50,matPlant,matZombie);
                                    acticeCard.setPreX(i.getPreX());
                                    acticeCard.setPreY(i.getPreY());
                                    acticeCard.setX(i.getPreX());
                                    acticeCard.setY(i.getPreY());
                                    break;
                                case "Chomper":
                                    acticeCard = new Chomper(0, 0, 150,matPlant,matZombie);
                                    acticeCard.setPreX(i.getPreX());
                                    acticeCard.setPreY(i.getPreY());
                                    acticeCard.setX(i.getPreX());
                                    acticeCard.setY(i.getPreY());
                                    break;
                                case "Squash":
                                    acticeCard = new Squash(0, 0, 50,matPlant,matZombie);
                                    acticeCard.setPreX(i.getPreX());
                                    acticeCard.setPreY(i.getPreY());
                                    acticeCard.setX(i.getPreX());
                                    acticeCard.setY(i.getPreY());
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                }
            } else {
                acticeCard.setX(mouse.getX() - (Card.width / 2));
                acticeCard.setY(mouse.getY() - (Card.height / 2));
            }
        } else {
            if (acticeCard != null) {
                int x = mouse.getX();
                int y = mouse.getY();
                if (x >= 55 && x <= 1333 && y >= 115 && y <= 765) {
                    System.out.println("Mouse(" + x + "," + y + ")");
                    int col = (x - 55) / widthSquare;
                    int row = (y - 115) / heightSquare;
                    if (canMove(row, col)) {
                        acticeCard.Update(row, col);
                        new Sound().sound(new File("src\\plant_zombie\\Sound\\seedlift.wav"));
                        powerSun -= acticeCard.getPower();
                        matPlant.get(acticeCard.getRow()).add(acticeCard);
                        // plants.add(acticeCard);
                        System.out.println("X: " + acticeCard.getX() + " Y: " + acticeCard.getY());
                        if (acticeCard instanceof SunFlower) {
                            addMouseListener((SunFlower) acticeCard);
                        }

                    } else {
                        acticeCard.setX(acticeCard.getPreX());
                        acticeCard.setY(acticeCard.getPreY());
                    }
                } else {
                    acticeCard.setX(acticeCard.getPreX());
                    acticeCard.setY(acticeCard.getPreY());
                }
                acticeCard = null;
            }
        }
    }

    public void Update() {

        if (isPlay) {
            handleSetPlant();
            if (checkWin()) {
                isEnd = true;
            }
            if(ZombieEmpty() && count>=maxZombie/2 && count<=maxZombie && phrase2)
            {
                new Sound().sound(new File("src\\plant_zombie\\Sound\\siren.wav"));
                
                    timePhrase2.schedule(new TimerTask() {
                        @Override
                        public void run() {
                                count++;
                                Random random = new Random();
                                int row = random.nextInt(5);
                                matZombie.get(row).add(new Zombie(row, Plant_Zombie.this,matPlant,matZombie));
                                if (count >= maxZombie) {
                                    timePhrase2.cancel();
                                }
                        }
                    }, 3000, 1000);
                phrase2=false;
            }
        } else {
            if (mouse.isPressed()) {
                //System.out.println("clicked");
                int x = mouse.getX();
                int y = mouse.getY();
                if (x >= choosePlant.getXFrame() + 30 && x <= choosePlant.getXFrame() + 620 && y >= choosePlant.getYFrame() + 50 && y <= choosePlant.getYFrame() + 510) {
                    choosePlant.clicked(mouse);
                } else if (x >= 155 && x <= 795 && y >= 10 && y <= 103) {
                    for (int i = 0; i < cards.size(); i++) {
                        if (cards.get(i).getCol() == (mouse.getX() - 155) / Card.width) {
                            cards.remove(cards.get(i));
                            ReArrange();
                            mouse.setPressed(false);
//                           break;
                        }
                    }
                } else if (x >= 418 && x <= 633 && y >= 704 && y <= 749) {
                    if (cards.size() > 0) {
                        isPlay = true;
                        CreatZombie();
                    }
                }
            }
        }
    }

    public void ReArrange() {
        for (int i = 0; i < cards.size(); i++) {
            cards.get(i).update(i);
        }
    }

    public void CreatZombie() {
        timePhrase1.schedule(new TimerTask() {
            @Override
            public void run() {
                count++;
                Random random = new Random();
                int row = random.nextInt(5);
                matZombie.get(row).add(new Zombie(row, Plant_Zombie.this,matPlant,matZombie));
                if (count >= maxZombie / 2) {
                    phrase2=true;
                    timePhrase1.cancel();
                }
            }
        }, 3000, speed);
    }
    public boolean ZombieEmpty()
    {
        for(int i=0;i<5;i++)
        {
            if(!matZombie.get(i).isEmpty())
                return false;
        }
        return true;
    }
    public boolean canMove(int targetRow, int targetCol) {

        for (Plant p : matPlant.get(targetRow)) {
            if (p.getRow() == targetRow && p.getCol() == targetCol) {
                return false;
            }
        }
        return true;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        Graphics2D g2d = (Graphics2D) g.create();
        //paint background
        background.Draw(g2d, this);
        //paint car
        for (int i = 0; i < listCar.size(); i++) {
            listCar.get(i).Draw(g2d, this);
        }
        //paint card of plant
        for (Card i : cards) {
            i.Draw(g2d, this);
        }
        if (!isPlay) {
            choosePlant.Draw(g2d, this);
        } else {
            if (acticeCard != null) {
                acticeCard.Draw(g2d, this);
            }
            //paint plant in list plants
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < matPlant.get(i).size(); j++) {
                    //System.out.println("Paint");
                    matPlant.get(i).get(j).Draw(g2d, this);
                }
            }

            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < matZombie.get(i).size(); j++) {
                    matZombie.get(i).get(j).Draw(g2d, this);
                }
            }
            //zombie die
            for (int i = 0; i < zombieDies.size(); i++) {
                g2d.drawImage(zombieDies.get(i).getImgHead(), zombieDies.get(i).getX() + 50, zombieDies.get(i).getY(), 220, 220, this);
                g2d.drawImage(zombieDies.get(i).getImgBody(), zombieDies.get(i).getX(), zombieDies.get(i).getY(), 220, 220, this);
            }
        }

        g2d.setColor(Color.red);
        g2d.drawString(String.valueOf(powerSun), 75, 100);
    }

    public boolean checkWin() {
        if(beEaten)
        {
            soundBackground.close();
            new Sound().sound(new File("src\\plant_zombie\\Sound\\Failure.wav"));
            JOptionPane.showMessageDialog(this, "YOU LOST");
            return true;
        }
        if (count >= maxZombie) {
            for (int i = 0; i < 5; i++) {
                if (!matZombie.get(i).isEmpty()) {
                    return false;
                }
            }
            JOptionPane.showMessageDialog(this, "YOU WIN");
            soundBackground.close();
            return true;
        }
        return false;
    }

    @Override
    public void run() {
        soundBackground.start();
        double drawInterval = 1000000000 / 60;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        while (gameThread != null) {
            System.out.println("Zombie: "+count);
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            if (delta >= 1) {
                Update();
                repaint();
                delta--;
                if (isEnd) {
                    gameThread=null;
                    timePhrase1.cancel();
                    timePhrase2.cancel();
                    CardLayout cardlayout = (CardLayout) getParent().getLayout();
                    cardlayout.show(getParent(), "intro");
                    getParent().remove(this);
                    beEaten=false;
                    break;
                }
            }
        }
    }
    
    @Override
    public void ZombieDie(ZombieDie zombieDie) {
        zombieDies.add(zombieDie);

        Timer time = new Timer();
        time.schedule(new TimerTask() {
            @Override
            public void run() {
                zombieDies.remove(zombieDie);
            }
        }, 2300);
    }

}
