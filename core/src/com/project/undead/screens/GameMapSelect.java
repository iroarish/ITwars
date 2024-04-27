package com.project.undead.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.TimeUtils;
import com.project.undead.*;

public class GameMapSelect implements Screen {
    GameClass game;
    Control control;

    // Button Position
    ScreenSize screen = new ScreenSize();
    float ptcButtonX;
    float ptcButtonY;
    float macButtonX;
    float macButtonY;
    float backButtonX;
    float backButtonY;

    // Mouse position
    float mouseX;
    float mouseY;

    // Time
    long startTime = TimeUtils.millis();

    public GameMapSelect(GameClass game) {
        this.game = game;

        control = new Control(screen.SCREENWIDTH, screen.SCREENHEIGHT);

        Media.loadMedia();

        ptcButtonX = (screen.SCREENWIDTH - Media.inactivePtcSelect.getWidth()) / 2f;
        ptcButtonY = screen.SCREENHEIGHT / 1.5f;
        macButtonX = (screen.SCREENWIDTH - Media.inactiveMacSelect.getWidth()) / 2f;
        macButtonY = screen.SCREENHEIGHT / 2.5f;
        backButtonX = 0;
        backButtonY = 1;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        float endTime = (float) TimeUtils.timeSinceMillis(startTime) / 1000;
        mouseX = control.getMousePos().x;
        mouseY = control.getMousePos().y;


        Gdx.gl.glClearColor(0.1f, 0.1f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();

        game.batch.draw(Media.mapSelectBg, 0, 0);

        if (mouseX > ptcButtonX && mouseX < (ptcButtonX + Media.inactivePtcSelect.getWidth()) && mouseY > ptcButtonY * 0.3f && mouseY < (ptcButtonY * 0.35) + Media.inactivePtcSelect.getHeight()) {
            game.batch.draw(Media.activePtcSelect, ptcButtonX, ptcButtonY);

            if (control.isClicked() && endTime > 1) {
                TileMap.map = TileMap.ptc;
                this.dispose();
                game.setScreen(new GameScreen(game));
                game.batch.begin();
            }
        } else {
            game.batch.draw(Media.inactivePtcSelect, ptcButtonX, ptcButtonY);
        }

        if (mouseX > ptcButtonX && mouseX < (macButtonX + Media.inactiveMacSelect.getWidth()) && mouseY > macButtonY * 1.1f && mouseY < (macButtonY * 1.2) + Media.inactiveMacSelect.getHeight()) {
            game.batch.draw(Media.activeMacSelect, macButtonX, macButtonY);

            if (control.isClicked() && endTime > 1) {
                TileMap.map = TileMap.mac;
                this.dispose();
                game.setScreen(new GameScreen(game));
                game.batch.begin();
            }
        } else {
            game.batch.draw(Media.inactiveMacSelect, macButtonX, macButtonY);
        }

        if (mouseX > backButtonX && mouseX < (backButtonX + Media.inactiveBackButton.getWidth()) && mouseY > backButtonY * 700f && mouseY < (backButtonY * 768) + Media.inactiveBackButton.getHeight()) {
            game.batch.draw(Media.activeBackButton, backButtonX, backButtonY);

            if (control.isClicked() && endTime > 1) {
                game.setScreen(new GameMainMenu(game));
            }
        } else {
            game.batch.draw(Media.inactiveBackButton, backButtonX, backButtonY);
        }

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
