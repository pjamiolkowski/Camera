package Figures;

import javafx.geometry.Point3D;

import java.awt.*;


public class Surface {
    private Point3D[] surfacePoints;
    private Color colorEdge;
    private Color colorSurface;


    public Surface(Point3D[] point3D, Color colorEdge, Color colorSurface) {
        surfacePoints = point3D;
        this.colorEdge = colorEdge;
        this.colorSurface = colorSurface;
    }

    public Point3D[] getSurfacePoints() {   //metoda pobierająca wszystkie charakterystyczne punkty ściany
        return surfacePoints;
    }

    public Color getColorEdge() {
        return colorEdge;
    }

    public Color getColorSurface() {
        return colorSurface;
    }

    public int[] getPointX(double width) {  //metoda zwracająca wspórzędne X punktów ściany z uwzględnieniem wymiarów okna
        int[] pointX;
        pointX = new int[surfacePoints.length];
        for (int i = 0; i < surfacePoints.length; i++) {
            pointX[i] = (int) Math.round(surfacePoints[i].getX() + width);
        }
        return pointX;
    }

    public int[] getPointX() {  //metoda zwracająca wspórzędne X punktów ściany
        int[] pointX;
        pointX = new int[surfacePoints.length];
        for (int i = 0; i < surfacePoints.length; i++) {
            pointX[i] = (int) Math.round(surfacePoints[i].getX());
        }
        return pointX;
    }

    public int[] getPointY(double height) { //metoda zwracająca wspórzędne Y punktów ściany z uwzględnieniem wymiarów okna
        int[] pointY;
        pointY = new int[surfacePoints.length];
        for (int i = 0; i < surfacePoints.length; i++) {
            pointY[i] = (int) Math.round(surfacePoints[i].getY() + height);
        }
        return pointY;
    }

    public int[] getPointY() {  //metoda zwracająca wspórzędne Y ściany płaszczyzny
        int[] pointY;
        pointY = new int[surfacePoints.length];
        for (int i = 0; i < surfacePoints.length; i++) {
            pointY[i] = (int) Math.round(surfacePoints[i].getY());
        }
        return pointY;
    }

    public int[] getPointZ() {  //metoda zwracająca wspórzędne Z punktów ściany
        int[] pointZ;
        pointZ = new int[surfacePoints.length];
        for (int i = 0; i < surfacePoints.length; i++) {
            pointZ[i] = (int) Math.round(surfacePoints[i].getZ());
        }
        return pointZ;
    }
}
