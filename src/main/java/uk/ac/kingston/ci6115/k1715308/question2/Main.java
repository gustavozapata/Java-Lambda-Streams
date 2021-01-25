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
        List<List<String>> NumberList = readData();
        System.out.println(NumberList); //1	1	2	3	5	8	13	21	34	55	89	144	233	377	610

        
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
        
        getSciCalcResult(80, radiansToDegrees);
        getSciCalcResult(20, degreesToRadians);
        getSciMultipleResult(5, radiansSinCosTan);
        
        
        //a) & b) Using a generic functional interface
        runCalc((n1, n2) -> {return n1 + n2;}, 12, 3);//12 + 3 = 15
        runCalc((n1, n2) -> n1 - n2, 8, 5);//8 - 5 = 3
        runCalc((n1, n2) -> n1 * n2, 8, 5);//8 * 5 = 40
        runCalc((angle, angle2) -> {
            return (angle * 180) / Math.PI; //4583.66
        }, 80, 0);
        runCalc((angle, angle2) -> (angle * Math.PI) / 180, 20, 0); //0.3490
        
        Calc<Integer> suma = (n1, n2) -> {
            return (n1 + n2[0]);
        };
        Calc<Integer> addition = (n1, n2) -> n1 + Arrays.stream(n2).reduce(0, Integer::sum);
//        getGeneric(suma, 8, 8);
//        getGeneric(addition, 8, 8);
        addition.operation(3,6);
    }
    public static <T> void getGeneric(Calc<T> calc, T n1, T n2){
        System.out.println("Sumemos pues");
        T result = calc.operation(n1, n2);
        System.out.println(result);
    }
    public static <T, K, V> void runCalc(Calculator<? extends T, ? super K, ? super V> calc, K n1, V n2) {
        T result = calc.operation(n1, n2);
        System.out.println(result);
    }

    public static void getSciMultipleResult(double value, SciCalcMultiple calc) {
        for (double val : calc.multipleDoubleOperation(5)) {
            System.out.println(val);
        }
    }
    public static void getBasicCalcResult(int n1, int n2, BasicCalc calc) {
        int result = calc.intOperation(n1, n2);
        System.out.println(result);
    }
    public static void getSciCalcResult(double value, SciCalc calc) {
        double result = calc.doubleOperation(value);
        System.out.println(result);
    }
}
interface Calc<T> {
    T operation(T n1, T... n2);
}
interface Calculator<T, K, V> {
    T operation(K n1, V n2);
}

