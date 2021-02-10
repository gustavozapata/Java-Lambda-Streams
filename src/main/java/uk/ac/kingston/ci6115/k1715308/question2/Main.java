/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.kingston.ci6115.k1715308.question2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

/**
 *
 * @author k1715308
 */
public class Main {

    //read the data from the csv file
    public static List<List<String>> readData() {
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("data.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                //regex that cleans up the values (leaves only digits and commas)
                String[] values = line.replaceAll("[^\\d,]", "").split(",");
                records.add(Arrays.asList(values));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return records;
    }

    public static void main(String[] args) {
        List<List<String>> Numbers = readData();
        List<Double> NumberList = new ArrayList<>();
        
        //clean up the List (flatten to single dimensional list) and convert values from String to Double
        Numbers.forEach((number) -> {
            number.forEach((num) -> {
                NumberList.add(Double.parseDouble(num));
            });
        });

        //a) Basic Calculations
        System.out.println("\na) Basic Calculations");
        SciCalcInt add = (n1, n2) -> n1 + n2;
        SciCalcInt subtract = (n1, n2) -> n1 - n2;
        SciCalcInt multiply = (n1, n2) -> n1 * n2;
        SciCalcInt divide = (n1, n2) -> n1 / n2;

        getSciCalcIntResult(3, 7, add);//10
        getSciCalcIntResult(6, 3, subtract);//3
        getSciCalcIntResult(2, 8, multiply);//16
        getSciCalcIntResult(80, 4, divide);//20

        
        //b) Scientific Calculations
        System.out.println("\nb) Scientific Calculations");
        SciCalcDouble radiansToDegrees = (angleRad) -> (angleRad * 180) / Math.PI;
        SciCalcDouble degreesToRadians = (angleDeg) -> (angleDeg * Math.PI) / 180;
        SciCalcArray radiansSinCosTan = (angleRad) -> new double[] {Math.sin(angleRad), Math.cos(angleRad), Math.tan(angleRad)};

        getSciCalcDoubleResult(80, radiansToDegrees); //4583.6623610
        getSciCalcDoubleResult(20, degreesToRadians); //0.34906585
        getSciCalcArrayResult(5, radiansSinCosTan); //[-0.95892, 0.28366, -3.380515]
        
        
        //c) Java Streams - Mean and Standard Deviation
        System.out.println("\n\nc) Stream Calculations");
        System.out.println("NumberList: " + NumberList);
        /*
        NumberList: [1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610]
        Count:              15              | number of elements in the list
        Sum:                1596            | sum of all numbers
        Mean:               106.4           | this is the average of the numbers
        Variance:           28817.04        | the average of the squared differences from the Mean
        Standard Deviation: 169.75582464234 | it is the square root of the Variance
        */
        SciCalcList meanFromList = (list) -> list.stream().mapToDouble(num -> num).average().orElse(0.0);
        SciCalcList varianceFromList = (list) -> list.stream().map(num -> num - getSciCalcListResult(list, meanFromList)).map(num -> num*num).mapToDouble(num -> num).average().getAsDouble();
        SciCalcDouble standardDeviation = Math::sqrt;
        System.out.println(getSciCalcListResult(NumberList, meanFromList));
        getSciCalcDoubleResult(getSciCalcListResult(NumberList, varianceFromList), standardDeviation);
    }

    public static void getSciCalcIntResult(int n1, int n2, SciCalcInt calc) {
        int result = calc.operation(n1, n2);
        System.out.println(result);
    }
    public static void getSciCalcDoubleResult(double value, SciCalcDouble calc) {
        double result = calc.operation(value);
        System.out.println(result);
    }
    public static void getSciCalcArrayResult(double values, SciCalcArray calc) {
        for (double val : calc.operation(values)) {
            System.out.print(val + "  ");
        }
    }
    public static double getSciCalcListResult(List<Double> list, SciCalcList calc){
        double result = calc.operation(list);
        return result;
    }
}
