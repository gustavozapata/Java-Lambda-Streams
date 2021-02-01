/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.kingston.ci6115.k1715308.question2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test; //JUnit test 5
//import static org.junit.jupiter.api.Assertions.*;
import org.junit.Ignore;
import org.junit.Test; //JUnit test 4
import static org.junit.Assert.*;

/**
 *
 * @author k1715308
 */
public class BasicCalcTest {
    
    public BasicCalcTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of intOperation method, of class BasicCalc.
     */
    @Test
//    @Ignore - ORIGINAL TEST CREATED BY JUNIT
    public void testIntOperation() {
        System.out.println("intOperation");
        int x = 0;
        int y = 0;
        BasicCalc instance = new BasicCalcImpl();
        int expResult = 0;
        int result = instance.intOperation(x, y);
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
    
    @Test
    public void testAddOperation(){
        System.out.println("Add Operation");
        BasicCalc add = (n1, n2) -> n1 + n2;
        int expResult = 12;
        int result = add.intOperation(7, 5);
        assertEquals(expResult, result);
    }
    @Test
    public void testSubtractOperation() {
        System.out.println("Subtract Operation");
        BasicCalc subtract = (n1, n2) -> n1 - n2;
        int expResult = 5;
        int result = subtract.intOperation(10, 5);
        assertEquals(expResult, result);
    }

    public class BasicCalcImpl implements BasicCalc {

        public int intOperation(int x, int y) {
            return 0;
        }
    }
    
}
