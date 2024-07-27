/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package plant_zombie;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineUnavailableException;

/**
 *
 * @author dell
 */
public class Sound{
    private boolean isSound=false;

    public boolean isIsSound() {
        return isSound;
    }

    public void setIsSound(boolean isSound) {
        this.isSound = isSound;
    }
    
    public  void sound(File file) {
        if(!isSound)
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(file.getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
        } catch (Exception e) {
               e.printStackTrace();
        }
    }
    public Clip Background(File file)
    {
        if(!isSound)
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(file.getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    clip.setFramePosition(0);  // Đưa clip về vị trí đầu
                    clip.start();              // Bắt đầu lại clip
                }
            });
            return clip;
        } catch (Exception e) {
               e.printStackTrace();
        }
        return null;
    }
    
    
}
