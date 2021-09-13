package com.playper3.space.managers;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class AssetManagers {

    public final AssetManager assetManager = new AssetManager();

    //assets
    public final String playerSprite = "playerSprite.png";
    public final String playerSpriteTXT = "playerSprite.TXT";
    public final String menuButton1 = "testButton.png";
    public final String mainMenuSong = "C418 - 0x10c.mp3";
    public final String endlessSpace = "EndlessSpace.mp3";
//    public final String  = "";

    public void mainMenuAssets() {
        assetManager.load(mainMenuSong, Sound.class);
        assetManager.load(endlessSpace , Music.class);
        assetManager.load(menuButton1, Texture.class);
        //assetManager.load(, );
        assetManager.finishLoading();
    }
}
