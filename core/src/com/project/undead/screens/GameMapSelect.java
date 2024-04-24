package com.project.undead.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.project.undead.Control;
import com.project.undead.GameClass;
import com.project.undead.Media;
import com.project.undead.ScreenSize;

public class GameMapSelect implements Screen {
    GameClass game;
    Control control;

    // Button Position
    ScreenSize screen = new ScreenSize();
    float ptcButtonX;
    float ptcButtonY;
    float macButtonX;
    float macButtonY;

    // Mouse position
    float mouseX;
    float mouseY;

    public GameMapSelect(GameClass game) {
        this.game = game;

        control = new Control(screen.SCREENWIDTH, screen.SCREENHEIGHT);

        Media.loadMedia();

        ptcButtonX = (screen.SCREENWIDTH - Media.inactivePtcSelect.getWidth()) / 2f;
        ptcButtonY = screen.SCREENHEIGHT / 1.5f;
        macButtonX = (screen.SCREENWIDTH - Media.inactiveMacSelect.getWidth()) / 2f;
        macButtonY = screen.SCREENHEIGHT / 2.5f;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        mouseX = control.getMousePos().x;
        mouseY = control.getMousePos().y;


        Gdx.gl.glClearColor(0.1f, 0.1f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();

        game.batch.draw(Media.inactivePtcSelect, ptcButtonX, ptcButtonY);

        game.batch.draw(Media.inactiveMacSelect, macButtonX, macButtonY);

        game.batch.end();

    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
