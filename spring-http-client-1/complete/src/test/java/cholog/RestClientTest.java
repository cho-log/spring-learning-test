package cholog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class RestClientTest {

    @Autowired
    private TodoRestClient todoRestClient;

    @Test
    public void testGetTodos() {
        List<Todo> todos = todoRestClient.getTodos();
        assertThat(todos).isNotEmpty();
    }

    @Test
    public void testGetTodoWithId() {
        Todo todo = todoRestClient.getTodoById(1L);
        assertThat(todo.getTitle()).isNotEmpty();
    }

    @Test
    public void testGetTodoWithNonExistentId() {
        Long nonExistentId = 9999L;

        assertThatThrownBy(() -> todoRestClient.getTodoById(nonExistentId))
                .isInstanceOf(TodoException.NotFound.class);
    }
}
