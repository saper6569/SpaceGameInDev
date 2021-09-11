package com.playper3.space.sprites.logic;

import com.playper3.space.loggers.DebugLogger;
import java.util.Random;

public class PlayerLogic {

    public int hunger = 10;
    private int hydration = 10;
    private int health = 100;
    private String room = "Main";

    private int count = 0;
    private float time = 0;

    private DebugLogger logger;

    public PlayerLogic() {
        logger = new DebugLogger();
    }

    public int isMoving(boolean state) {
        if (state == true) {
            count++;
            if (count == 100) {
                Random hungerLoss = new Random();
                int chance = 4;
                int hungerDeduction = hungerLoss.nextInt(chance);
                if (hungerDeduction == 1 & hunger != 0) {
                    hunger--;
                }
                count = 0;
            }
        }
        return hunger;
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
}
