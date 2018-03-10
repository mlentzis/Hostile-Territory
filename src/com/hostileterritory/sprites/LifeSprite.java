package com.hostileterritory.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;
import com.hostileterritory.game.HostileTerritory;
import com.hostileterritory.screens.GameStage;

/**
 * Created by AgbaiIroha on 12/13/16.
 */
public class LifeSprite extends Sprite {
    private World mainWorld;
    public Body lifeBody;
    private Screen GameStage;
    private FixtureDef lifeFixtureDef;
    private boolean toBeDeleted = false;

    public LifeSprite(World world, Screen GameStage, float x, float y) {
        super(new Texture(Gdx.files.internal("heart.png")));
        this.mainWorld = world;
        this.GameStage = GameStage;
        this.setPosition(x,y);
        this.defineLife();
    }
    public void defineLife() {
        BodyDef lifeDef = new BodyDef();
        lifeDef.position.set(this.getX(), this.getY());
        lifeDef.type = BodyDef.BodyType.StaticBody;

        this.lifeBody = this.mainWorld.createBody(lifeDef);
        this.lifeBody.setUserData(this);

        lifeFixtureDef = new FixtureDef();
        CircleShape lifeShape = new CircleShape();
        lifeShape.setRadius(6 / HostileTerritory.PPM);

        lifeFixtureDef.shape = lifeShape;
        lifeFixtureDef.filter.categoryBits = 0x0032;
        lifeFixtureDef.filter.maskBits =(short) (0x0001 | 0x0008);

        lifeBody.createFixture(lifeFixtureDef);
        this.setSize(32/HostileTerritory.PPM, 32/HostileTerritory.PPM);
        setPosition(lifeBody.getPosition().x - this.getWidth() / 2, lifeBody.getPosition().y - this.getHeight() / 2);

    }

    public void delete() {this.lifeBody.getWorld().destroyBody(lifeBody);}
    public boolean isToBeDeleted() {
        return toBeDeleted;
    }
    public void setToBeDeleted(boolean toBeDeleted) {
        this.toBeDeleted = toBeDeleted;
    }
}
