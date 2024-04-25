package com.project.undead.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.TimeUtils;
import com.project.undead.Control;
import com.project.undead.GameClass;
import com.project.undead.Media;
import com.project.undead.ScreenSize;
import com.project.undead.entities.Player;

public class GameHowTo implements Screen {
    ScreenSize screen = new ScreenSize();
    GameClass game;
    Control control;

    // Buttons
    float backButtonX;
    float backButtonY;

    // Mouse Pos
    float mouseX;
    float mouseY;

    // Font
    BitmapFont font;
    BitmapFont bigFont;

    // Time
    long startTime = TimeUtils.millis();

    public GameHowTo(GameClass game) {
        this.game = game;
        control = new Control(screen.SCREENWIDTH, screen.SCREENHEIGHT);

        Media.loadMedia();

        backButtonX = 0;
        backButtonY = 1;

        // Font Thingies
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Font/GravityBold8.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 22; // font size
        font = generator.generateFont(parameter);
        parameter.size = 40;
        bigFont = generator.generateFont(parameter);
        generator.dispose();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        float endTime = (float) TimeUtils.timeSinceMillis(startTime) / 1000;
        String howTo = "As a student of UPANG CITE you are tasked to protect the \n\n" +
                "computers.\n\n" +
                "Defeat Viruses, Trojan, Worms, and a Dummy.\n\nEquipped with Laptop and a USB.\n\n" +
                "While defeating them there's a chance of them dropping a folder.\n\n" +
                "You can use it to shoot with your gun.";

        String keyBoard = "Q - Switches your Weapon.\n\n" +
                "W, A, S, D - Control Player Movement.\n\n" +
                "Right Mouse CliCk - Shoot or Swing your Weapon.";

        mouseX = control.getMousePos().x;
        mouseY = control.getMousePos().y;

        Gdx.gl.glClearColor(0.1f, 0.1f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();

        game.batch.draw(Media.howToBg, 0, 0);

        bigFont.draw(game.batch, "How to Play", 530, 760);
        font.draw(game.batch, howTo, 17, 680);
        bigFont.draw(game.batch, "Controls", 530, 400);
        font.draw(game.batch, keyBoard, 17, 320);

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
