/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package plant_zombie;

import java.awt.Image;

/**
 *
 * @author dell
 */
public class ZombieDie {
    private int x;
    private int y;
    private Image imgHead;
    private Image imgBody;

    public ZombieDie(int x, int y, Image imgHead, Image imgBody) {
        this.x = x;
        this.y = y;
        this.imgHead = imgHead;
        this.imgBody = imgBody;
        imgHead.flush();
        imgBody.flush();
    }

    public Image getImgHead() {
        return imgHead;
    }

    public void setImgHead(Image imgHead) {
        this.imgHead = imgHead;
    }

    public Image getImgBody() {
        return imgBody;
    }

    public void setImgBody(Image imgBody) {
        this.imgBody = imgBody;
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
    
}
