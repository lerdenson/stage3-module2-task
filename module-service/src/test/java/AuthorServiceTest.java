import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.exceptions.NotFoundException;
import com.mjc.school.service.exceptions.ValidationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(NewsServiceTest.Config.class)
public class AuthorServiceTest {


    Random random = new Random();
    @Autowired
    private BaseService<AuthorDtoRequest, AuthorDtoResponse, Long> authorService;

    @Autowired
    private BaseService<NewsDtoRequest, NewsDtoResponse, Long> newsService;

    Long getRandomAuthorId() {
        List<AuthorDtoResponse> newsList = authorService.readAll();
        return newsList.get(random.nextInt(newsList.size())).getId();
    }

    @Test
    void findAllReturnsAuthors() {
        List<AuthorDtoResponse> readAllList = authorService.readAll();

        assertFalse(readAllList.isEmpty());

    }

    @Test
    void readByIdReturnsCorrectAuthor() {
        List<AuthorDtoResponse> readAllList = authorService.readAll();

        AuthorDtoResponse expectedAuthor = readAllList.get(random.nextInt(readAllList.size()));

        AuthorDtoResponse readAuthorById = authorService.readById(expectedAuthor.getId());

        assertEquals(expectedAuthor, readAuthorById);
    }

    @Test
    void findIdWithWrongIdThrowsNotFoundException() {
        Exception exception = assertThrows(NotFoundException.class, () -> authorService.readById(207L));

        String expectedErrorCode = "ERROR CODE: 02";
        assertTrue(exception.getMessage().contains(expectedErrorCode));
    }

    @Test
    void createReturnsCorrectAuthorTest() {
        AuthorDtoRequest authorDtoRequest = new AuthorDtoRequest("Maxim Morozov");
        AuthorDtoResponse response = authorService.create(authorDtoRequest);

        assertEquals(authorDtoRequest.getName(), response.getName());


    }

    @Test
    void createAddNewElementTest() {
        AuthorDtoRequest authorDtoRequest = new AuthorDtoRequest("Maxim Morozov");
        authorService.create(authorDtoRequest);
        List<AuthorDtoResponse> responseList = authorService.readAll();

        assertTrue(responseList.stream()
                .map(AuthorDtoResponse::getName)
                .anyMatch(a -> a.equals(authorDtoRequest.getName())));
    }

    @Test
    void createWithWrongDataThrowsValidationExceptionTest() {
        AuthorDtoRequest badAuthor = new AuthorDtoRequest("An");

        Exception exception = assertThrows(ValidationException.class, () -> authorService.create(badAuthor));

        String expectedErrorCode = "ERROR CODE: 05";
        assertTrue(exception.getMessage().contains(expectedErrorCode));
    }

    @Test
    void updateReturnsCorrectValueTest() {
        Long id = getRandomAuthorId();
        AuthorDtoRequest authorDtoRequest = new AuthorDtoRequest(id, "My New Name");

        AuthorDtoResponse response = authorService.update(authorDtoRequest);

        assertEquals(authorDtoRequest.getName(), response.getName());
    }

    @Test
    void updateChangeElementTest() {
        Long id = getRandomAuthorId();
        AuthorDtoRequest authorDtoRequest = new AuthorDtoRequest(id, "My Second Name");

        AuthorDtoResponse response = authorService.update(authorDtoRequest);

        AuthorDtoResponse updatedAuthor = authorService.readById(id);

        assertEquals(response, updatedAuthor);
    }

    @Test
    void updateWithWrongDataThrowsValidationExceptionTest() {
        long id = getRandomAuthorId();
        AuthorDtoRequest badAuthor = new AuthorDtoRequest(id, "An");

        Exception exception = assertThrows(ValidationException.class, () -> authorService.create(badAuthor));

        String expectedErrorCode = "ERROR CODE: 05";
        assertTrue(exception.getMessage().contains(expectedErrorCode));
    }

    @Test
    void updateWithIncorrectIdThrowsValidationException() {
        AuthorDtoRequest badAuthor = new AuthorDtoRequest(254L, "Andrew");

        Exception exception = assertThrows(NotFoundException.class, () -> authorService.update(badAuthor));

        String expectedErrorCode = "ERROR CODE: 02";
        assertTrue(exception.getMessage().contains(expectedErrorCode));
    }

    @Test
    void deleteRemovesElementTest() {
        Long id = getRandomAuthorId();
        boolean isRemoved = authorService.deleteById(id);
        List<AuthorDtoResponse> responseDTOList = authorService.readAll();

        assertTrue(isRemoved);
        assertFalse(responseDTOList.stream().anyMatch(a -> a.getId().equals(id)));
    }

    @Test
    void deleteReturnsFalseWithIncorrectIdTest() {
        int sizeBeforeDelete = authorService.readAll().size();
        boolean isRemoved = authorService.deleteById(123L);
        int sizeAfterDelete = authorService.readAll().size();

        assertFalse(isRemoved);
        assertEquals(sizeBeforeDelete, sizeAfterDelete);
    }

    @Test
    void deleteRemovesAllNewsOfDeletingAuthor() {
        List<NewsDtoResponse> allNewsBeforeDelete = newsService.readAll();
        Long authorId = allNewsBeforeDelete
                .get(random.nextInt(allNewsBeforeDelete.size()))
                .getAuthorId();

        authorService.deleteById(authorId);
        assertFalse(newsService.readAll().stream().anyMatch(a -> a.getAuthorId().equals(authorId)));
    }
}
