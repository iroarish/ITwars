package com.project.undead;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Control extends InputAdapter implements InputProcessor {
    // Camera
    OrthographicCamera camera;

    // Keyboard
    public boolean up;
    public boolean down;
    public boolean left;
    public boolean right;

    // Action of Users
    public float angle;

    // Mouse
    public boolean LMB;
    public boolean RMB;
    public boolean processed_click;
    public Vector2 mouseClickPos = new Vector2();
    public Vector2 mapClickPos = new Vector2();
    public Vector2 mousePos = new Vector2();

    public boolean debug = false;

    // Screen
    int screenWidth, screenHeight;

    public Control(int screen_width, int screen_height, OrthographicCamera camera) {
        this.camera = camera;
        this.screenWidth = screen_width;
        this.screenHeight = screen_height;
    }

    public Control(int screen_width, int screen_height) {
//        this.camera = Vector3;
        this.screenWidth = screen_width;
        this.screenHeight = screen_height;
    }

    public Vector2 getMousePos() {
        return new Vector2(Gdx.input.getX(), Gdx.input.getY());
    }

    public boolean isClicked() {
        return Gdx.input.isTouched();
    }

    public void setMouseClickedPos(int screenX, int screenY) {
        // Set mouse pos and flip screen Y
        mouseClickPos.set(screenX, screenHeight - screenY);
        mapClickPos.set(getMapCoords(mouseClickPos));
    }

    public Vector2 getMapCoords(Vector2 mouseCoords) {
        Vector3 v3 = new Vector3(mouseCoords.x, screenHeight - mouseCoords.y, 0);
        this.camera.unproject(v3);
        return new Vector2(v3.x, v3.y);
    }

    @Override
    public boolean keyDown(int keycode) {
        switch(keycode) {
            case Input.Keys.S:
                down = true;
                break;
            case Input.Keys.W:
                up = true;
                break;
            case Input.Keys.D:
                right = true;
                break;
            case Input.Keys.A:
                left = true;
                break;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch(keycode) {
            case Input.Keys.S:
                down = false;
                break;
            case Input.Keys.W:
                up = false;
                break;
            case Input.Keys.D:
                right = false;
                break;
            case Input.Keys.A:
                left = false;
                break;
            case Input.Keys.G:
                debug = !debug;
                break;
        }
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(pointer == 0 && button == 0) {
            LMB = true;
        } else if (pointer == 0 && button == 1) {
            RMB = true;
        }

        setMouseClickedPos(screenX, screenY);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        if (pointer == 0 && button == 0) {
            LMB = false;
            processed_click = false;
            System.out.println("Left Click");
        } else if (pointer == 0 && button == 1) {
            RMB = false;
            System.out.println("Right Click");
        }

        setMouseClickedPos(screenX, screenY);
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        setMouseClickedPos(screenX, screenY);
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        float flippedY = screenHeight - screenY;
        mousePos.set(screenX, flippedY);

        // Setting the angle of mouse
        angle = (float) Math.toDegrees(Math.atan2(screenX - (screenWidth/2), screenY - (screenHeight/2)));
        angle = angle < 0 ? angle += 360: angle;

        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
