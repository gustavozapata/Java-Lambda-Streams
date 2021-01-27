/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.kingston.ci6115.k1715308.question2;

import java.util.Arrays;
import java.util.stream.Stream;
/**
 *
 * @author k1715308
 */
//Using a generic functional interface for both int and double values
public class GenericInterfaceSolutions {
    public static void main2(String[] args){
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
//        Calc01<Integer> addition11 = (n1, n2) -> n1 + Arrays.stream(n2).reduce(0, Integer::sum);
        Calc02<Integer> addition22 = n -> (n == null) ? 0 : 
                                    Arrays.stream(n).reduce(0, Integer::sum);
        Calc02<Integer> addition23 = n -> (n == null) ? 0 : Arrays.stream(n).reduce(0, (ans, i) -> (i - ans));
        System.out.println(addition22.operation(1, 2)); //3
        System.out.println(addition22.operation(10, 20)); //30
        System.out.println(addition23.operation(30, 10)); //20
        
        Calc03<Integer> addition03 = (n1, n2) -> n1 + n2;
        Calc03<Integer> sbtraction = (n1, n2) -> n1 - n2;
        Calc03<Double> esp01 = (angle, angle2) -> (angle * 180) /Math.PI;
        Integer sum = addition03.operation(1, 2, 3, 4);
        System.out.println(esp01.operation(80.00, 0.00));
        System.out.println(sum);
        System.out.println(sbtraction.operation(20, 3));
    }
    
    public static <T> void getGeneric(Calc<T> calc, T n1, T n2){
        T result = calc.operation(n1, n2);
        System.out.println(result);
    }
    public static <T, K, V> void runCalc(Calculator<? extends T, ? super K, ? super V> calc, K n1, V n2) {
        T result = calc.operation(n1, n2);
        System.out.println(result);
    }
}

interface Calc01<T> {
    T operation(T n1, T n2);
}
interface Calc02<T> {
    T operation(T... n);
}
interface Calc<T> {
    T operation(T n1, T... n2);
}
interface Calculator<T, K, V> {
    T operation(K n1, V n2);
}
interface Calc03<T> {
    T operation(T n1, T n2);
    default T operation(T first, T... args) {
        return Stream.concat(Stream.of(first), Arrays.stream(args))
            .reduce(this::operation).orElseThrow();
    }
}
