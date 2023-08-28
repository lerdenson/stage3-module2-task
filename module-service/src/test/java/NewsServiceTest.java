import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.exceptions.NotFoundException;
import com.mjc.school.service.exceptions.ValidationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig
class NewsServiceTest {

    Random random = new Random();
    @Autowired
    private BaseService<NewsDtoRequest, NewsDtoResponse, Long> newsService;

    Long getRandomNewsId() {
        List<NewsDtoResponse> newsList = newsService.readAll();
        return newsList.get(random.nextInt(newsList.size())).getId();
    }

    @Test
    void findAllReturnsNews() {
        List<NewsDtoResponse> readAllList = newsService.readAll();

        for (NewsDtoResponse news : readAllList) {
            System.out.println(news);
        }

        assertFalse(readAllList.isEmpty());

    }

    @Test
    void readByIdReturnsCorrectNews() {
        List<NewsDtoResponse> readAllList = newsService.readAll();

        NewsDtoResponse expectedNews = readAllList.get(random.nextInt(readAllList.size()));

        NewsDtoResponse readByIdNews = newsService.readById(expectedNews.getId());

        System.out.println(expectedNews);
        System.out.println(readByIdNews);

        assertEquals(expectedNews, readByIdNews);
    }

    @Test
    void findIdWithWrongIdThrowsNotFoundException() {
        Exception exception = assertThrows(NotFoundException.class, () -> newsService.readById(207L));

        String expectedErrorCode = "ERROR CODE: 01";
        assertTrue(exception.getMessage().contains(expectedErrorCode));
    }

    @Test
    void createReturnsCorrectNewsTest() {
        NewsDtoRequest newsDto = new NewsDtoRequest("MY TITLE", "MY CONTENT", 7);
        NewsDtoResponse response = newsService.create(newsDto);
        System.out.println(response);

        assertEquals(newsDto.getTitle(), response.getTitle());


    }

    @Test
    void createAddNewElementTest() {
        NewsDtoRequest newsDto = new NewsDtoRequest("MY TITLE!", "MY CONTENT!", 11);
        newsService.create(newsDto);
        List<NewsDtoResponse> responseList = newsService.readAll();

        assertTrue(responseList.stream()
                .map(NewsDtoResponse::getTitle)
                .anyMatch(a -> a.equals(newsDto.getTitle())));

    }

    @Test
    void createWithWrongDataThrowsValidationExceptionTest() {
        NewsDtoRequest badNews = new NewsDtoRequest("A", "MY CONTENT!", 11);

        Exception exception = assertThrows(ValidationException.class, () -> newsService.create(badNews));

        String expectedErrorCode = "ERROR CODE: 03";
        assertTrue(exception.getMessage().contains(expectedErrorCode));
    }

    @Test
    void createWithWrongAuthorIdThrowsValidationExceptionTest() {
        NewsDtoRequest badNews = new NewsDtoRequest("CLIMBING", "MY CONTENT!", 117);

        Exception exception = assertThrows(NotFoundException.class, () -> newsService.create(badNews));

        String expectedErrorCode = "ERROR CODE: 02";
        assertTrue(exception.getMessage().contains(expectedErrorCode));
    }

    @Test
    void updateReturnsCorrectValueTest() {
        Long id = getRandomNewsId();
        NewsDtoRequest newsDto = new NewsDtoRequest(id, "MY TITLE!", "MY CONTENT!", 11);

        NewsDtoResponse response = newsService.update(newsDto);

        assertEquals(newsDto.getTitle(), response.getTitle());
    }


    @Test
    void updateChangeElementTest() {
        Long id = getRandomNewsId();
        NewsDtoRequest newsDto = new NewsDtoRequest(id, "MY TITLE!!!!", "MY CONTENT!!!!", 11);
        NewsDtoResponse response = newsService.update(newsDto);
        NewsDtoResponse updatedNews = newsService.readById(newsDto.getId());

        assertEquals(response, updatedNews);
    }

    @Test
    void updateWithWrongDataThrowsValidationExceptionTest() {
        long id = getRandomNewsId();

        NewsDtoRequest badNews = new NewsDtoRequest(id, "LALALA", "ab", 11);

        Exception exception = assertThrows(ValidationException.class, () -> newsService.create(badNews));

        String expectedErrorCode = "ERROR CODE: 04";
        assertTrue(exception.getMessage().contains(expectedErrorCode));
    }

    @Test
    void updateWithIncorrectAuthorIdThrowsValidationException() {
        long id = getRandomNewsId();

        NewsDtoRequest badNews = new NewsDtoRequest(id, "LALALA", "SASASASASAS", 209);

        Exception exception = assertThrows(NotFoundException.class, () -> newsService.create(badNews));

        String expectedErrorCode = "ERROR CODE: 02";
        assertTrue(exception.getMessage().contains(expectedErrorCode));
    }

    @Test
    void updateWithIncorrectIdThrowsValidationException() {

        NewsDtoRequest badNews = new NewsDtoRequest(254, "LALALA", "SASASASASAS", 6);

        Exception exception = assertThrows(NotFoundException.class, () -> newsService.update(badNews));

        String expectedErrorCode = "ERROR CODE: 01";
        assertTrue(exception.getMessage().contains(expectedErrorCode));
    }

    @Test
    void deleteRemovesElementTest() {
        Long id = getRandomNewsId();
        boolean isRemoved = newsService.deleteById(id);
        List<NewsDtoResponse> responseDTOList = newsService.readAll();

        assertTrue(isRemoved);
        assertFalse(responseDTOList.stream().anyMatch(a -> a.getId().equals(id)));
    }


    @Test
    void deleteReturnsFalseWithIncorrectIdTest() {
        int sizeBeforeDelete = newsService.readAll().size();
        boolean isRemoved = newsService.deleteById(123L);
        int sizeAfterDelete = newsService.readAll().size();

        assertFalse(isRemoved);
        assertEquals(sizeBeforeDelete, sizeAfterDelete);
    }

    @Configuration
    @EnableAspectJAutoProxy
    @ComponentScan(basePackages = {
            "com.mjc.school.service",
            "com.mjc.school.repository",
    })
    static class Config {

    }


}
