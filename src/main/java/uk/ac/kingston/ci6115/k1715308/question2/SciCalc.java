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
public interface SciCalc {
    double doubleOperation(double value);
}

//returns an array of values e.g. sin, cos, tan
interface SciCalcMultiple {
    double[] multipleDoubleOperation(double value);
}

interface SciCalcStream {
    double doubleOperation(List<Double> list);
}