package Calculations;

import Figures.Cuboid;
import Figures.Figure;
import Figures.Surface;
import javafx.geometry.Point3D;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CalculationPointsFigure { //klasa w której są wykonywane wszystkie obliczenia odnośnie położenia figur
    private Figure[] figures;
    private List<Surface> castingSurface;
    private List<Surface> surfaceList;
    private Matrix matrix;
    private PainterAlgorithm painterAlgorithm;
    private boolean[] visibleTable;


    public CalculationPointsFigure() {
        figures = new Cuboid[]{ //wywołanie figur i ustalenie ich wymiarów
                new Cuboid(-45, -14, 0, 20, 15, 10, Color.RED, Color.YELLOW),
                new Cuboid(-50, -14, 23, 36, 26, 15, Color.GRAY, Color.BLUE),
                new Cuboid(-45, -14, 53, 30, 20, 10, Color.LIGHT_GRAY, Color.RED),
                new Cuboid(35, -14, 0, 15, 25, 10, Color.GRAY, Color.darkGray),
                new Cuboid(35, -14, 30, 25, 20, 10, Color.darkGray, Color.gray),
                new Cuboid(35, -14, 66, 35, 23, 15, Color.BLUE, Color.DARK_GRAY)
        };
        matrix = new Matrix();
        painterAlgorithm = new PainterAlgorithm();
        surfaceList = new ArrayList<Surface>();
        castingSurface = new ArrayList<Surface>();
        for (Figure figure : figures) {
            surfaceList.addAll(Arrays.asList(figure.getSurface()));
        }
        visibleTable = new boolean[surfaceList.size()];
    }

    public void setPosition(double tx, double ty, double tz, double angleX, double angleY, double angleZ, double d) {   //metoda wykonująca przekształcenia położenia figur
        List<Surface> tempSurface = new ArrayList<Surface>();
        for (Surface surface : surfaceList) {
            Point3D[] tempPoints = new Point3D[surface.getSurfacePoints().length];
            for (int i = 0; i < surface.getSurfacePoints().length; i++) {
                double[][] pointMatrix = matrix.getPointMatrix(surface.getSurfacePoints()[i]);
                if (tx != 0 || ty != 0 || tz != 0) {
                    double[][] matrix1 = matrix.getTranslationMatrix(tx, ty, tz); //metoda wykonująca translację
                    double[][] multiplicationMatrix = matrix.getMultiplicationMatrix(matrix1, pointMatrix);
                    tempPoints[i] = new Point3D(multiplicationMatrix[0][0], multiplicationMatrix[1][0], multiplicationMatrix[2][0]); //tablica punktów po przekształceniach (bez rzutowania)

                } else if (angleX != 0) {
                    double[][] matrix1 = matrix.getRotationMatrixOX(angleX);   //metoda wykonująca obrót względem OX
                    double[][] multiplicationMatrix = matrix.getMultiplicationMatrix(matrix1, pointMatrix);
                    tempPoints[i] = new Point3D(multiplicationMatrix[0][0], multiplicationMatrix[1][0], multiplicationMatrix[2][0]); //tablica punktów po przekształceniach (bez rzutowania)

                } else if (angleY != 0) {
                    double[][] matrix1 = matrix.getRotationMatrixOY(angleY);   //metoda wykonująca obrót wzgledem OY
                    double[][] multiplicationMatrix = matrix.getMultiplicationMatrix(matrix1, pointMatrix);
                    tempPoints[i] = new Point3D(multiplicationMatrix[0][0], multiplicationMatrix[1][0], multiplicationMatrix[2][0]); //tablica punktów po przekształceniach (bez rzutowania)

                } else if (angleZ != 0) {
                    double[][] matrix1 = matrix.getRotationMatrixOZ(angleZ);   //metoda wykonująca obrót względem OZ
                    double[][] multiplicationMatrix = matrix.getMultiplicationMatrix(matrix1, pointMatrix);
                    tempPoints[i] = new Point3D(multiplicationMatrix[0][0], multiplicationMatrix[1][0], multiplicationMatrix[2][0]); //tablica punktów po przekształceniach (bez rzutowania)

                } else{
                    tempPoints[i] =  new Point3D(pointMatrix[0][0], pointMatrix[1][0], pointMatrix[2][0]);
                }
            }
            tempSurface.add(new Surface(tempPoints, surface.getColorEdge(), surface.getColorSurface()));
        }
        surfaceList.clear();
        surfaceList.addAll(new ArrayList<Surface>(painterAlgorithm.sortedSurfaces(tempSurface)));
        visibleTable = visiblePlane(surfaceList);    //ustawianie widoczności ścian
        tempSurface.clear();
        for (Surface surface : surfaceList) {   //pętla w której wykonywanie jest rzutowanie i normalizacja punktów
            Point3D[] tempPoints = new Point3D[surface.getSurfacePoints().length];
            for (int i = 0; i < surface.getSurfacePoints().length; i++) {
                double[][] pointMatrix = matrix.getPointMatrix(surface.getSurfacePoints()[i]);
                double[][] castingMatrix = matrix.getCastingMatrix(d);  //macierz rzutowania
                double[][] multiplicationCasting = matrix.getMultiplicationMatrix(castingMatrix, pointMatrix);
                double[][] normalizationPointMatrix = matrix.getNormalizationPointMatrix(multiplicationCasting);    // metoda wykonująca normalizację
                tempPoints[i] = new Point3D(normalizationPointMatrix[0][0], normalizationPointMatrix[1][0], normalizationPointMatrix[2][0]);
            }
            tempSurface.add(new Surface(tempPoints, surface.getColorEdge(), surface.getColorSurface()));   //lista ścian po wykonaniu przekształceń( z rzutowaniem), ściany do wyświetlenia na ekranie
        }
        castingSurface.clear();
        castingSurface.addAll(tempSurface);
    }

    private boolean[] visiblePlane(List<Surface> surfaceList) { //metoda sprawdzająca która ściana ma być widoczna
        boolean[] visiblePlane = new boolean[surfaceList.size()];
        int i = 0;
        for (Surface surface : surfaceList) {
            visiblePlane[i] = true;
            for (int j = 0; j < surface.getSurfacePoints().length; j++) {
                if (surface.getPointZ()[j] <= 0) {
                    visiblePlane[i] = false;
                }
            }
            i++;
        }
        return visiblePlane;
    }

    public List<Surface> getCastingSurface() {
        return castingSurface;
    }

    public boolean[] isVisible() {
        return visibleTable;
    }
}
