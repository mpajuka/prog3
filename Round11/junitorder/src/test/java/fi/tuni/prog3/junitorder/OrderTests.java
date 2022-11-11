package fi.tuni.prog3.junitorder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class OrderTests {
    
    @Test
    public void testIllegalName() {
        Exception thrown = assertThrows(IllegalArgumentException.class,
                () -> {new Order.Item(null, 1234.12);
                });
    }
    
    @Test
    public void testIllegalPrice() {
        Exception thrown2 = assertThrows(IllegalArgumentException.class,
                () -> {new Order.Item("test", -1); 
                });
    }
    
    @Test
    public void testGetPrice() {
        Order.Item instance = new Order.Item("test", 1234.12);
        double expected = 1234.12;
        assertEquals(expected, instance.getPrice());
    }
    
    @Test
    public void testGetName() {
        Order.Item instance = new Order.Item("test", 1234.12);
        String expected = "test";
        assertEquals(expected, instance.getName());
    }
    
    @Test
    public void testToString() {
        Order.Item instance = new Order.Item("test", 1234.12);
        String expected = "Item(test,1234.12)";
        assertEquals(expected, instance.toString());
    }
    
    @Test
    public void testEquals() {
        Order.Item instance = new Order.Item("test", 1234.12);
        Order.Item instance2 = new Order.Item("foobar", 4321.21);
        assertTrue(instance.equals(instance));
        assertFalse(instance.equals(instance2));
    }
    
    @Test
    public void testIllegalUnitCount() {
        Exception thrown = assertThrows(IllegalArgumentException.class,
                () -> {new Order.Entry(new Order.Item("test", 1234.12), -1);
                });
    }
    
    @Test
    public void testGetItemName() {
        Order.Entry instance = new Order.Entry(new Order.Item("a", 1.0), 1);
        String expected = "a";
        assertEquals(expected, instance.getItemName());
    }
    
    @Test
    public void testGetUnitPrice() {
        Order.Entry instance = new Order.Entry(new Order.Item("a", 1.0), 1);
        double expected = 1.0;
        assertEquals(expected, instance.getUnitPrice());
    }
    
    @Test
    public void testGetItem() {
        Order.Entry instance = new Order.Entry(new Order.Item("a", 1.0), 1);
        assertEquals(new Order.Item("a", 1.0), instance.getItem());
    }
    
    @Test
    public void testEntryToString() {
        Order.Entry instance = new Order.Entry(new Order.Item("a", 1.0), 1);
        String expected = "1 units of a";
        assertEquals(expected, instance.toString());
    }
   
    @Test
    public void testAddItemsIllegalCount() {
        Order order = new Order();
        assertThrows(IllegalArgumentException.class, () ->
        {
           order.addItems(new Order.Item("foobar", 1.0), -1);
        });
    }
    
    @Test
    public void testAdditemsDuplicate() {
        Order order = new Order();
        order.addItems(new Order.Item("foobar", 1.0), 1);
        Order.Item duplicate = new Order.Item("foobar", 2.0);
        
        assertThrows(IllegalStateException.class, () ->
        {
           order.addItems(duplicate, 1);
        });
    }
    
    
}
