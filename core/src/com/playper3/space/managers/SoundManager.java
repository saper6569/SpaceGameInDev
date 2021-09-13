package com.playper3.space.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class SoundManager {

    private Music menuSong;
    private int count = 0;
    private int countSec = 0;

    public SoundManager() {
        menuSong = Gdx.audio.newMusic(Gdx.files.internal("C418 - 0x10c.mp3"));
    }
}
