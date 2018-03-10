package com.hostileterritory.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.hostileterritory.game.HostileTerritory;

/**
 * @author AgbaiIroha on 11/15/16
 */
public class HighScore implements Screen{
	private Viewport viewport;
    private Stage stage;
    private HostileTerritory game;
    private Texture imgZombie;
    Label highScoreLabel;

    public HighScore(HostileTerritory game) {
        this.game = game;
        this.viewport = new FitViewport(HostileTerritory.V_WIDTH,HostileTerritory.V_HEIGHT,new OrthographicCamera());
        stage = new Stage(this.viewport,game.batch);
        imgZombie = new Texture(Gdx.files.internal("HighScore_logo.png"));
        imgZombie.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("lastninja.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.genMipMaps = true;
        parameter.minFilter = Texture.TextureFilter.Linear.MipMapLinearNearest;
        parameter.magFilter = Texture.TextureFilter.Linear;
        parameter.size = 40;
        BitmapFont font = generator.generateFont(parameter);

        highScoreLabel = new Label("YOUR HIGH SCORE!\nCAN YOU BEAT IT?", new Label.LabelStyle(font, Color.BLACK));
        highScoreLabel.setPosition(300,240);

        stage.addActor(highScoreLabel);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(255, 255, 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(Gdx.input.justTouched()) {
            game.setScreen(new StartScreen(game));
            dispose();
        }
        stage.draw();
        game.batch.begin();
        game.batch.draw(imgZombie,0,0,340,340);
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {

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
