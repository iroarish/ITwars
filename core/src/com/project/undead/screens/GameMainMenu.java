package com.project.undead.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.*;
import com.project.undead.Control;
import com.project.undead.GameClass;
import com.project.undead.Media;
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
    public static int bestScore = 0;
    float quitButtonX;
    float quitButtonY;
    float playButtonX;
    float playButtonY;
    float howToButtonX;
    float howToButtonX2;
    float howToButtonY;
    float mouseX;
    float mouseY;

    // Font
    BitmapFont font;



    public GameMainMenu(GameClass game) {
        this.game = game;

        playButton = new Texture("UI/PlayButton.png");
        quitButton = new Texture("UI/QuitButton.png");
        activePlayButton = new Texture("UI/ActivePlayButton.png");
        activeQuitButton = new Texture("UI/ActiveQuitButton.png");
        title = new Texture("UI/Title.png");
        System.out.println(title.getWidth() + " " + title.getHeight());
        control = new Control(screen.SCREENWIDTH, screen.SCREENHEIGHT);
        Media.loadMedia();

        // Variable for Positions
        playButtonX = (screen.SCREENWIDTH - activePlayButton.getWidth()) / 2f;
        playButtonY = screen.SCREENHEIGHT / 2.8f;
        quitButtonX = (screen.SCREENWIDTH - activePlayButton.getWidth()) / 2f;
        quitButtonY = screen.SCREENHEIGHT / 3.9f;
        howToButtonX = 1200;
        howToButtonX2 = 200;
        howToButtonY = 180;

        // For font
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Font/GravityBold8.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 16; // font size
        font = generator.generateFont(parameter);
        generator.dispose();
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float v) {
        String currentBestScore = "Most Computers Saved: " + bestScore;

        // Mouse Position
        mouseX = control.getMousePos().x;
        mouseY = control.getMousePos().y;

        Gdx.gl.glClearColor(0.1f, 0.1f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();

        game.batch.draw(Media.menuBackground, 0, 0);
        font.draw(game.batch, currentBestScore, 120, 650);

//        game.batch.draw(title, (screen.SCREENWIDTH - title.getWidth()) / 2f, screen.SCREENHEIGHT / 1.5f);

        // Play button stuffs
        if (mouseX > playButtonX && mouseX < (playButtonX + activePlayButton.getWidth()) && mouseY > playButtonY * 1.6f && mouseY < (playButtonY * 1.6) + playButton.getHeight()) {
            game.batch.draw(activePlayButton, playButtonX, playButtonY);

            if (control.isClicked()) {
                this.dispose();
                game.setScreen(new GameMapSelect(game));
            }
        } else {
            game.batch.draw(playButton, playButtonX, playButtonY);
        }

        // Quit Button Stuffs
        if (mouseX > playButtonX && mouseX < (quitButtonX + activeQuitButton.getWidth()) && mouseY > quitButtonY * 2.6f && mouseY < (quitButtonY * 2.6) + quitButton.getHeight()) {
            game.batch.draw(activeQuitButton, quitButtonX, quitButtonY);

            if (control.isClicked()) {
                this.dispose();
                Gdx.app.exit();
            }

        } else {
            game.batch.draw(quitButton, (screen.SCREENWIDTH - quitButton.getWidth()) / 2f, quitButtonY);
        }

        if (mouseX > howToButtonX && mouseX < (howToButtonX + Media.inactiveBackButton.getWidth()) && mouseY > howToButtonY * 2.8f && mouseY < (howToButtonY * 3) + Media.inactiveBackButton.getHeight()) {
            game.batch.draw(Media.activeHowTo, howToButtonX, howToButtonY);

            if (control.isClicked()) {
                game.setScreen(new GameHowTo(game));
            }
        } else {
            game.batch.draw(Media.inactiveHowTo, howToButtonX, howToButtonY);
        }

        if (mouseX > howToButtonX2 && mouseX < (howToButtonX2 + Media.inactiveBackButton.getWidth()) && mouseY > howToButtonY * 2.8f && mouseY < (howToButtonY * 3) + Media.inactiveBackButton.getHeight()) {
            game.batch.draw(Media.activeHowTo, howToButtonX2, howToButtonY);

            if (control.isClicked()) {
                game.mute = !game.mute;
            }
        } else {
            game.batch.draw(Media.inactiveHowTo, howToButtonX2, howToButtonY);
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
