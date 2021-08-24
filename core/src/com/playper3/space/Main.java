package com.playper3.space;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.playper3.space.screens.MainMenu;

public class Main extends Game {
    public void create() {
        this.setScreen(new MainMenu());
    }
}





