package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.map.GameGrid;
import com.mygdx.map.MapGrid;

import java.util.logging.Logger;

import static com.mygdx.map.MapGrid.GAME_GRID_HEIGHT;

public class MyGdxGame extends ApplicationAdapter {
    Logger log = Logger.getLogger("log");
    public OrthographicCamera camera;
    public SpriteBatch batch;
    public Sprite sprite;
    public ShapeRenderer render;
    public MapGrid grid;
    public GameGrid gameGrid;
    public Stage stage;
    boolean isGamePaused;
    boolean isGateOpen;
    public BitmapFont font;
    public int money;
    public int mood;
    private boolean isGameGridOn;
    public String myMoneyName;
    public String myMoodName;

    @Override
    public void create() {
        font = new BitmapFont();
        camera = new OrthographicCamera();
        camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch = new SpriteBatch();
        stage = new Stage(new ScreenViewport());
        render = new ShapeRenderer();
        render.setAutoShapeType(true);
        grid = new MapGrid();
        gameGrid = new GameGrid();
        Gdx.input.setInputProcessor(stage);
        isGamePaused = false;
        isGateOpen = false;
        isGameGridOn = false;

        money = 5000;
        mood = 70;
        myMoneyName = "Money : 5000";
        myMoodName = "Mood : 70";

        final ImageButton pauseButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(Textures.pauseButton)));
        pauseButton.setPosition(500, 12);
        stage.addActor(pauseButton);
        pauseButton.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (isGamePaused) {
                    pauseButton.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(Textures.pauseButton));
                } else {
                    pauseButton.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(Textures.resumeButton));
                }
                isGamePaused = !isGamePaused;
                return false;
            }
        });
        final ImageButton gate = new ImageButton(new TextureRegionDrawable(new TextureRegion(Textures.gateClosed)));
        gate.setPosition(2, 360);
        //gate.setSize(60, 150);
        stage.addActor(gate);

        final ImageButton openButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(Textures.openButton)));
        openButton.setPosition(660, 12);
        stage.addActor(openButton);
        openButton.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (isGateOpen) {
                    openButton.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(Textures.openButton));
                    gate.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(Textures.gateClosed));
                } else {
                    openButton.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(Textures.closeButton));
                    gate.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(Textures.gateOpen));
                }
                isGateOpen = !isGateOpen;
                return false;
            }
        });
        //final ImageButton tileButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(Textures.tileIcon)));
        //tileButton.setPosition(80, 42);
        //tileButton.setSize(150, 150);
        //stage.addActor(tileButton);

        //tileButton.addListener(new DragListener() {
        //    public void drag(InputEvent event, float x, float y, int pointer) {
        //        tileButton.moveBy(x - tileButton.getWidth() / 2, y - tileButton.getHeight() / 2);
        //    }

        //    public void dragStop(InputEvent event, float x, float y, int pointer) {
        //        ImageButton tileButtonCopy = new ImageButton(new TextureRegionDrawable(new TextureRegion(Textures.tileIcon)));
        //        tileButtonCopy.setPosition(tileButton.getX(), tileButton.getY());
        //        tileButtonCopy.setSize(150, 150);
        //        tileButtonCopy.setName("tile");
        //        stage.addActor(tileButtonCopy);
        //        tileButton.setPosition(80, 42);

        //        //for(Actor actor : stage.getActors()) {
        //        //	log.info(actor.getName()+"\n");
        //        //}
        //    }
        //});

        createDraggableGridButton(Textures.tileIcon, new NumberPair(6, 65), new NumberPair(50, 50), "tile", 10);
        createDraggableGridButton(Textures.bushIcon, new NumberPair(4, 8), new NumberPair(50, 50), "bush", 10);
        createDraggableGridButton(Textures.janitorIcon, new NumberPair(188, 5), new NumberPair(50, 50), "janitor", 10);
        createDraggableGridButton(Textures.cleanerIcon, new NumberPair(127, 63), new NumberPair(50, 50), "cleaner", 10);
        createDraggableGridButton(Textures.foodCartIcon, new NumberPair(127, 5), new NumberPair(50, 50), "foodCart", 10);
        createDraggableGridButton(Textures.grassIcon, new NumberPair(185, 65), new NumberPair(50, 50), "grass", 10);
        createDraggableGridButton(Textures.houseIcon, new NumberPair(245, 65), new NumberPair(50, 50), "house", 10);
        createDraggableGridButton(Textures.trashcanIcon, new NumberPair(65, 5), new NumberPair(50, 50), "trash", 10);
        createDraggableGridButton(Textures.treeIcon, new NumberPair(65, 65), new NumberPair(50, 50), "tree", 10);
    }

    private void createDraggableGridButton(final Texture buttonTexture, final NumberPair buttonPosition, final NumberPair buttonSize, final String name, final int zoomFactor) {
        final ImageButton gridButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(buttonTexture)));
        gridButton.setPosition(buttonPosition.getFirstNumber(), buttonPosition.getSecondNumber());
        gridButton.setSize(buttonSize.getFirstNumber(), buttonSize.getSecondNumber());
        stage.addActor(gridButton);

        gridButton.addListener(new DragListener() {
            public void drag(InputEvent event, float x, float y, int pointer) {
                gridButton.moveBy(x - gridButton.getWidth() / 2, y - gridButton.getHeight() / 2);
                isGameGridOn = true;
            }

            public void dragStop(InputEvent event, float x, float y, int pointer) {
                if (gridButton.getY() >= GAME_GRID_HEIGHT) {
                    ImageButton gridButtonCopy = new ImageButton(new TextureRegionDrawable(new TextureRegion(buttonTexture)));
                    NumberPair position = gameGrid.getCellCoordinates((int) gridButton.getX(), (int) gridButton.getY());
                    gridButtonCopy.setPosition(position.getFirstNumber(), position.getSecondNumber());
                    gridButtonCopy.setSize(buttonSize.getFirstNumber() + zoomFactor, buttonSize.getSecondNumber() + zoomFactor);
                    gridButtonCopy.setName(name);
                    stage.addActor(gridButtonCopy);
                    //for(Actor actor : stage.getActors()) {
                    //	log.info(actor.getName()+"\n");
                    //}
                }
                resetButton(gridButton, buttonPosition, buttonSize);
                isGameGridOn = false;
            }
        });
    }

    private void resetButton(ImageButton button, NumberPair buttonPosition, NumberPair buttonSize) {
        button.setPosition(buttonPosition.getFirstNumber(), buttonPosition.getSecondNumber());
        button.setSize(buttonSize.getFirstNumber(), buttonSize.getSecondNumber());
    }

    @Override
    public void render() {
        camera.update();
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(Textures.background, 0, 0, 1200, 660);
        font.getData().setScale(2, 1);
        font.setColor(200.0f, 255.0f, 255.0f, 255.0f);
        font.draw(batch, myMoneyName, 1000, 660);
        font.draw(batch, myMoodName, 1000, 630);
        batch.end();

        stage.act();
        stage.draw();

        render.begin();
        grid.render(render);
        gameGrid.render(render, isGameGridOn);
        render.end();
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
    public void dispose() {
    }
}