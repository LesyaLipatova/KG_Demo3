package ru.vsu.cs.kg2020.g21.lipatova;

import ru.vsu.cs.kg2020.g21.lipatova.pixel_lines.BresenhamLineDrawer;
import ru.vsu.cs.kg2020.g21.lipatova.pixel_lines.DDALineDrawer;
import ru.vsu.cs.kg2020.g21.lipatova.pixel_lines.GraphicsPixelDrawer;
import ru.vsu.cs.kg2020.g21.lipatova.pixel_lines.WuLineDrawer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class DrawPanel extends JPanel implements MouseMotionListener {
    private Point position = new Point(0, 0);

    public DrawPanel() {
        this.addMouseMotionListener(this);
    }

    @Override
    public void paint(Graphics g) {
        BufferedImage bi = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics gr = bi.createGraphics();
        gr.setColor(Color.WHITE);
        gr.fillRect(0, 0, getWidth(), getHeight());
        gr.setColor(Color.BLACK);
        /***********************/
        PixelDrawer pd = new GraphicsPixelDrawer(gr);
        //LineDrawer ld = new DDALineDrawer(pd);
        //LineDrawer ld = new BresenhamLineDrawer(pd);
        LineDrawer ld = new WuLineDrawer(pd);
        drawAll(ld);
        /***********************/
        g.drawImage(bi, 0, 0, null);
        gr.dispose();
    }

    private void drawAll(LineDrawer ld) {
        drawSnowFlake(ld, getWidth() / 2-100, getHeight() / 2-55, 200, 28);
        ld.drawLine(getWidth() / 2, getHeight() / 2, (int) position.getX(), (int) position.getY());
    }

    public static void drawSnowFlake(LineDrawer ld, int x, int y, int r, int n) {
        double da = 2 * Math.PI / n;
        for (int i = 0; i < n; i++) {
            double a = da * i;
            double dx = r * Math.cos(a);
            double dy = r * Math.sin(a);
            ld.drawLine(x, y, x + (int) dx, y + (int) dy);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        position = new Point(e.getX(), e.getY());
        repaint();
    }
}
