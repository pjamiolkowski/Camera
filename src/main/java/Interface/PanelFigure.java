package Interface;

import Calculations.CalculationPointsFigure;
import Figures.Surface;

import javax.swing.*;
import java.awt.*;

public class PanelFigure extends JPanel {
    private CalculationPointsFigure clcFigure;

    public PanelFigure() {
        clcFigure = new CalculationPointsFigure();
    }

    public void paintComponent(Graphics g) {    //metoda PaintComponent odpowiedzialna za rysowanie figur
        int i = 0;
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
        for (Surface surface : clcFigure.getCastingSurface()) {
            if (clcFigure.isVisible()[i]) { //sprawdza czy ściana ma być wyświetlana czy nie
                g2d.setColor(surface.getColorSurface());
                g2d.fillPolygon(surface.getPointX(this.getWidth() / 2.0), surface.getPointY(this.getHeight() / 2.0), surface.getSurfacePoints().length);
                g2d.setColor(surface.getColorEdge());
                g2d.drawPolygon(surface.getPointX(this.getWidth() / 2.0), surface.getPointY(this.getHeight() / 2.0), surface.getSurfacePoints().length);
            }
            i++;
        }
    }

    public CalculationPointsFigure getClcFigure() {
        return clcFigure;
    }
}
