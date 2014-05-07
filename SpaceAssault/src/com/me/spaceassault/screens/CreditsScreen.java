package com.me.spaceassault.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
public class CreditsScreen implements Screen {

	private Stage stage;
	private TextureAtlas atlas;
	private Skin skin;
	private Table table;
	private TextButton buttonBack;
	private BitmapFont white, black, text, text2;
	private Label heading, name1,name2,name3,name4;
	
    /**
     * Metodo <I>render</I> de la clase <code>CreditsScreen</code>, En este
     * metodo se pintan los objetos y se actualizan variables que involucran
     * tiempo.
     * @paramdelta tipo de dato <code>float</code> es el valor utilizado para
     * manejar la diferencia de tiempo
     */
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act(delta);
		
		//Table.drawDebug(stage);
		
		stage.draw();
	}
	
    /**
     * Metodo <I>resize</I> de la clase <code>CreditsScreen</code>, En este
     * metodo se redefine el tama�o de la pantalla
     *
     * @paramwidth tipo de dato <code>Entero</code> es el valor utilizado para
     * manejar el tama�o en x.
     * @paramheight tipo de dato <code>Entero</code> es el valor utilizado para
     * manejar el tama�o en y.
     */
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}
	
    /**
     * Metodo <I>show</I> de la clase <code>CreditsScreen</code>, En este
     * metodo se definen los actores de la clase
     *
     */
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
		stage = new Stage();
		
		Gdx.input.setInputProcessor(stage); 
		
		atlas = new TextureAtlas("data/button.pack");
		skin = new Skin(atlas);
		
		table = new Table(skin);
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		//creating fonts
		white = new BitmapFont(Gdx.files.internal("data/whiteHigher.fnt"),false);
		black = new BitmapFont(Gdx.files.internal("data/blackHigher.fnt"),false);
		text = new BitmapFont(Gdx.files.internal("data/SpaceRanger.fnt"),false);
		text2 = new BitmapFont(Gdx.files.internal("data/WhiteSpaceRanger.fnt"),false);
		
		
		//creating button
		TextButtonStyle textButtonStyle = new TextButtonStyle(); 
		textButtonStyle.up = skin.getDrawable("buttonUp");
		textButtonStyle.down = skin.getDrawable("buttonPressed");
		textButtonStyle.pressedOffsetX = 1;
		textButtonStyle.pressedOffsetY = -1;
		textButtonStyle.font = text;
	
		

		
		buttonBack = new TextButton("Back",textButtonStyle);
		buttonBack.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				((Game) Gdx.app.getApplicationListener()).setScreen(new MenuScreen());
			}
		});
		buttonBack.pad(15);
		
		//creating heading
		LabelStyle headingStyle = new LabelStyle(white, Color.RED);
		
		LabelStyle nameStyle = new LabelStyle(text2,Color.WHITE);
		
		
		heading = new Label("Credits", headingStyle);
		heading.setFontScale(2);
		
		
		name1 = new Label("Andres Galaviz A01044679",nameStyle);
		name2 = new Label("Alberto Astiazaran A01240828",nameStyle);
		name3 = new Label("Andres Gutierrez A01191581",nameStyle);
		name4 = new Label("Sergio Cordero A01191167",nameStyle);
		
		//table manipulation
		table.add(heading);
		table.getCell(heading).spaceBottom(60);
		table.row();
		table.add(name1);
		table.getCell(name1).spaceBottom(20);
		table.row();
		table.add(name2);
		table.getCell(name2).spaceBottom(20);
		table.row();
		table.add(name3);
		table.getCell(name3).spaceBottom(20);
		table.row();
		table.add(name4);
		table.getCell(name4).spaceBottom(20);
		table.row();
		table.add(buttonBack);
		table.debug(); //remove later
		stage.addActor(table);
		

	}
	
    /**
     * Metodo <I>hide</I> de la clase <code>CreditsScreen</code>, En este
     * metodo se minimiza la pantalla
     */
	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}
	
	/**
     * Metodo <I>pause</I> de la clase <code>CreditsScreen</code>, En este
     * metodo se pausa la pantalla
     */
	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}
	
	/**
     * Metodo <I>resume</I> de la clase <code>GameOverScreen</code>, En este
     * metodo se resume la pantalla.
     */
	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
	
	/**
     * Metodo <I>dispose</I> de la clase <code>CreditsScreen</code>, En este
     * metodo se hace dispose para limpiar los contenedores utilizados en la clase.
     */
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		stage.dispose();
		atlas.dispose();
		skin.dispose();
		white.dispose();
		black.dispose();
		
	}

}
