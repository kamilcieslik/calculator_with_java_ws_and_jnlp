package com.github.kamilcieslik.academic.calculator_with_java_ws_and_jnlp;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

public class ButtonSoundPlayer {
    private String filename;
    private Player player;

    public ButtonSoundPlayer(String filename) {
        this.filename = filename;
    }

    public void close() {
        if (player != null) player.close();
    }

    public void play() {
        try {
            FileInputStream fis = new FileInputStream(filename);
            BufferedInputStream bis = new BufferedInputStream(fis);
            player = new Player(bis);
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

