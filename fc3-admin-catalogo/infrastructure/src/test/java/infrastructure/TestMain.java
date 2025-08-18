package infrastructure;


import com.fullcycle.admin.catalogo.infrastructure.Main;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestMain {
    @Test
    public void testMain() {
        assertNotNull(new Main());
        Main.main(new String[]{});
    }
}