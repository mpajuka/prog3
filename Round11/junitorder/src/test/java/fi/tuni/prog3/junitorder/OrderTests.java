package fi.tuni.prog3.junitorder;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class OrderTests {
    
    @Test
    public void testIllegalPrice() {
        Exception thrown = assertThrows(IllegalArgumentException.class,
                () -> {new Order.Item(null, 1234);
                
        });
    }
}
