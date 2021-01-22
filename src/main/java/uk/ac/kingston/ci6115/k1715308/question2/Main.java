/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.kingston.ci6115.k1715308.question2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.lang.Integer;
import java.util.Scanner;

/**
 *
 * @author k1715308
 */
public class Main {

    public static void main(String[] args) {
//        InputStream is = new InputStream();
        List NumberList = new ArrayList<Integer>();
        try {
            File myObj = new File("data.csv");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        //a) Basic Calculations
        BasicCalc add = (n1, n2) -> n1 + n2;
        BasicCalc subtract = (n1, n2) -> n1 - n2;
        BasicCalc multiply = (n1, n2) -> n1 * n2;
        BasicCalc divide = (n1, n2) -> n1 / n2;

        getResult(3, 7, add);//10
        getResult(6, 3, subtract);//3
        getResult(2, 8, multiply);//16
        getResult(80, 4, divide);//20

        //b) Scientific Calculations
        SciCalc radiansToDegrees = (angle) -> (angle * 180) / Math.PI;;;;;
        SciCalc degreesToRadians = (angle) -> (angle * Math.PI) / 180;

        SciCalcMultiple sinCosTanFromRadians = (angle) -> {
            double[] values = new double[3];
            values[0] = Math.sin(angle);
            values[1] = Math.cos(angle);
            values[2] = Math.tan(angle);
            return values;
        };

        getResultSci(80, radiansToDegrees);
        getResultSci(20, degreesToRadians);
        getResultSciMultiple(5, sinCosTanFromRadians);
    }

    public static void getResult(int n1, int n2, BasicCalc calc) {
        int result = calc.intOperation(n1, n2);
        System.out.println(result);
    }

    public static void getResultSci(double value, SciCalc calc) {
        double result = calc.doubleOperation(value);
        System.out.println(result);
    }

    public static void getResultSciMultiple(double value, SciCalcMultiple calc) {
//        double[] results = calc.multipleDoubleOperation(value);
        for (double val : calc.multipleDoubleOperation(5)) {
            System.out.println(val);
        }
    }
}
