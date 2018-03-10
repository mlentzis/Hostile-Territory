package com.hostileterritory.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.hostileterritory.game.HostileTerritory;

/**
 * @author AgbaiIroha on 11/15/16
 */
public class StartScreen implements Screen {
	final com.hostileterritory.game.HostileTerritory game;
	private Viewport viewport;
	private Stage stage;
	private boolean changeToInstruction = false;
	Label startLabel;
	Label instructionLabel;
	Label aboutLabel;
	Label quitLabel;
	private Texture imgShadow;

	public StartScreen(final HostileTerritory hostileTerritory) {
		this.game = hostileTerritory;
		this.viewport = new FitViewport(HostileTerritory.V_WIDTH, HostileTerritory.V_HEIGHT, new OrthographicCamera());
		stage = new Stage(viewport, hostileTerritory.batch);
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(255,255,255,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.draw();
		game.batch.begin();
		game.batch.draw(imgShadow,10,10,300,470);
		game.batch.end();

		//game.scrollingBackground.updateAndRender(delta, game.batch);

	}

	@Override
	public void show() {
		imgShadow = new Texture(Gdx.files.internal("Zombie-Shadow.gif"));
		imgShadow.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("lastninja.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.genMipMaps = true;
		parameter.minFilter = Texture.TextureFilter.Linear.MipMapLinearNearest;
		parameter.magFilter = Texture.TextureFilter.Linear;
		parameter.size = 26;
		final BitmapFont font = generator.generateFont(parameter);

		startLabel = new Label("Start Game", new Label.LabelStyle(font, Color.BLACK));
		startLabel.setPosition(500,300);
		startLabel.setTouchable(Touchable.enabled);
		startLabel.setBounds(500,300,startLabel.getWidth(),startLabel.getHeight());
		startLabel.addListener(new ClickListener() {
			
        	@Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameStage(game));
                dispose();
            }
			 
		});


		instructionLabel = new Label("Instructions", new Label.LabelStyle(font, Color.BLACK));
		instructionLabel.setPosition(500, 250);
		instructionLabel.setTouchable(Touchable.enabled);
		instructionLabel.setBounds(500,250,instructionLabel.getWidth(),instructionLabel.getHeight());
		instructionLabel.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.setScreen(new InstructionScreen(game));
				dispose();
			}
		});

		aboutLabel = new Label("About", new Label.LabelStyle(font, Color.BLACK));
		aboutLabel.setPosition(500,200);
		aboutLabel.setTouchable(Touchable.enabled);
		aboutLabel.setBounds(500,200,aboutLabel.getWidth(),aboutLabel.getHeight());
		aboutLabel.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.setScreen(new AboutScreen(game));
				dispose();
			}
		});
		quitLabel = new Label("Quit", new Label.LabelStyle(font, Color.BLACK));
		quitLabel.setPosition(500, 150);
		quitLabel.setTouchable(Touchable.enabled);
		quitLabel.setBounds(500,150,quitLabel.getWidth(),quitLabel.getHeight());
		quitLabel.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.exit();
			}
		});
		stage.addActor(startLabel);
		stage.addActor(instructionLabel);
		stage.addActor(aboutLabel);
		stage.addActor(quitLabel);

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
		startLabel.setTouchable(Touchable.disabled);
		instructionLabel.setTouchable(Touchable.disabled);
		aboutLabel.setTouchable(Touchable.disabled);
		quitLabel.setTouchable(Touchable.disabled);
	}
}
