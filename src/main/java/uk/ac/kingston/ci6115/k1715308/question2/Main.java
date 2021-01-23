/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.kingston.ci6115.k1715308.question2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.lang.Integer;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author k1715308
 */
public class Main {

    public static List<List<String>> readCSV() {
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("data.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                records.add(Arrays.asList(values));
            }

        } catch (Exception e) {
            System.out.println("GZ error: " + e);
        }

        List<List<String>> records2 = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File("data.csv"));) {
            while (scanner.hasNextLine()) {
                records2.add(getRecordFromLine(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            System.out.println("GZ error: " + e);
        }

        System.out.println("3rd method");
        System.out.println(records2);

        return records;
    }

    private static List<String> getRecordFromLine(String line) {
        List<String> values = new ArrayList<String>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(",");
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        return values;
    }

    public static void main(String[] args) {
        List<List<String>> NumberList = readCSV();
        System.out.println("2nd method");
        System.out.println(NumberList); //1	1	2	3	5	8	13	21	34	55	89	144	233	377	610

        try {
            File myObj = new File("data.csv");
            Scanner myReader = new Scanner(myObj);
            System.out.println("1st method");
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

        String a = "A";
        Long b = 1L;
//        runCalc((ta, tb) -> {
//            return 1;
//        }, a, b);

//        Calc add3 = (n1, n2) -> n1 + n2;
//        Calculator add4 = (n1, n2) -> n1 + n2;
        int num01 = 8;
        int num02 = 5;
        runCalc((n1, n2) -> {
            return n1 + n2;
        }, num01, num02);//8 + 5 = 13
        runCalc((n1, n2) -> n1 - n2, num01, num02);//8 - 5 = 3
        runCalc((n1, n2) -> n1 * n2, num01, num02);//8 * 5 = 40
        runCalc((angle, angle2) -> {
            return (angle * 180) / Math.PI;
        }, num01, num02);
//        SciCalc degreesToRadians = (angle) -> (angle * Math.PI) / 180;
    }

    public static <T, K, V> void runCalc2(Calculator<? extends T, ? super K, ? super V> calc, K n1, V n2) {
        System.out.println("Vamos generic 02");
        T result = calc.operation(n1, n2);
        System.out.println(result);
    }

    public static <T, K, V> void runCalc(Calculator<? extends T, ? super K, ? super V> calc, K n1, V n2) {
        System.out.println("Vamos generic");
        T result = calc.operation(n1, n2);
        System.out.println(result);
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
