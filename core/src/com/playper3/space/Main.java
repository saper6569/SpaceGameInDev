package com.playper3.space;

import com.badlogic.gdx.Game;
import com.playper3.space.screens.MainMenu;

//set screen to main menu
public class Main extends Game {

    @Override
    public void create() {

        setScreen(new MainMenu());

    }
}





