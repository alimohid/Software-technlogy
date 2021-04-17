package com.mygdx.map;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class MapGrid {

    public final int CELL_SIZE = 60;
    public final int WINDOW_WIDTH = 1200;

    public MapGrid() {

    }

    public void update() {

    }

    public void render(ShapeRenderer render) {
        render.setColor(0, 0, 0, 1);

        //rows top to bottom
        render.line(0, 2 * CELL_SIZE, WINDOW_WIDTH, 2 * CELL_SIZE);
        render.line(0, CELL_SIZE, 7 * CELL_SIZE, CELL_SIZE);
        //columns left to right
        render.line(CELL_SIZE, 0, CELL_SIZE, 2 * CELL_SIZE);
        render.line(2 * CELL_SIZE, 0, 2 * CELL_SIZE, 2 * CELL_SIZE);
        render.line(3 * CELL_SIZE, 0, 3 * CELL_SIZE, 2 * CELL_SIZE);
        render.line(4 * CELL_SIZE, 0, 4 * CELL_SIZE, 2 * CELL_SIZE);
        render.line(5 * CELL_SIZE, 0, 5 * CELL_SIZE, 2 * CELL_SIZE);
        render.line(6 * CELL_SIZE, 0, 6 * CELL_SIZE, 2 * CELL_SIZE);
        render.line(7 * CELL_SIZE, 0, 7 * CELL_SIZE, 2 * CELL_SIZE);
        render.line(14 * CELL_SIZE, 0, 14 * CELL_SIZE, 2 * CELL_SIZE);
    }
}
