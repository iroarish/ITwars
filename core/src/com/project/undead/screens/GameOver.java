package com.project.undead.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;
import com.project.undead.*;
import com.project.undead.entities.Player;

public class GameOver implements Screen {
    ScreenSize screen = new ScreenSize();
    GameClass game;
    Control control;
    Player player;

    // Buttons
    float menuButtonX;
    float menuButtonY;

    // Mouse Pos
    float mouseX;
    float mouseY;

    // Time
    long startTime = TimeUtils.millis();

    // Font
    BitmapFont font;
    BitmapFont bigFont;

    public GameOver(GameClass game) {
        this.game = game;
        control = new Control(screen.SCREENWIDTH, screen.SCREENHEIGHT);

        Media.loadMedia();

        menuButtonX = (screen.SCREENWIDTH - Media.inactiveMainMenu.getWidth()) / 2f;
        menuButtonY = screen.SCREENHEIGHT / 5.5f;

        // Font Thingies
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Font/GravityBold8.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 50; // font size
        font = generator.generateFont(parameter);
        parameter.size = 80;
        bigFont = generator.generateFont(parameter);
        generator.dispose();
    }
    @Override
    public void show() {


    }

    @Override
    public void render(float v) {
        // Announcement of Scores
        String finalScore = "You saved " + Player.score + " computers!";

        // Time Chuchu
        float endTime = (float) TimeUtils.timeSinceMillis(startTime) / 1000;

        // Mousey Mousey
        mouseX = control.getMousePos().x;
        mouseY = control.getMousePos().y;

        Gdx.gl.glClearColor(0.1f, 0.1f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();

        game.batch.draw(Media.gameOverBg, 0, 0);

        bigFont.draw(game.batch, "Game Over", screen.SCREENWIDTH / 3.5f, screen.SCREENHEIGHT / 1.2f);
        font.draw(game.batch, finalScore, screen.SCREENWIDTH / 5f, screen.SCREENHEIGHT / 1.7f);

        if (Player.score > GameMainMenu.bestScore) {
            GameMainMenu.bestScore = Player.score;
        }

        if (mouseX > menuButtonX && mouseX < (menuButtonX + Media.inactiveMainMenu.getWidth()) && mouseY > menuButtonY * 4f && mouseY < (menuButtonY * 4.2) + Media.inactiveMainMenu.getHeight()) {
            game.batch.draw(Media.activeMainMenu, menuButtonX, menuButtonY);

            if (control.isClicked() && endTime > 1) {
                this.dispose();
                game.setScreen(new GameMainMenu(game));
            }
        } else {
            game.batch.draw(Media.inactiveMainMenu, menuButtonX, menuButtonY);
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
