package com.playper3.space.managers;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;

public class AssetManagers {

    public final AssetManager assetManager = new AssetManager();

    //assets
    public final String playerSprite = "Sprites/playerSprite.png";
    public final String playerSpriteTXT = "Sprites/playerSprite.TXT";
    public final String menuButton1 = "MainMenu/testButton.png";
    public final String mainMenuSong = "MainMenu/C418 - 0x10c.mp3";
    public final String endlessSpace = "MainMenu/EndlessSpace.mp3";
    public final String spaceship1 = "GameScreen1/spaceShip.tmx";

    public void mainMenuAssets() {
        assetManager.load(mainMenuSong, Music.class);
        assetManager.load(endlessSpace , Music.class);
        assetManager.load(menuButton1, Texture.class);
        //assetManager.load(, );
        assetManager.finishLoading();
    }

    public void gameScreen1Assets() {
        assetManager.load(spaceship1, TiledMap.class);
    }
}
