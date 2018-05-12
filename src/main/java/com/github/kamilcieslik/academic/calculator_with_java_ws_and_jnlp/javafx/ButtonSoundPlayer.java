package com.github.kamilcieslik.academic.calculator_with_java_ws_and_jnlp.javafx;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.InputStream;

public class ButtonSoundPlayer {
    private InputStream inputStream;
    private Player player;

    public ButtonSoundPlayer(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void close() {
        if (player != null) player.close();
    }

    public void play() {
        try {
            player = new Player(inputStream);
        } catch (Exception ignored) {
        }

        new Thread(() -> {
            try {
                player.play();
            } catch (JavaLayerException ignored) {
            }
        }).start();
    }
}

