/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package plant_zombie;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import javax.accessibility.AccessibleContext;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.event.EventListenerList;
import javax.swing.plaf.ComponentUI;

/**
 *
 * @author dell
 */
public class ChoosePlant extends JPanel{
    private ArrayList<Card> listCard;
    private ArrayList<Card> cardResource=new ArrayList<>();
    private final int widthCard=68;
    private  final int heightCard=90;
    private final int xFrame=200;
    private final int yFrame=120;
    private int x=xFrame+30;
    private int y=yFrame+50;
    private int rowSelect=1;
    private int colSelect=-1;
    private int count=0;
    public ChoosePlant(ArrayList<Card> listCard){
        this.listCard=listCard;
        setBounds(200, 120,  650,650);
        
        cardResource.add(new Card(50,new ImageIcon("src\\plant_zombie\\Image\\card_sunflower.jpg").getImage(), "Sunflower"));
        cardResource.add(new Card(100,new ImageIcon("src\\plant_zombie\\Image\\card_peashooter.jpg").getImage(), "Peashooter"));
        cardResource.add(new Card(175,new ImageIcon("src\\plant_zombie\\Image\\card_freezepeashooter.jpg").getImage(), "Freezepeashooter"));
        cardResource.add(new Card(50,new ImageIcon("src\\plant_zombie\\Image\\card_wallnut.jpg").getImage(), "Wallnut"));
        cardResource.add(new Card(150,new ImageIcon("src\\plant_zombie\\Image\\card_chomper.jpg").getImage(), "Chomper"));
        cardResource.add(new Card(50,new ImageIcon("src\\plant_zombie\\Image\\card_squash.jpg").getImage(), "Squash")); 
    }

    public void Draw(Graphics2D g2d,ImageObserver observer) {
        Image img=new ImageIcon("D:\\java\\Plant_Zombie\\src\\plant_zombie\\Image\\ChoosePlant.jpg").getImage();
        g2d.drawImage(img, 200, 120,  650,650, observer); 
        for(int i=0;i<cardResource.size();i++)
        {
            g2d.drawImage(cardResource.get(i).getImg(),x, y,widthCard,heightCard, observer);
            if(colSelect==i){
                g2d.setColor(Color.red);
                g2d.drawRect(x, y, widthCard, heightCard);
              }
            x+=(widthCard+7);
            if(i==cardResource.size()-1)
                x=xFrame+30; 
        }
       if(listCard.size()<1)
       {
            g2d.setColor(Color.black);
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
            g2d.fillRect(418, 704, 215, 45);
       }
    }
    public boolean existCard(Card key)
    {
        for(Card i:listCard)
        {
            if(i.getName().equals(key.getName()))
                return true;
        }
        return false;
    }
    public void clicked(Mouse e)
    {
        int x=e.getX();
        int y=e.getY();
        int col=(x-(xFrame+30))/(widthCard+7);
        if(x>=col*(widthCard+7)+(xFrame+30) && x<=col*(widthCard+7)+(xFrame+30)+widthCard)
        {
            colSelect=col;
            for(int i=0;i<cardResource.size();i++)
            {
                if (colSelect == i) {
                    if (!existCard(cardResource.get(i)) && listCard.size() <= 3) {
                        listCard.add(new Card(listCard.size(), cardResource.get(i).getPower(), cardResource.get(i).getImg(), cardResource.get(i).getName()));
                        colSelect = -1;
                    }
                }
            }
        }            
    }

    public ArrayList<Card> getListCard() {
        return listCard;
    }

    public void setListCard(ArrayList<Card> listCard) {
        this.listCard = listCard;
    }

    public ArrayList<Card> getCardResource() {
        return cardResource;
    }

    public void setCardResource(ArrayList<Card> cardResource) {
        this.cardResource = cardResource;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getRowSelect() {
        return rowSelect;
    }

    public void setRowSelect(int rowSelect) {
        this.rowSelect = rowSelect;
    }

    public int getColSelect() {
        return colSelect;
    }

    public void setColSelect(int colSelect) {
        this.colSelect = colSelect;
    }

    public int getXFrame()
    {
        return xFrame;
    }
    
    public int getYFrame()
    {
        return yFrame;
    }
}
