/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.kingston.ci6115.k1715308.question2;

import java.util.List;

/**
 *
 * @author k1715308
 */

//SciCalc is a marker interface
public interface SciCalc {
 /*
    A marker interface is an interface that has no methods or constants inside it.
    It provides run-time type information about objects, so the compiler and JVM have additional information about the object.
    Serializable and Clonable are examples of marker interfaces in the Java library
    A marker interface is also called a tagging interface.
 */
}

//These are functional interfaces that extends the marker interface
interface SciCalcInt extends SciCalc {
    int operation(int x, int y);
}
interface SciCalcDouble extends SciCalc {
    double operation(double value);
}
//returns an array of values e.g. sin, cos, tan
interface SciCalcArray extends SciCalc {
    double[] operation(double values);
}
//takes a list, processes it and returns a value
interface SciCalcList extends SciCalc {
    double operation(List<Double> list);
}