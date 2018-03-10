package com.hostileterritory.Box2D;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.hostileterritory.actors.Life;
import com.hostileterritory.game.HostileTerritory;

public class Hud implements Disposable{

	public Stage stage;
	private Viewport viewport;
	private Integer worldTimer;
	private float timeCount;
	private Integer score;
	

	static Label scoreLabel;
	public Label worldLabel;
	public Label heroLabel;
	
	public Stage getStage() {
		return stage;
	}
	
	public Array<Life> lives;
	
	public Hud(SpriteBatch sb){
		//worldTimer = 300;
		timeCount = 0;
		score = 0;
		
		viewport = new FitViewport(HostileTerritory.V_WIDTH, HostileTerritory.V_HEIGHT, new OrthographicCamera());
		stage = new Stage(viewport, sb);
		
		Table table = new Table();
		table.top();
		table.setFillParent(true);
		
		
		Label scoreLabel = new Label(String.format("%06d", score ), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		Label worldLabel = new Label("Hostile Territory", new Label.LabelStyle(new BitmapFont(), Color.WHITE));;
		Label heroLabel = new Label("ZRunner", new Label.LabelStyle(new BitmapFont(), Color.WHITE));;;
		
		table.add(heroLabel).expandX().padTop(10);
		table.add(worldLabel).expandX().padTop(10);
		table.row();
		table.add(scoreLabel).expandX();
		
		stage.addActor(table);
	}
	public void dispose(){
		stage.dispose();
	}
	
}
