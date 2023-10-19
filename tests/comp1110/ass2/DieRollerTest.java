package comp1110.ass2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DieRollerTest {
    public void testRollDieInRange() {
        int result = Marrakech.rollDie();
        assertTrue(result >= 1 && result <= 4); // Expecting a value between 1 and 4
    }

    @Test
    public void testRollDieExcludes5And6() {

        int result = Marrakech.rollDie();
        assertNotEquals(5, result); // Ensure that 5 is not returned
        assertNotEquals(6, result); // Ensure that 6 is not returned
    }

    @Test
    public void testRollDieRepeatedly() {
        // Check multiple rolls to ensure they all fall within the expected range
        for (int i = 0; i < 100; i++) {
            Marrakech DieRoller;
            int result = Marrakech.rollDie();
            assertTrue(result >= 1 && result <= 4);
        }
    }
}
