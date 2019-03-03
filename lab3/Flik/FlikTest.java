import static org.junit.Assert.*;
import org.junit.Test;

import java.security.PublicKey;


public class FlikTest {
    @Test(timeout = 1000)
    public void testSame() {
        int i = 127;
        int j = 127;
        assertTrue(Flik.isSameNumber(i, j));

        for (i = 129, j = 129; i < 500; i++, j++) {
            assertTrue(Flik.isSameNumber(i, j));
        }

        i = 128;
        j = 128;
        assertTrue(Flik.isSameNumber(i, j));
    }
}
