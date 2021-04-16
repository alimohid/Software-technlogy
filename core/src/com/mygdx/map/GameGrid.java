package com.mygdx.map;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.NumberPair;

public class GameGrid {
    public final int CELL_SIZE = 60;
    public final int WINDOW_WIDTH = 1200;
    public final int WINDOW_HEIGHT = 660;

    public void render(ShapeRenderer render, boolean isGridDisplayOn) {
        if (isGridDisplayOn) {
            render.setColor(0, 0, 0, 1);

            int rows = WINDOW_HEIGHT / CELL_SIZE;
            int cols = WINDOW_WIDTH / CELL_SIZE;

            // draw the rows
            int rowHt = WINDOW_HEIGHT / (rows);
            for (int i = 0; i < rows; ++i) {
                if (i * rowHt > 120)
                    render.line(0, i * rowHt, WINDOW_WIDTH, i * rowHt);
            }

            // draw the columns
            int rowWid = WINDOW_WIDTH / (cols);
            for (int i = 0; i < cols; ++i)
                render.line(i * rowWid, 120, i * rowWid, WINDOW_HEIGHT);
        }
    }

    public NumberPair getCellCoordinates(int x, int y) {
        //round x and y coordinates to multiples of CELL_SIZE (down)
        return new NumberPair(CELL_SIZE * Math.round((int) (x / CELL_SIZE)),
                CELL_SIZE * Math.round((int) (y / CELL_SIZE)));
    }
}
