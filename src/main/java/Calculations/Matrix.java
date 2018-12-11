package Calculations;

import javafx.geometry.Point3D;


public class Matrix {

    public double[][] getMultiplicationMatrix(double[][] matrix1, double[][] matrix2) {     //metoda mnożenia macierzy
        double[][] tempMatrix = new double[matrix1.length][];
        try {
            for (int i = 0; i < matrix1.length; i++) {
                if (matrix1[i].length != matrix2.length) {
                    System.out.println("Nieprawidłowe wymiary macierzy do mnożenia");
                    break;
                }
                tempMatrix[i] = new double[matrix2[i].length];
                for (int j = 0; j < matrix2[i].length; j++) {
                    for (int k = 0; k < matrix1[i].length; k++) {
                        tempMatrix[i][j] += matrix1[i][k] * matrix2[k][j];
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Błąd w mnożeniu macierzy");
        }
        return tempMatrix;
    }

    public double[][] getPointMatrix(Point3D point3D) {     //macierz punktu
        double[][] tempMatrix = new double[4][1];
        tempMatrix[0][0] = point3D.getX();
        tempMatrix[1][0] = point3D.getY();
        tempMatrix[2][0] = point3D.getZ();
        tempMatrix[3][0] = 1;
        return tempMatrix;
    }

    public double[][] getTranslationMatrix(double tx, double ty, double tz) {       //macierz translacji
        double[][] tempMatrix = new double[4][4];
        tempMatrix[0][0] = 1;
        tempMatrix[1][1] = 1;
        tempMatrix[2][2] = 1;
        tempMatrix[3][3] = 1;
        tempMatrix[0][3] = tx;
        tempMatrix[1][3] = ty;
        tempMatrix[2][3] = tz;
        return tempMatrix;
    }

    public double[][] getRotationMatrixOX(double angleDegX) {       //macierz obrotu względem OX
        double[][] tempMatrix = new double[4][4];
        tempMatrix[0][0] = 1;
        tempMatrix[1][1] = Math.cos(Math.toRadians(angleDegX));
        tempMatrix[1][2] = -Math.sin(Math.toRadians(angleDegX));
        tempMatrix[2][1] = Math.sin(Math.toRadians(angleDegX));
        tempMatrix[2][2] = Math.cos(Math.toRadians(angleDegX));
        tempMatrix[3][3] = 1;
        return tempMatrix;
    }

    public double[][] getRotationMatrixOY(double angleDegY) {   //macierz obrotu względem OY
        double[][] tempMatrix = new double[4][4];
        tempMatrix[0][0] = Math.cos(Math.toRadians(angleDegY));
        tempMatrix[0][2] = Math.sin(Math.toRadians(angleDegY));
        tempMatrix[1][1] = 1;
        tempMatrix[2][0] = -Math.sin(Math.toRadians(angleDegY));
        tempMatrix[2][2] = Math.cos(Math.toRadians(angleDegY));
        tempMatrix[3][3] = 1;
        return tempMatrix;
    }

    public double[][] getRotationMatrixOZ(double angleDegZ) {   //macierz obrotu względem OZ
        double[][] tempMatrix = new double[4][4];
        tempMatrix[0][0] = Math.cos(Math.toRadians(angleDegZ));
        tempMatrix[0][1] = -Math.sin(Math.toRadians(angleDegZ));
        tempMatrix[1][0] = Math.sin(Math.toRadians(angleDegZ));
        tempMatrix[1][1] = Math.cos(Math.toRadians(angleDegZ));
        tempMatrix[2][2] = 1;
        tempMatrix[3][3] = 1;
        return tempMatrix;
    }

    public double[][] getCastingMatrix(double d) {  //macierz rzutowania
        double[][] tempMatrix = new double[4][4];
        try {
            tempMatrix[0][0] = 1;
            tempMatrix[1][1] = 1;
            tempMatrix[2][2] = 1;
            tempMatrix[3][2] = 1 / d;
        } catch (Exception e) {
            System.err.println("Odległość rzutowania powinna być większa od zera!!!");
        }
        return tempMatrix;
    }

    public double[][] getNormalizationPointMatrix(double[][] matrix1) {    //macierz normalizacji
        double[][] matrix2 = new double[4][1];
        try {
            for (int i = 0; i < 4; i++) {
                matrix2[i][0] = matrix1[i][0] / matrix1[3][0];
            }
        } catch (Exception e) {
            System.out.println("Błąd normalizacji macierzy");
        }
        return matrix2;
    }
}
