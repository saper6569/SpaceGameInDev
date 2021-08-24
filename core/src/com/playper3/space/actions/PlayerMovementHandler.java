package com.playper3.space.actions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class PlayerMovementHandler {

    public static String movement() {
        String movementDirection;

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            movementDirection = "forward";
            boolean isRunning = false;
            if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
                isRunning = true;
            }


        }
        else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            movementDirection = "backward";
            boolean isRunning = false;
            if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
                isRunning = true;
            }

        }
        else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            movementDirection = "right";
            boolean isRunning = false;
            if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
                isRunning = true;
            }

        }
        else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            movementDirection = "left";
            boolean isRunning = false;
            if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
                isRunning = true;
            }

        }
        else {
            movementDirection = "stasis";
        }

        return movementDirection;
    }
}