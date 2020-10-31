package ru.vsu.cs.kg2020.g21.lipatova.pixel_lines;
import ru.vsu.cs.kg2020.g21.lipatova.LineDrawer;
import ru.vsu.cs.kg2020.g21.lipatova.PixelDrawer;

import java.awt.*;

public class BresenhamLineDrawer implements LineDrawer {

    private PixelDrawer pd;

    private boolean tmp = false;

    public BresenhamLineDrawer(PixelDrawer pd) {
        this.pd = pd;
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {
        int x, y, dx, dy;
        boolean tmp = false;
        if ( Math.abs(x2 - x1) > Math.abs(y2 - y1)) {
            if (x2 - x1 > 0) {
                x = x1;
                y = y1;
                dx = x2 - x1;
                dy = y2 - y1;
            } else {
                x = x2;
                y = y2;
                dx = x1 - x2;
                dy = y1 - y2;
            }
        } else {
            tmp = true;
            if (y2 - y1 > 0) {
                x = y1;
                y = x1;
                dx = y2 - y1;
                dy = x2 - x1;
            } else {
                x = y2;
                y = x2;
                dx = y1 - y2;
                dy = x1 - x2;
            }
        }
        int err = 0;
        for (int i = 0; i <= dx; i++) {
            if (tmp)
                pd.drawPixel(y, x,  Color.black
                );
            else
                pd.drawPixel(x, y,  Color.black);
            err += 2 * dy;
            if (err > dx) {
                err -= 2 * dx;
                y++;
            } else if (err < -dx) {
                err += 2 * dx;
                y--;
            }
            x++;
        }
    }
}
