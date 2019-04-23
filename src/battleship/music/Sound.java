package battleship.music;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class Sound {
    private Clip clip;
    public Sound(String fileName) {
        try {
            File file = new File(fileName);
            AudioInputStream sound = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(sound);
        }
        catch (FileNotFoundException e) {
            System.out.println("Файл " + fileName +" не найден");
        }
        catch (UnsupportedAudioFileException e) {
            System.out.println("Неподдерживаемый формат: ");
        }
        catch (IOException e) {
            System.out.println("Ошибка чтения записи: "+fileName);
        }
        catch (LineUnavailableException e) {
            System.out.println("Line Unavailable Exception Error: " + fileName);
        }

    }

    public void play(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
    }
}

