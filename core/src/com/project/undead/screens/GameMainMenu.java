package com.project.undead.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.project.undead.Control;
import com.project.undead.GameClass;
import com.project.undead.ScreenSize;

public class GameMainMenu implements Screen {
    GameClass game;

    Texture playButton;
    Texture quitButton;
    Texture title;

    ScreenSize screen = new ScreenSize();
    Control control;


    public GameMainMenu(GameClass game) {
        this.game = game;

        playButton = new Texture("UI/PlayButton.png");
        quitButton = new Texture("UI/QuitButton.png");
        title = new Texture("UI/Title.png");
    }

    @Override
    public void show() {
        control = new Control(screen.SCREENWIDTH, screen.SCREENHEIGHT);
        Gdx.input.setInputProcessor(control);
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();

        game.batch.draw(title, screen.SCREENWIDTH / 2.6f, screen.SCREENHEIGHT / 1.5f);
        game.batch.draw(playButton, screen.SCREENWIDTH / 2.2f, screen.SCREENHEIGHT / 2.8f);
        game.batch.draw(quitButton, screen.SCREENWIDTH / 2.2f, screen.SCREENHEIGHT / 3.9f);

        game.batch.end();


        System.out.println(control.mapClickPos);
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
        game.dispose();
    }
}
