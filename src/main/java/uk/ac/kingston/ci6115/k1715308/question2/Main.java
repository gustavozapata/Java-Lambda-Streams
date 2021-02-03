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
        for(List<String> number : Numbers){
            for(String num : number){
                NumberList.add(Double.parseDouble(num));
            }
        }
        System.out.println(NumberList); //[1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610]

        
        //a) Basic Calculations
        BasicCalc add = (n1, n2) -> n1 + n2;
        BasicCalc subtract = (n1, n2) -> n1 - n2;
        BasicCalc multiply = (n1, n2) -> n1 * n2;
        BasicCalc divide = (n1, n2) -> n1 / n2;

        getBasicCalcResult(3, 7, add);//10
        getBasicCalcResult(6, 3, subtract);//3
        getBasicCalcResult(2, 8, multiply);//16
        getBasicCalcResult(80, 4, divide);//20

        
        //b) Scientific Calculations
        SciCalc radiansToDegrees = (angleRad) -> (angleRad * 180) / Math.PI;
        SciCalc degreesToRadians = (angleDeg) -> (angleDeg * Math.PI) / 180;
        SciCalcMultiple radiansSinCosTan = (angleRad) -> new double[] {Math.sin(angleRad), Math.cos(angleRad), Math.tan(angleRad)};
        
        getSciCalcResult(80, radiansToDegrees); //4583.6623610
        getSciCalcResult(20, degreesToRadians); //0.34906585
        getSciMultipleResult(5, radiansSinCosTan); //[-0.95892, 0.28366, -3.380515]
        
        
        //c) Java Streams - Mean and Standard Deviation
        /*
        NumberList: [1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610]
        Count:              15              | number of elements in the list
        Sum:                1596            | sum of all numbers
        Mean:               106.4           | this is the average of the numbers
        Variance:           28817.04        | the average of the squared differences from the Mean
        Standard Deviation: 169.75582464234 | it is the square root of the Variance
        */
        double sum = NumberList.stream().mapToDouble(num -> num).sum();
        double mean = NumberList.stream().mapToDouble(num -> num).average().orElse(0.0);
        double variance = NumberList.stream().map(num -> num - mean).map(num -> num*num).mapToDouble(num -> num).average().getAsDouble();

        System.out.println(sum);
        System.out.println(mean);
        System.out.println(variance);
        System.out.println(Math.sqrt(variance));
        
        
        // ########################################
        
        
        //Mark: you can link up more than one intermediate operation so the results of one feed into the next
        double standardDeviation = NumberList.stream().map(num -> num - mean).map(num -> num*num).map(Math::sqrt).mapToDouble(num -> num).average().getAsDouble();
        
        SciCalcStream numberListMean = (list) -> list.stream().mapToDouble(num -> num).average().orElse(0.0);
        SciCalcStream numberListStandardDeviation = (list) -> list.stream().map(num -> num - mean).map(num -> num*num).mapToDouble(num -> num).average().getAsDouble();

        // Variance: the average of the squared differences from the Mean
        // for each number: subtract the Mean and square the result, then work out the average of those squared differences
        // 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610 | avg: 106.4
        // -105.4, -105.4, -104.2, -103.2, -101.2, -98.4, -93.4, -85.4, -72.4, -51.4, -17.4, 37.6, 126.6, 270.6, 503.6
        // 11,109.16, 11,109.16, 10,857.64, 10,650.24, 10,241.44, 9,682.56, 8,723.56, 7,293.16, 5,241.76, 2,641.96, 302.76, 1,413.76, 16,027.56, 73,224.36, 253,612.96
        // 432,132.04 / 15 = 28,808.8026666 srt() = 169.731560
        double myMean = NumberList.stream().mapToDouble(num -> num).sum();
        System.out.println("myMean: " + myMean);
        
        System.out.println("Feb 2021");
        getSciCalcStreamResult(NumberList, numberListMean);
        getSciCalcStreamResult(NumberList, numberListStandardDeviation);
        System.out.println(standardDeviation);
    }
    public static void NumberListToInt(){
        List<List<String>> Numbers = readData();
        List<Integer> NumberListInt = new ArrayList<>();
        
        for(List<String> number : Numbers){
            for(String num : number){
                NumberListInt.add(Integer.parseInt(num));
            }
        }
        
        int sumInt = NumberListInt.stream().reduce(0, (n1, n2) -> n1 + n2);
        System.out.println(sumInt);
        System.out.println(sumInt / NumberListInt.size());
    }
    
    public static void getSciCalcStreamResult(List<Double> list, SciCalcStream calc){
        double result = calc.doubleOperation(list);
        System.out.println(result);
    }
    public static void getBasicCalcResult(int n1, int n2, BasicCalc calc) {
        int result = calc.intOperation(n1, n2);
        System.out.println(result);
    }
    public static void getSciCalcResult(double value, SciCalc calc) {
        double result = calc.doubleOperation(value);
        System.out.println(result);
    }
    public static void getSciMultipleResult(double value, SciCalcMultiple calc) {
        for (double val : calc.multipleDoubleOperation(5)) {
            System.out.println(val);
        }
    }
}
