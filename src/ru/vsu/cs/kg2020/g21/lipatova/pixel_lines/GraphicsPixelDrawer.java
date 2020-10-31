package ru.vsu.cs.kg2020.g21.lipatova.pixel_lines;

import ru.vsu.cs.kg2020.g21.lipatova.PixelDrawer;

import java.awt.*;

public class GraphicsPixelDrawer
        implements PixelDrawer {
    private Graphics g;

    public GraphicsPixelDrawer(Graphics g) {
        this.g = g;
    }

    @Override
    public void drawPixel(int x, int y, Color c) {
        g.setColor(c);
        g.fillRect(x, y, 1, 1);
    }
}
