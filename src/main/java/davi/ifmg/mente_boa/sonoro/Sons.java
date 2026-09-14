package davi.ifmg.mente_boa.sonoro;

import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sons {
    private static void tocar(String caminho) {
        try {
            URL url = Sons.class.getResource(caminho);
            AudioInputStream audio = AudioSystem.getAudioInputStream(url);

            Clip tocador = AudioSystem.getClip();
            tocador.open(audio);
            tocador.start();
        } catch (Exception ex) {
            Logger.getLogger(Sons.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void clicar() {
        tocar("/sons/click.wav");
    }
}
