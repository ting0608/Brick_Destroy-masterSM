package Sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;


/**
 * Created by a 189cm lengzaii, tingcc.
 * @author tingcc
 * @since 11/11/2021
 * declare CrackSound as String, and search with string path in file
 */
public class Sound {

    String CrackSound;
    SoundEffect se = new SoundEffect();
    public Sound(){

        // Type your audio file name in the res folder
        CrackSound = "Sound/soundEffect_01.wav";
        se.setFile(CrackSound);
        se.play();

    }


    /**
     * use try catch to set file and use clip to allow sound effect be play
     * Use AudioSystem.getAudioInputStream(file) to get file
     */
    public class SoundEffect {

        Clip clip;

        public void setFile(String soundFileName){

            try{
                File file = new File(soundFileName);
                AudioInputStream sound = AudioSystem.getAudioInputStream(file);
                clip = AudioSystem.getClip();
                clip.open(sound);
            }
            catch(Exception e){

            }
        }

        /**
         * start() to start playing sound effect
         */
        public void play(){

            clip.setFramePosition(0);
            clip.start();
        }

    }


}