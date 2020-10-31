package ru.vsu.cs.kg2020.g21.lipatova.pixel_lines;

import ru.vsu.cs.kg2020.g21.lipatova.LineDrawer;
import ru.vsu.cs.kg2020.g21.lipatova.PixelDrawer;

import java.awt.*;


public class WuLineDrawer implements LineDrawer {

    private PixelDrawer pd;

    public WuLineDrawer(PixelDrawer pd) {
        this.pd = pd;
    }


    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {
        int x, y, dx, dy;
        boolean tmp = false;

        if (Math.abs(y2 - y1) < Math.abs(x2 - x1)) {
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
            drawWuPixel(x, y, err, dx, tmp);
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

    private void drawWuPixel(int x, int y, int err, int dx, boolean tmp) {
        int strength;
        if (dx != 0) {
            strength = (255 * err) / (2 * dx);
        } else {
            strength = 255;
        }

        Color c1 = new Color(0, 0, 0, 255 - Math.abs(strength));
        Color c2 = new Color(0, 0, 0, Math.abs(strength));

        if (!tmp) {
            pd.drawPixel(x, y, c1);
            if (dx != 0) {
                if (strength > 0)
                    pd.drawPixel(x, y + 1, c2);
                else
                    pd.drawPixel(x, y - 1, c2);
            }
        } else {
            pd.drawPixel(y, x, c1);
            if (dx != 0) {
                if (strength > 0)
                    pd.drawPixel(y + 1, x, c2);
                else
                    pd.drawPixel(y - 1, x, c2);
            }
        }
    }
}








