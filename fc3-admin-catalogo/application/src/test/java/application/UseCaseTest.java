package application;

import com.fullcycle.admin.catalogo.application.UseCase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UseCaseTest {

    @Test
    public void testCreateUseCase() {
        assertNotNull(new UseCase());
        assertNotNull(new UseCase().execute());
    }

}