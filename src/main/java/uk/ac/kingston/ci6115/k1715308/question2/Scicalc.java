/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.kingston.ci6115.k1715308.question2;

/**
 *
 * @author k1715308
 */
/*
a) Given two numbers x and y return x + y 
Given two numbers x and y return x * y 
Given two numbers x and y return x - y 
Given two numbers x and y return x / y (2%)
b) Given an angle x in Radians return the value of the angle in Degrees. 
Given an angle x in Degrees return the value of the angle in Radians. 
Given an angle x in Radians return the values of Sin(x), Cos(x) and Tan(x) (4%)
c) Using the java Streams return the mean of the numbers in the List NumberList
Using the java Streams function return the Standard Deviation of the numbers in List NumberList (4%)
*/
public interface Scicalc {
    int add(int x, int y);
    int subtract(int x, int y);
    int multiply(int x, int y);
    int divide(int x, int y);
    
    double radiansToDegrees(double angle);
    double degreesToRadians(double angle);
    double[] sinCosTanFromRadians(double angle);
}
