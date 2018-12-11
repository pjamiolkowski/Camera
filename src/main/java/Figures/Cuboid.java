package Figures;

import javafx.geometry.Point3D;
import java.awt.*;

public class Cuboid extends Figure {    //klasa figury prostopadłościan
    private double height;
    private double width;
    private double length;
    private Color colorEdge;
    private Color colorSurface;

    public Cuboid(double x, double y, double z, double height, double width, double length, Color colorEdge, Color colorSurface) {
        this.height = height;
        this.width = width;
        this.length = length;
        this.colorEdge = colorEdge;
        this.colorSurface = colorSurface;
        initPoint3D(x, y, z);
        initSurface();
    }

    private void initPoint3D(double x, double y, double z) {    //metoda ustalająca charakterystyczne punkty prostopadłościanu
        setPoint3D(new Point3D[]{
                new Point3D(x, y, z),   //punkt 0,0,0 czyli 0
                new Point3D(x + length, y, z),      //punkt 1,0,0 czyli 1
                new Point3D(x, y + height, z),      //punkt 0,1,0 czyli 2
                new Point3D(x, y, z + width),       //punkt 0,0,1 czyli 3
                new Point3D(x + length, y + height, z),     //punkt 1,1,0 czyli 4
                new Point3D(x + length, y, z + width),      //punkt 1,0,1 czyli 5
                new Point3D(x + length, y + height, z + width),     //punkt 1,1,1 czyli 6
                new Point3D(x, y + height, z + width)       //punkt 0,1,1 czyli 7
        });
    }

    private void initSurface() {    //metoda ustalająca ściany prostopadłościanu
        setSurface(new Surface[]{
                new Surface(new Point3D[]{getPoint3D()[0], getPoint3D()[2], getPoint3D()[4], getPoint3D()[1]}, colorEdge, colorSurface),
                new Surface(new Point3D[]{getPoint3D()[0], getPoint3D()[1], getPoint3D()[5], getPoint3D()[3]}, colorEdge, colorSurface),
                new Surface(new Point3D[]{getPoint3D()[2], getPoint3D()[4], getPoint3D()[6], getPoint3D()[7]}, colorEdge, colorSurface),
                new Surface(new Point3D[]{getPoint3D()[1], getPoint3D()[5], getPoint3D()[6], getPoint3D()[4]}, colorEdge, colorSurface),
                new Surface(new Point3D[]{getPoint3D()[3], getPoint3D()[7], getPoint3D()[6], getPoint3D()[5]}, colorEdge, colorSurface),
                new Surface(new Point3D[]{getPoint3D()[0], getPoint3D()[3], getPoint3D()[7], getPoint3D()[2]}, colorEdge, colorSurface),
        });
    }
}