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
 * @author AgbaiIroha 11/15/16
 */
public class AboutScreen implements Screen{
	private Viewport viewport;
    private Stage stage;
    private HostileTerritory game;
    Label objectivesLabel;
    Label whoDevelopedLabel;
    Label objectivesText;
    Label developers;
    
    public AboutScreen(HostileTerritory game2) {
        this.viewport = new FitViewport(HostileTerritory.V_WIDTH, HostileTerritory.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, game2.batch);
        this.game = game2;

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("lastninja.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.genMipMaps = true;
        parameter.minFilter = Texture.TextureFilter.Linear.MipMapLinearNearest;
        parameter.magFilter = Texture.TextureFilter.Linear;
        parameter.size = 15;
        BitmapFont font = generator.generateFont(parameter);
        //objectivesLabel = new Label("Objectives", new Label.LabelStyle(font, Color.BLACK));
        //objectivesLabel.setPosition(80,450);
        whoDevelopedLabel = new Label("Developers", new Label.LabelStyle(font, Color.BLACK));
        whoDevelopedLabel.setPosition(450, 370);
        developers = new Label("Agbai Iroha\nMiranda Richardson\nEli Brown\nMike Lentzis", new Label.LabelStyle(font, Color.BLACK));
        developers.setPosition(450,300);
        objectivesText = new Label("You are a the only human alive.\n\nThey want to\neat your brain\n, dont let them.\n\nYou have to\nrun for your life, and\ndefend your brain\nfrom the zombies.\n\nGood luck!", new Label.LabelStyle(font, Color.BLACK));
        objectivesText.setPosition(70,250);
        //stage.addActor(objectivesLabel);
        stage.addActor(whoDevelopedLabel);
        stage.addActor(objectivesText);
        stage.addActor(developers);
    }
    
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if(Gdx.input.justTouched()) {
            game.setScreen(new StartScreen(game));
            dispose();
        }
        Gdx.gl.glClearColor(255, 255, 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
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

