package Figures;

import javafx.geometry.Point3D;

public class Figure {   //ogólna klasa figur wyświetlanych na ekranie
    private Point3D[] point3D;
    private Surface[] surface;


    public Point3D[] getPoint3D() {
        return point3D;
    }

    public void setPoint3D(Point3D[] point3D) {
        this.point3D = point3D;
    }

    public Surface[] getSurface() {
        return surface;
    }

    public void setSurface(Surface[] surface) {
        this.surface = surface;
    }
}
