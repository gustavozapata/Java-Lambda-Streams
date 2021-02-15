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
    public static List<Double> readDataFromFile() {
        List<List<String>> records = new ArrayList<>();
        List<Double> numbers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("data.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                //regex that cleans up the values (leaves only digits and commas)
                String[] values = line.replaceAll("[^\\d,]", "").split(",");
                records.add(Arrays.asList(values));
            }
            //flatten the list<list<string>> to a single dimensional list
            records.forEach((number) -> {
                number.forEach((num) -> {
                    numbers.add(Double.parseDouble(num));
                });
            });
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return numbers;
    }

    public static void main(String[] args) {
        List<Double> NumberList = readDataFromFile();

        //a) Basic Calculations
        System.out.println("\na) Basic Calculations");
        SciCalcInt add = (n1, n2) -> n1 + n2;
        SciCalcInt subtract = (n1, n2) -> n1 - n2;
        SciCalcInt multiply = (n1, n2) -> n1 * n2;
        SciCalcInt divide = (n1, n2) -> n1 / n2;

        System.out.println(getIntResult(3, 7, add));//10
        System.out.println(getIntResult(6, 3, subtract));//3
        System.out.println(getIntResult(2, 8, multiply));//16
        System.out.println(getIntResult(80, 4, divide));//20

        
        //b) Scientific Calculations
        System.out.println("\nb) Scientific Calculations");
        SciCalcDouble radiansToDegrees = (angleRad) -> (angleRad * 180) / Math.PI;
        SciCalcDouble degreesToRadians = (angleDeg) -> (angleDeg * Math.PI) / 180;
        SciCalcArray radiansSinCosTan = (angleRad) -> new double[] {Math.sin(angleRad), Math.cos(angleRad), Math.tan(angleRad)};

        System.out.println(getDoubleResult(80, radiansToDegrees)); //4583.6623610
        System.out.println(getDoubleResult(20, degreesToRadians)); //0.34906585
        System.out.println(Arrays.toString(getArrayResult(5, radiansSinCosTan))); //[-0.95892, 0.28366, -3.380515]
        
        
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
        SciCalcList mean = (list) -> list.stream().mapToDouble(num -> num).average().orElse(0.0);
        SciCalcList standardDeviation = (list) -> Math.sqrt(list.stream().map(num -> num - getResultFromList(list, mean)).map(num -> num*num).mapToDouble(num -> num).average().orElse(0.0));
        
        System.out.println(getResultFromList(NumberList, mean));
        System.out.println(getResultFromList(NumberList, standardDeviation));
    }

    public static int getIntResult(int n1, int n2, SciCalcInt calc) {
        return calc.operation(n1, n2);
    }
    public static double getDoubleResult(double value, SciCalcDouble calc) {
        return calc.operation(value);
    }
    public static double[] getArrayResult(double value, SciCalcArray calc) {
        return calc.operation(value);
    }
    public static double getResultFromList(List<Double> list, SciCalcList calc){
        return calc.operation(list);
    }
}
