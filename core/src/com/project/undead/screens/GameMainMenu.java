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
    Texture activePlayButton;
    Texture activeQuitButton;
    Texture title;

    ScreenSize screen = new ScreenSize();
    Control control;

    // Variable for Positions
    float playButtonX;
    float playButtonY;
    float mouseX;
    float mouseY;


    public GameMainMenu(GameClass game) {
        this.game = game;

        playButton = new Texture("UI/PlayButton.png");
        quitButton = new Texture("UI/QuitButton.png");
        activePlayButton = new Texture("UI/ActivePlayButton.png");
        activeQuitButton = new Texture("UI/ActiveQuitButton.png");
        title = new Texture("UI/Title.png");
        control = new Control(screen.SCREENWIDTH, screen.SCREENHEIGHT);

        // Variable for Positions
        playButtonX = (screen.SCREENWIDTH - activePlayButton.getWidth()) / 2f;
        playButtonY = screen.SCREENHEIGHT / 2.8f;
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float v) {
        // Mouse Position
        mouseX = control.getMousePos().x;
        mouseY = control.getMousePos().y;

        Gdx.gl.glClearColor(0.1f, 0.1f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();

        game.batch.draw(title, (screen.SCREENWIDTH - title.getWidth()) / 2f, screen.SCREENHEIGHT / 1.5f);



        game.batch.draw(quitButton, (screen.SCREENWIDTH - quitButton.getWidth()) / 2f, screen.SCREENHEIGHT / 3.9f);

        if (mouseX > playButtonX && mouseX < (playButtonX + activePlayButton.getWidth()) && mouseY > playButtonY * 1.6f && mouseY < (playButtonY * 1.6) + playButton.getHeight()) {
            game.batch.draw(activePlayButton, playButtonX, playButtonY);

            if (control.isClicked()) {
                this.dispose();
                game.setScreen(new GameScreen(game));
                game.batch.begin();
            }
        } else {
            game.batch.draw(playButton, playButtonX, playButtonY);
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
//        game.batch.dispose();
    }
}
