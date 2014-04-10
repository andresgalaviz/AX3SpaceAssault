package com.me.spaceassault.world;

import com.me.spaceassault.resources.Hero;
import com.me.spaceassault.resources.Tile;
import com.me.spaceassault.world.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

public class WorldRenderer2 {

	private World world;
	private OrthographicCamera cam;

	private static final float CAMERA_WIDTH = 10f;
	private static final float CAMERA_HEIGHT = 7f;
    private static final float RUNNING_FRAME_DURATION = 0.06f;

    /** for debug rendering **/
	ShapeRenderer debugRenderer = new ShapeRenderer();
	
	/** Textures **/
    private TextureRegion heroIdleLeft;
    private TextureRegion heroIdleRight;
    private TextureRegion tileTexture;
    private TextureRegion heroFrame;

    /** Animations **/
    private Animation walkLeftAnimation;
    private Animation walkRightAnimation;
	

    private SpriteBatch spriteBatch;
	private boolean debug = false;
	private int width;
	private int height;
	private float ppuX;
	private float ppuY;
	
	public void setSize (int w, int h) {
		this.width = w;
		this.height = h;
		ppuX = (float)width/CAMERA_WIDTH;
		ppuY = (float)height/CAMERA_HEIGHT;
	}
	

	public WorldRenderer2(World world, boolean debug) {
		this.world = world;
		this.cam = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
		this.cam.position.set(CAMERA_WIDTH / 2f, CAMERA_HEIGHT / 2f, 0);
		this.cam.update();
		this.debug = debug;
		spriteBatch = new SpriteBatch();
		loadTextures();
		
	}
	
	public void loadTextures(){
		TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("images/textures/textures.pack"));
		heroIdleLeft = atlas.findRegion("bob-01");
		heroIdleRight = new TextureRegion(heroIdleLeft);
		heroIdleRight.flip(true, false);
		tileTexture = atlas.findRegion("block");
		TextureRegion[] walkLeftFrames = new TextureRegion[5];
		for (int i = 0; i < 5; i++) {
			walkLeftFrames[i] = atlas.findRegion("bob-0" + (i + 2));
		}
		walkLeftAnimation = new Animation(RUNNING_FRAME_DURATION, walkLeftFrames);

		TextureRegion[] walkRightFrames = new TextureRegion[5];

		for (int i = 0; i < 5; i++) {
			walkRightFrames[i] = new TextureRegion(walkLeftFrames[i]);
			walkRightFrames[i].flip(true, false);
		}
		walkRightAnimation = new Animation(RUNNING_FRAME_DURATION, walkRightFrames);
	}

	public void render() {
		// render blocks
		spriteBatch.begin();
			drawTiles();
			drawHero();
		spriteBatch.end();
			
		if(debug) {
			drawDebug();
		}
			
	}
	public void drawDebug() {
		debugRenderer.setProjectionMatrix(cam.combined);
		debugRenderer.begin(ShapeType.Line);
		for (Tile tile : world.getTiles()) {
			Rectangle rect = tile.getBounds();
			float x1 = tile.getPosition().x + rect.x;
			float y1 = tile.getPosition().y + rect.y;
			debugRenderer.setColor(new Color(1, 0, 0, 1));
			debugRenderer.rect(x1, y1, rect.width, rect.height);
		}
		// render Hero
		Hero Pyro = world.getHero();
		Rectangle rect = Pyro.getBounds();
		float x1 = Pyro.getPosition().x + rect.x;
		float y1 = Pyro.getPosition().y + rect.y;
		debugRenderer.setColor(new Color(0, 1, 0, 1));
		debugRenderer.rect(x1, y1, rect.width, rect.height);
		debugRenderer.end();
	}
	private void drawTiles() {
		for(Tile tile : world.getTiles()) {
			spriteBatch.draw(tileTexture, tile.getPosition().x*ppuX, tile.getPosition().y*ppuY, tile.SIZE*ppuX, tile.SIZE*ppuY);
		}
	}
	
	private void drawHero() {
		Hero hero = world.getHero();
		heroFrame = hero.isFacingLeft() ? heroIdleLeft : heroIdleRight;
		if(hero.getState().equals(Hero.State.WALK)) {
			heroFrame = hero.isFacingLeft() ? walkLeftAnimation.getKeyFrame(hero.getStateTime(), true) : walkRightAnimation.getKeyFrame(hero.getStateTime(), true);
		}
		spriteBatch.draw(heroFrame, hero.getPosition().x * ppuX, hero.getPosition().y * ppuY, hero.SIZE * ppuX, hero.SIZE * ppuY);
	}
}
