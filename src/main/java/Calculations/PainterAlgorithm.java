package Calculations;

import Figures.Surface;
import javafx.geometry.Point3D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class PainterAlgorithm {

    public List<Surface> sortedSurfaces(List<Surface> surfaceList) {       // metoda sortująca ściany
        Surface[] surfaceTable = surfaceList.toArray(new Surface[surfaceList.size()]);
        Surface tempSurface;
        for (int i = 0; i < surfaceTable.length - 1; i++) {
            for (int j = 0; j < surfaceTable.length - 1; j++) {
                if (compareSurfaces(surfaceTable[j], surfaceTable[j + 1])) {
                    tempSurface = surfaceTable[j];
                    surfaceTable[j] = surfaceTable[j + 1];
                    surfaceTable[j + 1] = tempSurface;
                }
            }
        }
        return new ArrayList<Surface>(Arrays.asList(surfaceTable));
    }

    private boolean compareSurfaces(Surface surface1, Surface surface2) {
        if (centerPoint(surface1) < centerPoint(surface2)) {    //wstępne uporządkowanie punktów
            return true;
        }
        if (getMax(surface1.getPointZ()) < getMin(surface2.getPointZ())) {    //uporządkowanie ścian względem współrzędnej Z
            return true;
        }
        if (getMin(surface1.getPointZ()) > getMax(surface2.getPointZ())) {
            return false;
        }
        if (getMax(surface1.getPointX()) < getMin(surface2.getPointX()) || (getMin(surface1.getPointX()) > getMax(surface2.getPointX()))) {    //sprawdzenie czy ściany nie pokrywają sie względem wspołrzędnej X
            return false;
        }
        if (getMax(surface1.getPointY()) < getMin(surface2.getPointY()) || (getMin(surface1.getPointY()) > getMax(surface2.getPointY()))) {    //sprawdzenie czy ściany nie pokrywają sie względem wspołrzędnej Y
            return false;
        }
        if (comparePlanePosition(surface1, surface2) > 0) {     //porównianie położenia punktów ściany względem płaszczyzny
            return false;
        }
        if (comparePlanePosition(surface1, surface2) < 0) {
            return true;
        }
        return false;
    }

    private int getMin(int[] coordinate) {  //metoda wyznaczająca minimalną składową współrzędną punktów ściany
        int min;
        min = coordinate[0];   //dodajemy 1 punkt ściany względem którego będziemy sprawdzać czy inne są mniejsze
        for (int i = 1; i < coordinate.length; i++) {
            if (coordinate[i] < min)
                min = coordinate[i];
        }
        return min;
    }

    private int getMax(int[] coordinate) {  //metoda wyznaczająca maksymalną składową współrzędną punktów ściany
        int max;
        max = coordinate[0];   //dodajemy 1 punkt ściany względem którego będziemy sprawdzać czy inne są większe
        for (int i = 1; i < coordinate.length; i++) {
            if (coordinate[i] > max)
                max = coordinate[i];
        }
        return max;
    }

    private int comparePlanePosition(Surface surface1, Surface surface2) {  //porównanie czy punkty ściany leżą za plaszczyzna
        int visible = 0;
        int temp1 = 0;
        int temp2 = 0;
        double[] planeEquation = planeEquation(surface1);   //równanie płaszczyzny
        Point3D[] point3D = surface2.getSurfacePoints();    //punkty porównywania z płaszczyzną
        for (int i = 0; i < point3D.length; i++) {
            int compareResult = (int) Math.round(planeEquation[0] * point3D[i].getX() + planeEquation[1] * point3D[i].getY() + planeEquation[2] * point3D[i].getZ() + planeEquation[3]);
            if (compareResult > 0) {
                temp1++;
            }
            if (compareResult < 0) {
                temp2++;
            }
        }
        if (temp1 == point3D.length) {
            visible = 1;
        }
        if (temp2 == point3D.length) {
            visible = -1;
        }
        return visible;
    }

    private double[] planeEquation(Surface surface) {        //wyznaczanie równania płaszczyzny dla punktów ściany
        double[] planeFactors = new double[4];
        Point3D[] point3D = surface.getSurfacePoints();

        planeFactors[0] = point3D[0].getY() * point3D[1].getZ() + point3D[0].getZ() * point3D[2].getY() + point3D[1].getY() * point3D[2].getZ()
                - (point3D[2].getY() * point3D[1].getZ() + point3D[1].getY() * point3D[0].getZ() + point3D[2].getZ() * point3D[0].getY());
        planeFactors[1] = -(point3D[0].getX() * point3D[1].getZ() + point3D[0].getZ() * point3D[2].getX() + point3D[1].getX() * point3D[2].getZ()
                - (point3D[2].getX() * point3D[1].getZ() + point3D[1].getX() * point3D[0].getZ() + point3D[2].getZ() * point3D[0].getX()));
        planeFactors[2] = point3D[0].getX() * point3D[1].getY() + point3D[0].getY() * point3D[2].getX() + point3D[1].getX() * point3D[2].getY()
                - (point3D[2].getX() * point3D[1].getY() + point3D[1].getX() * point3D[0].getY() + point3D[2].getY() * point3D[0].getX());
        planeFactors[3] = -(point3D[0].getX() * point3D[1].getY() * point3D[2].getZ() + point3D[0].getY() * point3D[1].getZ() * point3D[2].getX() + point3D[1].getX() * point3D[2].getY() * point3D[0].getZ()
                - (point3D[2].getX() * point3D[1].getY() * point3D[0].getZ() + point3D[1].getX() * point3D[0].getY() * point3D[2].getZ() + point3D[2].getY() * point3D[1].getZ() * point3D[0].getX()));
        return planeFactors;
    }

    private int centerPoint(Surface surface) {   //sortowanie ścian względem środka ciężkośći
        double x = 0;
        double y = 0;
        double z = 0;
        Point3D[] point3DTable = surface.getSurfacePoints();

        for (Point3D point3D : point3DTable) {
            x += point3D.getX();
            y += point3D.getY();
            z += point3D.getZ();
        }
        x /= point3DTable.length;
        y /= point3DTable.length;
        z /= point3DTable.length;
        return (int) Math.round(Math.sqrt(x * x + y * y + z * z));
    }
}

