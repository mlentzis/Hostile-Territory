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
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.hostileterritory.game.HostileTerritory;

/**
 * @author Agbai Iroha on 11/15/16
 */
public class GameOverScreen implements Screen {
	private Viewport viewport;
    private Stage stage;
    private HostileTerritory game;
    private Texture imgZombie;
    Label gameOverLabel;
    public GameOverScreen(HostileTerritory game) {
        this.game = game;
        this.viewport = new FitViewport(HostileTerritory.V_WIDTH, HostileTerritory.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, game.batch);
        imgZombie = new Texture(Gdx.files.internal("Zombie_Point.png"));

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("lastninja.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.genMipMaps = true;
        parameter.minFilter = Texture.TextureFilter.Linear.MipMapLinearNearest;
        parameter.magFilter = Texture.TextureFilter.Linear;
        parameter.size = 50;
        BitmapFont font = generator.generateFont(parameter);

        gameOverLabel = new Label("GAME OVER", new Label.LabelStyle(font, Color.BLACK));
        gameOverLabel.setPosition(400-(gameOverLabel.getWidth()/2),240-(gameOverLabel.getHeight()/2));

        stage.addActor(gameOverLabel);


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
        game.batch.begin();
        game.batch.draw(imgZombie,10,60);
        game.batch.end();
        
        //game.updateAndRender(delta, game.batch);
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
        this.stage.dispose();
    }
}
