package domain;


import com.fullcycle.admin.catalogo.domain.Category;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CategoryTest {

    @Test
    public void testNewCategory() {
        assertNotNull(new Category());
    }

}