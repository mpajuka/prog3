
package fi.tuni.prog3.junitattainment;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;




public class AttainmentTest {
    
    @Test
    public void testGetCourseCode() {
        System.out.println("getCourseCode");
        Attainment instance = new Attainment("COMP.CS.140", "12344321", 3);
        String expected = "COMP.CS.140";
        String result = instance.getCourseCode();
        assertEquals(expected, result);   
    }
    
    @Test
    public void testGetStudentNumber() {
        System.out.println("getStudentNumber");
        Attainment instance = new Attainment("COMP.CS.140", "12344321", 3);
        String expected = "12344321";
        String result = instance.getStudentNumber();
        assertEquals(expected, result);
    }
    
    @Test
    public void testGetGrade() {
        System.out.println("getGrade");
        Attainment instance = new Attainment("COMP.CS.140", "12344321", 3);
        int expected = 3;
        int result = instance.getGrade();
        assertEquals(expected, result);
    }
    
    @Test
    public void testIllegalArgumentException() {
        System.out.println("IllegalArgumentException");
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> {throw new IllegalArgumentException("test message");
                });
        assertEquals("test message", thrown.getMessage());
    }
    
    @Test
    public void testToString() {
        System.out.println("toString");
        Attainment instance = new Attainment("COMP.CS.140", "12344321", 3);
        String expected = "COMP.CS.140 12344312 3";
        assertEquals(expected, instance.toString());
    }
    
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
       
    }
 
}
