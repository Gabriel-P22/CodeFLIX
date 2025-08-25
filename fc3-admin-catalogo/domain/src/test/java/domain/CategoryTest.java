package domain;


import com.fullcycle.admin.catalogo.domain.category.Category;
import com.fullcycle.admin.catalogo.domain.exceptions.DomainException;
import com.fullcycle.admin.catalogo.domain.validation.handler.ThrowsValidationHandler;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CategoryTest {

    @Test
    public void givenAValidParams_whenCallNewCategory_thenInstantiateACategory() {
        final String expectedName = "filmes";
        final String expectedDescription = "A Categoria mais assistida";
        final boolean expectedIsActive = true;

        final var actualCategory =
                Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        assertNotNull(actualCategory);
        assertNotNull(actualCategory.getId());

        assertEquals(expectedName, actualCategory.getName());
        assertEquals(expectedDescription, actualCategory.getDescription());
        assertEquals(expectedIsActive, actualCategory.isActive());

        assertNotNull(actualCategory.getCreatedAt());
        assertNotNull(actualCategory.getUpdatedAt());
        assertNull(actualCategory.getDeletedAt());
    }


    @Test
    public void givenAnInvalidName_whenCallNewCategoryAndValidate_thenShouldReceiveError() {
        final String expectedName = null;
        final String expectedErrorMessage = "'name' should no be null";
        final int expectedErrorCount = 1;
        final String expectedDescription = "A Categoria mais assistida";
        final boolean expectedIsActive = true;

        final var actualCategory =
                Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        final var actualException = assertThrows(DomainException.class, () -> actualCategory.validate(
                new ThrowsValidationHandler()
        ));

        assertEquals(expectedErrorMessage, actualException.getErrors().getFirst().message());
        assertEquals(expectedErrorCount, actualException.getErrors().size());
    }


    @Test
    public void givenAnInvalidEmptyName_whenCallNewCategoryAndValidate_thenShouldReceiveError() {
        final String expectedName = "    ";
        final int expectedErrorCount = 1;
        final String expectedErrorMessage = "'name' should not be empty";
        final String expectedDescription = "A Categoria mais assistida";
        final boolean expectedIsActive = true;

        final var actualCategory =
                Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        final var actualException = assertThrows(DomainException.class, () -> actualCategory.validate(
                new ThrowsValidationHandler()
        ));

        assertEquals(expectedErrorMessage, actualException.getErrors().getFirst().message());
        assertEquals(expectedErrorCount, actualException.getErrors().size());
    }


    @Test
    public void givenAnInvalidNameLengthLessThen3_whenCallNewCategoryAndValidate_thenShouldReceiveError() {
        final String expectedName = "Fi ";
        final int expectedErrorCount = 1;
        final String expectedErrorMessage = "'name' must be between 3 and 255 characters";
        final String expectedDescription = "A Categoria mais assistida";
        final boolean expectedIsActive = true;

        final var actualCategory =
                Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        final var actualException = assertThrows(DomainException.class, () -> actualCategory.validate(
                new ThrowsValidationHandler()
        ));

        assertEquals(expectedErrorMessage, actualException.getErrors().getFirst().message());
        assertEquals(expectedErrorCount, actualException.getErrors().size());
    }


    @Test
    public void givenAnInvalidNameLengthMoreThan255_whenCallNewCategoryAndValidate_thenShouldReceiveError() {
        final String expectedName = """
                Na imensidão do tempo e do espaço, cada escolha que fazemos desenha novos caminhos,
                e embora possamos duvidar do destino, a verdade é que somos autores de nossa própria história,
                escrevendo-a com coragem, esperança e sonhos que nunca se apagammmmmmmmmmmmm
                mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm
                mmmmmmmmmmmmmmm.
                """;
        final int expectedErrorCount = 1;
        final String expectedErrorMessage = "'name' must be between 3 and 255 characters";
        final String expectedDescription = "A Categoria mais assistida";
        final boolean expectedIsActive = true;

        final var actualCategory =
                Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        final var actualException = assertThrows(DomainException.class, () -> actualCategory.validate(
                new ThrowsValidationHandler()
        ));

        assertEquals(expectedErrorMessage, actualException.getErrors().getFirst().message());
        assertEquals(expectedErrorCount, actualException.getErrors().size());
    }

    @Test
    public void givenAValidEmptyDescription_whenCallNewCategoryAndValidate_thenShouldReturnOk() {
        final String expectedName = "filmes";
        final String expectedDescription = "";
        final boolean expectedIsActive = true;

        final var actualCategory =
                Category.newCategory(expectedName, expectedDescription, expectedIsActive);


        assertDoesNotThrow(() -> {
            actualCategory.validate(new ThrowsValidationHandler());
        });

        assertNotNull(actualCategory);
        assertNotNull(actualCategory.getId());

        assertEquals(expectedName, actualCategory.getName());
        assertEquals(expectedDescription, actualCategory.getDescription());
        assertEquals(expectedIsActive, actualCategory.isActive());

        assertNotNull(actualCategory.getCreatedAt());
        assertNotNull(actualCategory.getUpdatedAt());
        assertNull(actualCategory.getDeletedAt());
    }

    @Test
    public void givenAValidFalseIsActive_whenCallNewCategoryAndValidate_thenShouldReturnOk() {
        final String expectedName = "filmes";
        final String expectedDescription = "";
        final boolean expectedIsActive = false;

        final var actualCategory =
                Category.newCategory(expectedName, expectedDescription, expectedIsActive);


        assertDoesNotThrow(() -> {
            actualCategory.validate(new ThrowsValidationHandler());
        });

        assertNotNull(actualCategory);
        assertNotNull(actualCategory.getId());

        assertEquals(expectedName, actualCategory.getName());
        assertEquals(expectedDescription, actualCategory.getDescription());
        assertEquals(expectedIsActive, actualCategory.isActive());

        assertNotNull(actualCategory.getCreatedAt());
        assertNotNull(actualCategory.getUpdatedAt());
        assertNotNull(actualCategory.getDeletedAt());
    }

    @Test
    public void givenAValidActiveCategory_whenCallDeactivate_thenReturnCategoryInactivated() {
        final String expectedName = "filmes";
        final String expectedDescription = "";
        final boolean expectedIsActive = false;

        final var aCategory =
                Category.newCategory(expectedName, expectedDescription, true);


        assertDoesNotThrow(() -> {
            aCategory.validate(new ThrowsValidationHandler());
        });

        final var updatedAt = aCategory.getUpdatedAt();

        assertNull(aCategory.getDeletedAt());
        assertTrue(aCategory.isActive());

        final var actualCategory = aCategory.deactivate();

        assertEquals(actualCategory.getId(), aCategory.getId());

        assertEquals(expectedName, actualCategory.getName());
        assertEquals(expectedDescription, actualCategory.getDescription());
        assertEquals(expectedIsActive, actualCategory.isActive());

        assertNotNull(actualCategory.getCreatedAt());
        assertTrue(actualCategory.getUpdatedAt().isAfter(updatedAt));
        assertNotNull(actualCategory.getDeletedAt());
    }

    @Test
    public void givenAValidInactivatedCategory_whenCallActivate_thenReturnCategoryActivated() {
        final String expectedName = "filmes";
        final String expectedDescription = "";
        final boolean expectedIsActive = true;

        final var aCategory =
                Category.newCategory(expectedName, expectedDescription, false);


        assertDoesNotThrow(() -> {
            aCategory.validate(new ThrowsValidationHandler());
        });

        final var createdAt = aCategory.getCreatedAt();
        final var updatedAt = aCategory.getUpdatedAt();

        assertNotNull(aCategory.getDeletedAt());
        assertFalse(aCategory.isActive());

        final var actualCategory = aCategory.activate();

        assertEquals(actualCategory.getId(), aCategory.getId());

        assertEquals(expectedName, actualCategory.getName());
        assertEquals(expectedDescription, actualCategory.getDescription());
        assertEquals(expectedIsActive, actualCategory.isActive());

        assertNotNull(actualCategory.getCreatedAt());
        assertTrue(actualCategory.getUpdatedAt().isAfter(updatedAt));
        assertEquals(createdAt, actualCategory.getCreatedAt());

        assertNull(actualCategory.getDeletedAt());
    }


}