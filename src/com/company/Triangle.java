package com.company;

import java.util.ArrayList;
import java.util.Scanner;

import static com.company.Main.*;

public class Triangle {
    private double sideA;
    private double sideB;
    private double sideC;
    private double area;
    private double perimeter;
    private String name;

    public Triangle(double sideA, double sideB, double sideC, String name) throws Exception {
        if (!isTrianglePossible(sideA, sideB, sideC)) {
            throw new Exception();
        }
        this.name = name;
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
        this.perimeter = sideA + sideB + sideC;
        this.area = findArea(sideA, sideB, sideC);
    }

    double findArea(double sideA, double sideB, double sideC) {
        double p = perimeter / 2;
        double argument = p * (p - sideA) * (p - sideB) * (p - sideC);

        return Math.sqrt(argument) / 4;
    }
    boolean isTrianglePossible (double sideA, double sideB, double sideC) {
        return !(sideA >= sideB + sideC) && !(sideB >= sideC + sideA) && !(sideC >= sideB + sideA);
    }

    public static void sortByAreaDesc(ArrayList<Triangle> al, Compare comp) {
        for (int j = 0; j < al.size(); j++) {
            Triangle maxEl = al.get(j);
            int indexOfMax = j;

            for (int i = j; i < al.size(); i++) {
                int finalI = i;
                int compareRes = comp.compareTo(al.get(finalI), maxEl);
                if (compareRes > 0) {
                    maxEl = al.get(i);
                    indexOfMax = i;
                }
            }

            if (indexOfMax != j) {
                swapTriangles(al.get(j), al.get(indexOfMax));
            }
        }
    }

    public static void swapTriangles(Triangle t1, Triangle t2) {
        double temp = t1.sideA;
        t1.sideA = t2.sideA;
        t2.sideA = temp;

        temp = t1.sideB;
        t1.sideB = t2.sideB;
        t2.sideB = temp;

        temp = t1.sideC;
        t1.sideC = t2.sideC;
        t2.sideC = temp;

        temp = t1.area;
        t1.area = t2.area;
        t2.area = temp;

        temp = t1.perimeter;
        t1.perimeter = t2.perimeter;
        t2.perimeter = temp;
    }

    static Compare comp = (Triangle tt1, Triangle tt2) -> {
        double area1 = tt1.getArea();
        double area2 = tt2.getArea();

        if (area1 == area2) {
            return 0;
        }
        if (area1 > area2) {
            return 1;
        } else {
            return -1;
        }
    };

    public static void setTriangle( String a, String b, String c,String name, Scanner scanner) {
        try {
            double sideA = Double.parseDouble(a);
            double sideB = Double.parseDouble(b);
            double sideC = Double.parseDouble(c);

            if (!areNumsPositive(sideA, sideB, sideC)) {
                System.out.println("Sizes must be >= 0!");
                inputValues( scanner);
            }
            Triangle t = new Triangle(sideA, sideB, sideC, name);
            al.add(t);
            continueProgram(scanner);

        } catch (Exception e) {
            System.out.println("Please type in numeric sizes!");
            inputValues( scanner);
        }
    }

    private static boolean areNumsPositive(double size1, double size2, double size3) {
        return !(size1 < 0 || size2 < 0 || size3 < 0);
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "sideA=" + sideA +
                ", sideB=" + sideB +
                ", sideC=" + sideC +
                ", area=" + area +
                ", perimeter=" + perimeter +
                '}';
    }
    public String getName() {
        return name;
    }

    public double getArea() {
        return area;
    }

}
