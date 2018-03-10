
package com.hostileterritory.game;

import com.badlogic.gdx.Game;
import com.hostileterritory.game.HostileTerritory;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.hostileterritory.screens.GameStage;
import com.hostileterritory.screens.ScrollingBackground;
import com.hostileterritory.screens.StartScreen;

/**
 * @author created by Agbai Iroha on 11/22/16
 */

public class HostileTerritory extends Game{


	public SpriteBatch batch;
	//public ScrollingBackground scrollingBackground;
	//public BitmapFont font;

	public static final int V_WIDTH = 800;
	public static final int V_HEIGHT = 480;
	public static final float PPM = 32;
	private HostileTerritory game;


	public static AssetManager assetManager;

	public void create () {

		//setScreen( new GameStage(game));
		batch = new SpriteBatch();
		//assetManager = new AssetManager();
		//assetManager.load("audio/music/Call-of-Duty-Black-Ops-2-Zombie - Theme-Song (Mp3FB.com).mp3", Music.class);
		//assetManager.load("audio/sounds/pirateJump.wav", Sound.class);
		//assetManager.finishLoading();
		//this.scrollingBackground = new ScrollingBackground();
		this.setScreen(new StartScreen(this));
		


	}

	public void render(){

		super.render();
		
		//assetManager.dispose();
		//batch.dispose();

	}
	@Override
	public void resize(int width, int height) {
	} {
		//this.scrollingBackground.resize(V_WIDTH, V_HEIGHT);
		super.resize(V_WIDTH, V_HEIGHT);
	}


}
