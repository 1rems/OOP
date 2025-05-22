package Proje;

import java.net.URL;

import javafx.scene.media.AudioClip;

public class SoundClass implements SoundPlayer {
    @Override
    public void playSound(String soundFile) {
        try {
            // 1) Parametreyi kullan, 2) toExternalForm ile URI formata çevir
            URL res = getClass().getResource("/sound.mp3");
            if (res == null) {
                System.err.println("Sound file bulunamadı: " + soundFile);
                return;
            }
            AudioClip clip = new AudioClip(res.toExternalForm());
            clip.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
