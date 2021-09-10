package com.playper3.space.sprites.logic;

import com.badlogic.gdx.Gdx;
import com.playper3.space.loggers.DebugLogger;

import java.util.Random;

public class PlayerLogic {

    private int hunger;
    private int hydration;
    private int health;
    private String room;

    private int count;
    private float time;

    private DebugLogger logger;

    public PlayerLogic() {
        hunger = 10;
        hydration = 10;
        health = 100;
        room = "Main";

        count = 0;
        time = 0;

        logger = new DebugLogger();
    }

    public void isMoving(boolean state, float dt) {
        if (state = true) {
            if (dt == 1) {
                timerLogic();
                logger.log("hi");
            }
        }
    }

    public int getHealth() {
        return health;
    }

    public int getHydration() {
        return hydration;
    }

    public int getHunger() {
        return hunger;
    }

    public String getRoom() {
        return room;
    }

    public void timerLogic() {
        Random hungerLoss = new Random();
        int chance = 0;
        //if (hungerLoss.nextInt(chance) == 1 || hunger != 0) {
            hunger--;
        //}
    }
}
